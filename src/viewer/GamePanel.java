package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.Direction;
import Controller.EnemyController;
import Controller.PlayerController;
import assetclasses.AbstractAsset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import assetclasses.Treasure;
import assetclasses.Wall;
import main.Main;

public class GamePanel extends JPanel implements Runnable {

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
	private static List<AbstractAsset> assets = new ArrayList<AbstractAsset>();

	// Starting position
	private int x = 0;
	private int y = 0;

	// Symbols
	private AbstractAsset AbstractAsset;
	// level paths
	String level = "";
	String level1 = "src/levels/level2.txt";

	// Players
	private ArrayList<PlayerController> players = new ArrayList<PlayerController>();
	// Enemys
	private ArrayList<EnemyController> enemies = new ArrayList<EnemyController>();
	// Enemy threads
	private ArrayList<Thread> enemyThreads = new ArrayList<Thread>();
	// Player threads
	private ArrayList<Thread> playerThreads = new ArrayList<Thread>();

	// Clear the threads and assets
	public void clearAllGameInfo() {
		// reset game map
		assets.clear();
		// reset player info
		players.clear();
		// playerThreads.clear();
		// reset enemy info
		enemies.clear();
		enemyThreads.clear();
	}

	// Reach assets in controller
	public static List<AbstractAsset> getAssetList() {
		return assets;
	}

	public static void setAssetList(ArrayList<AbstractAsset> assetList) {
		assets = assetList;
	}

	// get direction
	public static Direction getDirection() {
		return direction;
	}

	private void startInGameThreads() {
		// start player
		for (Thread t : playerThreads) {
			t.start();
		}
		// starts enemies
		for (Thread t : enemyThreads) {
			t.start();
		}
	}

	public void readInlevel(String path) {
		// pos int map
		int posInList = 0;
		// pos in enemy list
		int enemyList = 0;
		// pos in player list
		int playerList = 0;
		// System.out.println("current working directory is: " +
		// System.getProperty("user.dir"));
		try {
			level = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// clear previous games if there are any
		clearAllGameInfo();
		for (int i = 0; i < level.length(); i++) {

			if (level.charAt(i) == '#') {
				assets.add(new Wall(posInList));
				posInList++;
			} else if (level.charAt(i) == ' ') {
				assets.add(new Tile(posInList));
				posInList++;
			}
			// Load in closed treasures
			else if (level.charAt(i) == 't') {
				assets.add(new Treasure(posInList));
				posInList++;
			}

			// Load in opened treasures
			else if (level.charAt(i) == 'o') {
				assets.add(new Treasure(posInList));
				posInList++;
			}
			// Load in spikes
			else if (level.charAt(i) == 's') {
				assets.add(new Spikes(posInList));
				posInList++;
			} else if (level.charAt(i) == 'p') {
				// Make a list of all players
				System.out.println("Making a new player therad");
				players.add(playerList, new PlayerController(posInList));
				// Add threads to all players
				playerThreads.add(playerList, new Thread(players.get(playerList)));
				// Change to next pos in the player list
				playerList++;
				posInList++;
			}
			// Load in enemies
			else if (level.charAt(i) == 'e') {
				// Make a list of all enemies
				enemies.add(new EnemyController(posInList));
				// Add threads to all enemies
				enemyThreads.add(new Thread(enemies.get(enemyList)));
				// change to next pos in the enemy list
				enemyList++;
				posInList++;
			}
		}
		// Add enemies to the map
		// for (EnemyController e : enemies) {

		// }
	}

	private void initWorld(Graphics g) {
		// System.out.println("Hej initWorld");

		for (int i = 0; i < assets.size(); i++) {
			// System.out.println(level.charAt(i));
			// assetSymbol = level.charAt(i);
			AbstractAsset = assets.get(i);
			// Time for a new row?
			if (x == WIDTH) {
				x = 0;
				y = y + SIZE;
			}
			// Load in player
			if (AbstractAsset instanceof Player ) {
				AbstractAsset.getImageAtMap(direction);
				g.drawImage(AbstractAsset.getImage(), x, y, this);
				// System.out.println("vi f�rs�ker g�ra en gubbe");
				x = x + SIZE;
			}
			// Load in enemies
			if (AbstractAsset instanceof Enemy) {
				// System.out.println("Vi f�rs�ker g�ra en enemy");
				g.drawImage(AbstractAsset.getImage(), x, y, this);
				x = x + SIZE;
			}
			if (AbstractAsset instanceof Spikes) {
				// System.out.println("Vi f�rs�ker g�ra en enemy");
				g.drawImage(AbstractAsset.getImage(), x, y, this);
				x = x + SIZE;
			}
			// Load in wall assets
			if (AbstractAsset instanceof Wall) {
				// System.out.println("Vi f�rs�ker g�ra en wall");
				g.drawImage(AbstractAsset.getImage(), x, y, this);
				x = x + SIZE;
			}
			// Load in tile assets
			else if (AbstractAsset instanceof Tile) {
				g.drawImage(AbstractAsset.getImage(), x, y, this);
				x = x + SIZE;
			}
			// Load in treasures
			else if (AbstractAsset instanceof Treasure) {

				g.drawImage(AbstractAsset.getImage(), x, y, this);
				x = x + SIZE;
			}
		}
		y = 0;
		x = 0;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		initWorld(g);
	}

	private class keyLis extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int input = e.getKeyCode();
			// System.out.println("vi f�rs�ker m�la om");
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

	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(null);
		System.out.println("L�ser in leveln");
		// System.out.println(level);
		readInlevel(level1);
		System.out.println(level);
		this.setVisible(true);
		// adding the keylistener
		this.addKeyListener(new keyLis());
		this.setFocusable(true);
		this.revalidate();
		startInGameThreads();

	}

	@Override
	public void run() {
		while (Main.isRunning && GameWindowTemp.isGameState()) {
			repaint();
			// Repaint at 60 fps
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// G�r en gameoversk�rm om player �r d�d
			if (!PlayerController.isPlayerAlive()) {
			
				SwingUtilities.getWindowAncestor(this).dispose();
				new GameWindowTemp();
			}
		}
	}
}