package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JPanel;
import Controller.Direction;
import assetclasses.AbstractAsset;
import server.UdpClient;
import server.UdpServer;
/**
 * A class containing the core part of the game 
 * 
 * @author Group 10
 * @version 2020-03-08
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
	// NewKeyPressedBool
	private static volatile boolean isKeyPressed = false;
	// general counter for different purposes
	int counter = 0;
	// set base Direction for enum
	private static Direction direction = Direction.RIGHT;
	// list with assets
	public List<AbstractAsset> assets;
	// Starting position
	private final Point pos = new Point(0, 0);
	// Symbols
	private AbstractAsset asset;
	private AbstractAsset movingAsset;
	// Current level
	String CurrentLevel;
	// music for each level
	HashMap<String, String> levelMusic = new HashMap<>();
	// asset that moves
	List<AbstractAsset> Movingassets = new ArrayList<>();
	private int period = 1000; // ms
	private int firstTime = 0;
	private boolean slowTime = false;
	private long timeToBeatGame;

	// server stuff
	UdpServer server = new UdpServer();
	UdpClient client = new UdpClient();
	Thread serverThread = new Thread(this.server);
	Thread clientThread = new Thread(this.client);
	private static String profileName = "";
	private int HowManyTries = 0;

	private void loadInLevelMusicPaths() {
		this.levelMusic.put("level1", "src/Music/level1.aifc");
		this.levelMusic.put("level2", "src/Music/level2.aifc");
		this.levelMusic.put("level3", "src/Music/level3.aifc");
		this.levelMusic.put("level4", "src/Music/level4.aifc");
		this.levelMusic.put("level5", "src/Music/level5.aifc");
		this.levelMusic.put("level6", "src/Music/level6.aifc");
		this.levelMusic.put("level7", "src/Music/level7.aifc");
		this.levelMusic.put("level8", "src/Music/level8.aifc");
		this.levelMusic.put("level9", "src/Music/level9.aifc");
		this.levelMusic.put("level10","src/Music/level10.aifc");
		
	}

	private String getLevelMusic(final String level) {
		return this.levelMusic.get(level);
	}

	/**
	 * A method to say whether a key is currently pressed or not.
	 * @return The status of the key
	 */
	public static boolean isKeyPressed() {
		return isKeyPressed;
	}
/**
 * Sets isKeyPressed to true or false.
 * @param isKeyPressed
 */
	public static void setKeyPressed(final boolean isKeyPressed) {
		GamePanel.isKeyPressed = isKeyPressed;
	}
/**
 * Give the direction. 
 *
 * @return The direction.
 */

	public static Direction getDirection() {
		return direction;
	}

	private class keyLis extends KeyAdapter {

		@Override
		public void keyPressed(final KeyEvent e) {

			final int input = e.getKeyCode();
			switch (input) {

			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:

				direction = Direction.UP;
				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:

				direction = Direction.DOWN;
				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:

				direction = Direction.RIGHT;
				break;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:

				direction = Direction.LEFT;
				break;

			case KeyEvent.VK_R:

				// GameWindowTemp.setRestartState();
				break;

			default:
				break;
			}
			isKeyPressed = true;
		}
	}

	@SuppressWarnings("unused")
	private static void playMusic(final String path) {
		// precaution
		if (!path.equals("src/Music/level1.aifc") && !path.equals(null)) {
			MusicPlayer.StopMusic();
			new MusicPlayer(path);
		
		} else if (MusicPlayer.isMusicRunning()) {
			MusicPlayer.StopMusic();
			new MusicPlayer(path);

		} else {
			new MusicPlayer(path);
		}
	}

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
				this.movingAsset.hasDirections(direction);
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
		final ReactionScreen gos = new ReactionScreen(path, Msg);
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
	public GamePanel(final String CurrentLevel, final String profileName) {

		this.HowManyTries = 1;
		GamePanel.profileName = profileName;

		UDPSetup();

		// read in and build level
		this.CurrentLevel = CurrentLevel;
		this.world = new ReadInWorld(this.CurrentLevel);
		this.world.startControllers();

		// set layout
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		this.setVisible(true);

		// adding the keylistener
		this.addKeyListener(new keyLis());
		this.setFocusable(true);

		// add music
		loadInLevelMusicPaths();
		GamePanel.playMusic(getLevelMusic(this.CurrentLevel));

		startTimer(0, 1000 / 60);
		StopWatch.start();
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
					// reset timer
					StopWatch.start();
					GamePanel.this.HowManyTries++;
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
					String nextLevelNumber = GamePanel.this.CurrentLevel
							.substring(GamePanel.this.CurrentLevel.length() - 1);
					nextLevelNumber = String.valueOf(Integer.parseInt(nextLevelNumber) + 1);
					GamePanel.this.CurrentLevel = "level" + nextLevelNumber;
						}
						
					GamePanel.this.world = new ReadInWorld(GamePanel.this.CurrentLevel);
						GameWindowTemp.setStateGame();
						GamePanel.this.world.startControllers();
						playMusic(getLevelMusic(GamePanel.this.CurrentLevel));
					}
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
			}
		}, this.firstTime, this.period);
	}
}