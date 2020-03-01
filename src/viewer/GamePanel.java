package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Controller.Direction;
import assetclasses.AbstractAsset;

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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		initWorld(g);
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
		}		
		else
		new MusicPlayer(path);		
	}
	
	//maybe add what level to lead here as type for the contructor
	public GamePanel(String CurrentLevel) {
		
	    	//read in and build  level
		this.CurrentLevel = CurrentLevel;	
		world = new ReadInWorld(CurrentLevel);
		
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

		// Kör timerTasken b 60 gånger per sek. Just nu repaint och kolla om vi har dött
		timer1.scheduleAtFixedRate(timer2, 0, 1000/60);
		//world.startInGameThreads();
		StopWatch.start();
	}
	
	// Vi kör en timer istället för en busy wait
	Timer timer1 = new Timer();
	TimerTask timer2 = new TimerTask() {
      
	    public void run() {
        	
        	if(GameWindowTemp.isRestartState()) {
        		System.out.println("Försöker starta om");
        		while(!(numberOfControllers == 0)) {
        			// wait 
        			//System.out.println(numberOfControllers);
        		}
        		System.out.println("Väntat klart");
        		GameWindowTemp.setStateGame();
        		System.out.println(GameWindowTemp.state);
        		world.restartGame();
        	}
        	
        	if(GameWindowTemp.isNextLevelState()) {
	
        		System.out.println("loading next level");
     
        		String nextLevelNumber = CurrentLevel.substring(CurrentLevel.length() - 1);
    			nextLevelNumber= String.valueOf(Integer.parseInt(nextLevelNumber) + 1);
    			CurrentLevel = "level" + nextLevelNumber;
        		
    			world = new ReadInWorld(CurrentLevel); 			
    			GameWindowTemp.setStateGame();
    			playMusic(getLevelMusic(CurrentLevel));

        	}
        	
        	else if(GameWindowTemp.isGameState()) {
        	
        		repaint();
        	}	

        	else if ( GameWindowTemp.isWinState()) {
				SwingUtilities.getWindowAncestor(gamePanel).dispose();
				new GameWindowTemp(null);
				this.cancel();
        	}
        	// Gör en gameoverskärm om player är död
        	else if (GameWindowTemp.isDeathScreenState()) {
				SwingUtilities.getWindowAncestor(gamePanel).dispose();
				new GameWindowTemp(null);
				this.cancel();		
			}
        }
    };
}