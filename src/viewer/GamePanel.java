package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.TimerTask;
import java.util.PrimitiveIterator.OfDouble;
import java.util.Timer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.Direction;
import Controller.PlayerController;
import assetclasses.AbstractAsset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import assetclasses.Treasure;
import assetclasses.Wall;

/*
 Att göra:
 	Allmän interrupt för threads
 	Instanceof FIXED except when restarting level need to look at
 	kordinater 
 	attributes för assets FIXED
 	Testa sätta upp en lokal server (Kolla föreläsningar) 
 */

public class GamePanel extends JPanel {
	//
	public static volatile int numberOfControllers = 0;
	// This window
	GamePanel gamePanel = this;
	// Level read
	public static boolean levelRead = false;
	// Read in level
	private ReadInWorld world;
	//public static ReadInWorld getWorld() {
	//	return world;
	//}
	// Window size
	private static final int WIDTH = GameSettings.getWidth();
	private static final int HEIGHT = GameSettings.getHeight();
	// Size of an AbstractAsset
	private static final int SIZE = GameSettings.getAssetsize();
	// Serial
	private static final long serialVersionUID = 1L;
	// NewKeyPressedBool
	private static volatile boolean isKeyPressed = false;

	public static boolean isKeyPressed() {
		return isKeyPressed;
	}

	public static void setKeyPressed(boolean isKeyPressed) {
		GamePanel.isKeyPressed = isKeyPressed;
	}

	private static Direction direction = Direction.RIGHT;

	// list with assets
	public List<AbstractAsset> assets;

	// Starting position
	private Point pos = new Point(0,0);


	
	// Symbols
	private AbstractAsset asset;
	

	// Clear the threads and assets

	//FIX THIS SO ITS USED
	/*
	public void clearAllGameInfo() {
		// reset game map
		assets.clear();
		// reset player info
		players.clear();
		playerThreads.clear();
		// reset enemy info
		enemies.clear();
		enemyThreads.clear();
		// Reset spikes
		spikes.clear();
		//spikeThreads.clear();
		levelRead = false;
	} 
	*/

	// get direction
	public static Direction getDirection() {
		return direction;
	}
	
	private void initWorld(Graphics g) {
		
		 List<AbstractAsset> assets = world.getAssetList();

		for (int i = 0; i < assets.size(); i++) {
			// assetSymbol = level.charAt(i);
			asset = assets.get(i);
			// Time for a new row?
			if (pos.x == WIDTH) {
				pos.x = 0;
				pos.y = pos.y + SIZE;
			}
			// Load in player
			if (asset.hasDirections() ) {
				asset.getImageAtMap(direction);
				g.drawImage(asset.getImage(), pos.x, pos.y, this);
				pos.x = pos.x + SIZE;
			}
			// Load in enemies
			else {
				g.drawImage(asset.getImage(), pos.x, pos.y, this);
				pos.x = pos.x + SIZE;
			}
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
				// player1.moveDirection(Direction.UP);
				// moveDirection(Direction.UP);

				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:

				System.out.println("Moved Down");
				direction = Direction.DOWN;
				// player1.moveDirection(Direction.DOWN);
				// moveDirection(Direction.DOWN);

				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:

				System.out.println("Moved right");
				direction = Direction.RIGHT;
				// player1.moveDirection(Direction.RIGHT);
				// moveDirection(Direction.RIGHT);

				break;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				System.out.println("Moved Left");
				direction = Direction.LEFT;

				break;

			case KeyEvent.VK_R: // reset level

				// resetLevel(); //Written resetLevel Method

				break;

			case KeyEvent.VK_ESCAPE: // Esc button

				// openMenu(); //Possible method to open menu

				break;

			default:
				break;
			}
			isKeyPressed = true;
		}
	}
	//Levels
	String level1 = "src/levels/level1.txt";
	String level2 = "src/levels/level2.txt";
	
	public GamePanel() {
		System.out.println("Vi går in i gamepanel");
		System.out.println(GameWindowTemp.state+ " GamePanel");
		// Read in new world
		world = new ReadInWorld(level2);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		this.setVisible(true);
		// adding the keylistener
		this.addKeyListener(new keyLis());
		this.setFocusable(true);
		// Kör timerTasken b 60 gånger per sek. Just nu repaint och kolla om vi har dött
		b.scheduleAtFixedRate(c, 0, 1000/60);
		//world.startInGameThreads();

	}
	
	// Vi kör en timer istället för en busy wait
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
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
        	
        	else if(GameWindowTemp.isGameState()) {
	            repaint();				
        	}
        	// Att vinna ger just nu game over screen
        	else if ( GameWindowTemp.isWinState()) {
        		GameWindowTemp.setDeathScreenState();
				SwingUtilities.getWindowAncestor(gamePanel).dispose();
				new GameWindowTemp();
				this.cancel();
        	}
        	// Gör en gameoverskärm om player är död
        	else if (GameWindowTemp.isDeathScreenState()) {
				SwingUtilities.getWindowAncestor(gamePanel).dispose();
				new GameWindowTemp();
        		this.cancel();
				
			}
        	
        }
    };

}