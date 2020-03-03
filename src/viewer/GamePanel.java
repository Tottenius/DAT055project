package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Controller.Direction;
import assetclasses.AbstractAsset;
import server.UdpClient;
import server.UdpServer;

public class GamePanel extends JPanel {
	
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
	//general counter for different purposes
	int counter = 0;
	//set base Direction for enum
	private static Direction direction = Direction.RIGHT;
	// list with assets
	public List<AbstractAsset> assets;
	// Starting position
	private Point pos = new Point(0,0);
	// Symbols
	private AbstractAsset asset;
	private AbstractAsset movingAsset;
	//Current level
	String CurrentLevel;
	//music for each level
	HashMap<String, String> levelMusic = new HashMap<String, String>();
	//asset that moves
	List<AbstractAsset> Movingassets = new ArrayList<AbstractAsset>();
	private int period= 1000; // ms
	private int firstTime = 0;
	private boolean slowTime = false;
	private long timeToBeatGame;
	
	//server stuff
	UdpServer server = new UdpServer();
	UdpClient client = new UdpClient();
	Thread serverThread = new Thread(server);
	Thread clientThread = new Thread(client);
	
	public void loadInLevelMusicPaths() {
	levelMusic.put("level1","src/Music/level1.aifc");
	levelMusic.put("level2","src/Music/level2.aifc");
	levelMusic.put("level3","src/Music/level3.aifc");
	levelMusic.put("level4","src/Music/level4.aifc");	
}

	public String getLevelMusic(String level) {
	System.out.println(level);
	System.out.println(levelMusic.get(level));
	return levelMusic.get(level);
}
	
	public static boolean isKeyPressed() {
		return isKeyPressed;
	}

	public static void setKeyPressed(boolean isKeyPressed) {
		GamePanel.isKeyPressed = isKeyPressed;
	}
	
	// get direction
	public static Direction getDirection() {
		return direction;
	}
	
	private class keyLis extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int input = e.getKeyCode();
			switch (input) {

			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:

				System.out.println("Moved Up");
				direction = Direction.UP;
				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:

				System.out.println("Moved Down");
				direction = Direction.DOWN;
				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:

				System.out.println("Moved right");
				direction = Direction.RIGHT;
				break;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				
				System.out.println("Moved Left");
				direction = Direction.LEFT;
				break;

			default:
				break;
			}
			isKeyPressed = true;
		}
	}
	
	public static void playMusic(String path) {
		//precaution
		if(!path.equals("src/Music/level1.aifc") && !path.equals( null )) {
			System.out.println("we stop music");
			MusicPlayer.StopMusic();
			new MusicPlayer(path);
		}		
		else
		new MusicPlayer(path);		}
		
	private void initWorld(Graphics g) {
		//System.out.println("am in here boi");

		 List<AbstractAsset> assets = world.getAssetList();
		 List<AbstractAsset> movingAssets = world.getMovingAssets();		 
			for (int i = 0; i < assets.size(); i++) {
				// Get first asset
				asset = assets.get(i);
				movingAsset = movingAssets.get(i);
				// Time for a new row?
				if (pos.x == WIDTH) {
					pos.x = 0;
					pos.y = pos.y + SIZE;
				}
				// Paint all assets
				if(asset.getCoords() != pos) {
					movingAsset.hasDirections(direction);
					asset.paintAsset(g, gamePanel);
					movingAsset.paintAsset(g, gamePanel);
				}
				pos.x = pos.x + SIZE;
			}
			pos.y = 0;
			pos.x = 0;				
	 }
	
	//0 = deathScreen , 1= Winscreen
	private void initDeathOrWinScreen(Graphics g, String path, String Msg) { 
		
		this.slowTime = true;
		ReactionScreen gos = new ReactionScreen(path, Msg);
		gos.render(g);
		this.add(gos);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(GameWindowTemp.isGameState()) 
	
			initWorld(g);
		
		
		else if(GameWindowTemp.isDeathScreenState())
			initDeathOrWinScreen(g,"src/assets/GameOverScreen.jpg", "YOU LOST!");
		
		else if(GameWindowTemp.isWinState() )
			initDeathOrWinScreen(g,"src/assets/WinScreenTemp.jpg", "You Won nice work!");
	}
	
	private void UDPSetup() throws IOException{
		
		serverThread.start();
		clientThread.start();
	
		client.sendMessage("hello");
		client.sendMessage("hello 2nd message");
		client.sendMessage("Känns ju som det här typ funkar :)");
		client.sendMessage("getLeaderboard");
	}
	
	//maybe add what level to lead here as type for the contructor
	public GamePanel(String CurrentLevel) {
		
		
		try {
			UDPSetup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    	//read in and build  level
		this.CurrentLevel = CurrentLevel;	
		world = new ReadInWorld(CurrentLevel);
		world.startControllers();
		
		//set layout
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		this.setVisible(true);
		
		// adding the keylistener
		this.addKeyListener(new keyLis());
		this.setFocusable(true);
		
		//add music
		loadInLevelMusicPaths();
		GamePanel.playMusic(getLevelMusic("level1"));
		
		startTimer(0,1000/60);
		StopWatch.start();
	}
	
	private void setTimeToCompleteGame() {
		
		timeToBeatGame = timeToBeatGame + StopWatch.stopTimer();
		
	}

		
	public void startTimer(int firstTime,int period) {
	   
		this.period = period;
		this.firstTime = firstTime;
		
	    Timer timer = new Timer();
	    
	    timer.schedule(new TimerTask() {
	   
	    	public void run() {
        	
        	if(GameWindowTemp.isRestartState()) {
        		System.out.println("Försöker starta om");
        		world = new ReadInWorld(CurrentLevel);
        		GameWindowTemp.setStateGame();
        		world.startControllers();
        		//reset timer
        		StopWatch.start();
        		System.out.println(GameWindowTemp.state);

        	}
        	
        	if(GameWindowTemp.isNextLevelState()) {
        		
        		//add time it took to beat level!
        		setTimeToCompleteGame();        		
        		
        		System.out.println("loading next level");
        		String nextLevelNumber = CurrentLevel.substring(CurrentLevel.length() - 1);
    			nextLevelNumber= String.valueOf(Integer.parseInt(nextLevelNumber) + 1);
    			CurrentLevel = "level" + nextLevelNumber;
    			
    			//temporary set when beating level 2 you win the game!
    			if(CurrentLevel.equals("level3")) {
    				System.out.println("It took you this many seconds to beat both levels!");
    				System.out.println(StopWatch.stopTimer());
    				String time = Long.toString(StopWatch.stopTimer());
    				try {
						client.sendMessage(time);
					} catch (IOException e) {
						e.printStackTrace();
					}
    				GameWindowTemp.setWinState();
    				
    			}
    			else {
    			world = new ReadInWorld(CurrentLevel);  			
    			GameWindowTemp.setStateGame();
    			world.startControllers();
    			System.out.println("Sätter Gamestate");
    			playMusic(getLevelMusic(CurrentLevel));
    			}
        	}
        	
        	else if(GameWindowTemp.isGameState()) {
        		if(slowTime == true) {
        		timer.cancel();
        		startTimer(0,1000/60);
        		slowTime = false;
        		repaint();
        		}
        		else 
        			repaint();
        		//System.out.println(firstTime);
        	}
        	
        	else if (GameWindowTemp.isDeathScreenState() || GameWindowTemp.isWinState() ) {
        		//System.out.println(firstTime);
        		timer.cancel();
        		startTimer(5000,100000);
        		//System.out.println(firstTime);
        		repaint();	
			}       	
       }
    },this.firstTime ,this.period);
}	
}