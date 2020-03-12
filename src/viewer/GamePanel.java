package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JPanel;
import Controller.KeyListenerController;
import model.AbstractAsset;
import model.GameSettings;
import model.MusicPlayer;
import model.ReadInWorld;
import model.StopWatch;
import server.UdpClient;
import server.UdpServer;
/**
 * A class containing the core part of the game 
 * 
 * @author Group 10
 * @version 2020-03-13
 *
 */
public class GamePanel extends JPanel {
	//Gamepanels
	public static volatile int numberOfControllers = 0;
	// This window
	GamePanel gamePanel = this;
	// Level read
	public static boolean levelRead = false;
	// Read in level
	private ReadInWorld world;
	// Window size
	private static final int WIDTH = GameSettings.getWidth();
	private static final int HEIGHT = GameSettings.getHeight();
	// Size of an AbstractAsset
	private static final int SIZE = GameSettings.getAssetsize();
	// Serial
	private static final long serialVersionUID = 1L;
	// general counter for different purposes
	int counter = 0;
	// list with assets
	public List<AbstractAsset> assets;
	// Starting position
	private final Point pos = new Point(0, 0);
	// Symbols
	private AbstractAsset asset;
	private AbstractAsset movingAsset;
	// Current level
	String CurrentLevel;

	private int period = 1000; // ms
	private int firstTime = 0;
	private boolean slowTime = false;
	private long timeToBeatGame;
	
	//music
	private MusicPlayer musicplayer; 

	// server stuff
	UdpServer server = new UdpServer();
	UdpClient client = new UdpClient();
	Thread serverThread = new Thread(this.server);
	Thread clientThread = new Thread(this.client);
	private static String profileName = "";
	private int HowManyTries = 0;

	
	private void initWorld(final Graphics g) {

		final List<AbstractAsset> assets = this.world.getAssetList();
		final List<AbstractAsset> movingAssets = this.world.getMovingAssets();
		for (int i = 0; i < assets.size(); i++) {
			// Get first asset
			this.asset = assets.get(i);
			this.movingAsset = movingAssets.get(i);
			// Time for a new row?
			if (this.pos.x == WIDTH) {
				this.pos.x = 0;
				this.pos.y = this.pos.y + SIZE;
			}
			// Paint all assets
			if (this.asset.getCoords() != this.pos) {
				this.movingAsset.hasDirections(KeyListenerController.getDirection());
				this.asset.paintAsset(g, this.gamePanel);
				this.movingAsset.paintAsset(g, this.gamePanel);
			}
			this.pos.x = this.pos.x + SIZE;
		}
		this.pos.y = 0;
		this.pos.x = 0;
	}

	// 0 = deathScreen , 1= Winscreen
	private void initDeathOrWinScreen(final Graphics g, final String path, final String Msg) {

		this.slowTime = true;
		final ReactionScreen gos = new ReactionScreen(path, Msg, GamePanel.this.musicplayer);
		gos.render(g);
		this.add(gos);

	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);

		if (GameWindowTemp.isGameState()) {
			initWorld(g);
		} else if (GameWindowTemp.isDeathScreenState()) {
			initDeathOrWinScreen(g, "src/assets/GameOverScreen.jpg", "bad");
		} else if (GameWindowTemp.isWinState()) {
			final String finishtime = Long.toString(StopWatch.stopTimer());
			initDeathOrWinScreen(g, "src/assets/WinScreen.png", "It took you " + GamePanel.this.HowManyTries + " tries and " + finishtime + "s" + " to beat the game!");
		}
	}

	private void UDPSetup() {

		this.serverThread.start();
		this.clientThread.start();
	}

	/**
	 * Constructor for the GamePanel class. Sets the currentLevel that shall be loaded in and the Profile name for the session.
	 * @param CurrentLevel
	 * @param profileName
	 */
	public GamePanel(final String CurrentLevel, final String profileName, MusicPlayer musicplayer) {

		this.HowManyTries = 1;
		GamePanel.profileName = profileName;
		closeSocket();
		UDPSetup();

		// read in and build level
		this.CurrentLevel = CurrentLevel;
		this.world = new ReadInWorld(this.CurrentLevel);
		this.world.startControllers();
		// if it's a saved game, change it to the right level
		if(this.CurrentLevel == "saveLevel") {
			this.timeToBeatGame = world.getTime();
			this.CurrentLevel = world.getCurrentSavedLevel();;
			this.HowManyTries = world.getAttempts();
		}
		// set layout
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		this.setVisible(true);

		// adding the keylistener
		this.addKeyListener(new KeyListenerController());
		this.setFocusable(true);

		// add music
		this.musicplayer = musicplayer;
		GamePanel.this.musicplayer.playMusic(this.CurrentLevel);

		startTimer(0, 1000 / 60);
		StopWatch.start();
	
	}
	
	private void closeSocket() {
		GamePanel.this.client.stopSocket();
		try {
			GamePanel.this.client.sendMessage("bye");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void setTimeToCompleteGame() {

		this.timeToBeatGame = this.timeToBeatGame + StopWatch.stopTimer();

	}

	private void startTimer(final int firstTime, final int period) {

		this.period = period;
		this.firstTime = firstTime;

		final Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				if (GameWindowTemp.isRestartState()) {
					GamePanel.this.world = new ReadInWorld(GamePanel.this.CurrentLevel);
					GameWindowTemp.setStateGame();
					GamePanel.this.world.startControllers();
					// set timer
					setTimeToCompleteGame();
					StopWatch.start();
					GamePanel.this.HowManyTries++;
				}
				if(GameWindowTemp.isSaveLevelState()) {
					setTimeToCompleteGame();
					GamePanel.this.world.saveLevel(GamePanel.this.CurrentLevel, GamePanel.this.timeToBeatGame, GamePanel.this.HowManyTries);
					GameWindowTemp.setStateGame();
					StopWatch.start();
				}

				if (GameWindowTemp.isNextLevelState()) {

					// add time it took to beat level!
					setTimeToCompleteGame();

					// temporary set when beating level 1 you win the game!
					if (GamePanel.this.CurrentLevel.equals("level10")) {

						final String temptime = Long.toString(StopWatch.stopTimer());											
						
						final String time = "Name: " + GamePanel.profileName + " \t Tries: " + GamePanel.this.HowManyTries + " \t " + "Time it took to beat the game: "
								+ temptime + "s";
						try {
							GamePanel.this.client.sendMessage(time);
						} catch (final IOException e) {
							e.printStackTrace();
						}
						GameWindowTemp.setWinState();
					}
						
						
					else {
						if (GamePanel.this.CurrentLevel.equals("level9"))
							GamePanel.this.CurrentLevel = "level10";
						else {
							String nextLevelNumber = GamePanel.this.CurrentLevel.substring(GamePanel.this.CurrentLevel.length() - 1);
							nextLevelNumber = String.valueOf(Integer.parseInt(nextLevelNumber) + 1);
							GamePanel.this.CurrentLevel = "level" + nextLevelNumber;
						}
						
						GamePanel.this.world = new ReadInWorld(GamePanel.this.CurrentLevel);
						GameWindowTemp.setStateGame();
						GamePanel.this.world.startControllers();
						musicplayer.playMusic(GamePanel.this.CurrentLevel);					}
				}

				else if (GameWindowTemp.isGameState()) {
					if (GamePanel.this.slowTime == true) {
						timer.cancel();
						startTimer(0, 1000 / 60);
						GamePanel.this.slowTime = false;
						repaint();
					} else {
						repaint();
					}
				}

				else if (GameWindowTemp.isDeathScreenState() || GameWindowTemp.isWinState()) {
					timer.cancel();
					startTimer(5000, 100000);
					repaint();
				}
				else {
					timer.cancel();
				}
			}
		}, this.firstTime, this.period);
	}
}