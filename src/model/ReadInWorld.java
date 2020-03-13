package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import Controller.AssetController;
import Controller.EnemyController;
import Controller.PlayerController;
import Controller.SpikeController;
import model.AbstractAsset;
import model.Empty;
import model.Door;
import model.Enemy;
import model.Player;
import model.Spikes;
import model.Tile;
import model.Treasure;
import model.Wall;

/**
 * Creates a game world based on the level chosen in the constructor of the
 * class
 *
 * @author Group 10
 * @version 2020-03-06
 *
 */
public class ReadInWorld {

	// Amount of treasures win condition
	/**
	 * The amount of treasures that has to be collected to finish the current level
	 */
	public static int numberOfTresures = 0;
	private String currentSavedLevel = "";
	private int attempts;
	private long time;

	// list with assets
	private List<AbstractAsset> assets = new ArrayList<>();
	private List<AbstractAsset> movingAssets = new ArrayList<>();
	// list with assets for quick restart
	private List<AbstractAsset> restartAssets = new ArrayList<>();

	HashMap<String, String> levels = new HashMap<>();
	String nextLevel;

	private void initLevelPaths() {

		this.levels.put("level1", "src/levels/level1.txt");
		this.levels.put("level2", "src/levels/level2.txt");
		this.levels.put("level3", "src/levels/level3.txt");
		this.levels.put("level4", "src/levels/level4.txt");
		this.levels.put("level5", "src/levels/level5.txt");
		this.levels.put("level6", "src/levels/level6.txt");
		this.levels.put("level7", "src/levels/level7.txt");
		this.levels.put("level8", "src/levels/level8.txt");
		this.levels.put("level9", "src/levels/level9.txt");
		this.levels.put("level10", "src/levels/level10.txt");
		this.levels.put("saveLevel", "src/levels/saveLevel.txt");
	}

	/**
	 * Restarts the current level
	 */
	@SuppressWarnings("unused")
	public void restartGame() {
		this.assets = this.restartAssets;
		for (int i = 0; i < this.assets.size(); i++) {
			if (this.restartAssets.get(i) instanceof Enemy) {
				this.assets.remove(i);
				new EnemyController(i, this);
			} else if (this.restartAssets.get(i) instanceof Spikes) {
				this.assets.remove(i);
				new SpikeController(i, this);
			} else if (this.restartAssets.get(i) instanceof Player) {
				this.assets.remove(i);
				new PlayerController(i, this);
			}

		}
	}

	// level paths
	String level = "";
	// pos int map
	int posInList = 0;

	// Asset list
	private final List<AssetController> assetContollers = new ArrayList<>();

	// Symbols
	// private AbstractAsset AbstractAsset;
	/**
	 * Creates a world based on the level chosen in the argument
	 * 
	 * @param thisLevel A key for the hashmap containing level information
	 * 
	 */
	public ReadInWorld(final String thisLevel) {
		numberOfTresures = 0;
		Treasure.setOpenedTreasures(0);
		initLevelPaths();
		readInlevel(thisLevel);
		this.restartAssets = this.assets;
	}

	private void readInlevel(final String path) {
		// Read in the level
		try {
			this.level = new String(Files.readAllBytes(Paths.get(this.levels.get(path))));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < this.level.length(); i++) {
			// Load in walls
			if (this.level.charAt(i) == '#') {
				this.movingAssets.add(new Empty(this.posInList));
				this.assets.add(new Wall(this.posInList));
				this.posInList++;
			}
			// Load in tiles
			else if (this.level.charAt(i) == ' ') {
				this.movingAssets.add(new Empty(this.posInList));
				this.assets.add(new Tile(this.posInList));
				this.posInList++;
			}
			// Load in closed treasures
			else if (this.level.charAt(i) == 't') {
				this.movingAssets.add(new Empty(this.posInList));
				this.assets.add(new Treasure(this.posInList));
				numberOfTresures++;
				this.posInList++;
			}

			// Load in opened treasures
			else if (this.level.charAt(i) == 'o') {
				this.movingAssets.add(new Empty(this.posInList));
				this.assets.add(new Treasure(this.posInList));
				this.posInList++;
			}

			// Load in door
			else if (this.level.charAt(i) == 'd') {
				this.movingAssets.add(new Empty(this.posInList));
				this.assets.add(new Door(this.posInList));
				this.posInList++;
			}

			// Load in spikes
			else if (this.level.charAt(i) == 's') {
				// Make a list of all spikes
				this.movingAssets.add(new Empty(this.posInList));
				this.assetContollers.add(new SpikeController(this.posInList, this));
				this.posInList++;

			} else if (this.level.charAt(i) == 'p') {
				this.assets.add(new Tile(this.posInList));
				this.assetContollers.add(new PlayerController(this.posInList, this));

				this.posInList++;
			}
			// Load in enemies
			else if (this.level.charAt(i) == 'e') {
				// Make a list of all enemies
				this.assets.add(new Tile(this.posInList));
				this.assetContollers.add(new EnemyController(this.posInList, this));
				this.posInList++;
			}
		}

		if (path == "saveLevel") {
			FileInputStream saveGameData = null;
			String s;
			try {
				saveGameData = new FileInputStream("src/levels/saveLevelData.txt");
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			}
			try (Scanner sc = new Scanner(saveGameData)) {

				// Save the save level
				s = sc.nextLine();
				this.currentSavedLevel = s;
				// Number of attempts
				this.attempts = sc.nextInt();
				// Save the time
				this.time = sc.nextLong();

				while (sc.hasNextLine()) {
					if (sc.hasNextInt()) {
						this.assets.get(sc.nextInt()).intractable();
					} else {
						sc.nextLine();
					}

				}
			}
		}
	}

	/**
	 * Saves the current game
	 * 
	 * @param s The current level
	 */
	// Save a level to two files one for the positions and one for the data
	public void saveLevel(final String s, final long currentTime, final int attempts) {
		int pos = 0;
		// For the level layout
		String saveLevel = "";
		final StringBuilder saveLevelBuilder = new StringBuilder(this.assets.size());
		// For the level data
		String saveLevelData = "";
		final StringBuilder saveLevelDataBuilder = new StringBuilder();
		// Add level that is saved for music etc
		saveLevelDataBuilder.append(s);
		saveLevelDataBuilder.append('\n');
		// Attempts
		saveLevelDataBuilder.append(attempts);
		saveLevelDataBuilder.append('\n');
		// Game time
		saveLevelDataBuilder.append(currentTime);
		saveLevelDataBuilder.append('\n');

		// Empty the old savefiles
		PrintWriter writerData;
		PrintWriter writerLevel;
		try {
			writerData = new PrintWriter("src/levels/saveLevelData.txt");
			writerData.print("");
			writerData.close();
			writerLevel = new PrintWriter("src/levels/saveLevel.txt");
			writerLevel.print("");
			writerLevel.close();
		} catch (final FileNotFoundException e1) {
			e1.printStackTrace();
		}

		// Save stationary assets at the right places
		for (final AbstractAsset a : this.assets) {
			if (a instanceof Wall) {
				saveLevelBuilder.append('#');
			} else if (a instanceof Tile) {
				saveLevelBuilder.append(' ');
			} else if (a instanceof Spikes) {
				saveLevelBuilder.append('s');
			} else if (a instanceof Door) {
				saveLevelBuilder.append('d');
			} else if (a instanceof Treasure) {
				saveLevelBuilder.append('t');
				if (((Treasure) a).treasureIsOpen()) {
					saveLevelDataBuilder.append(pos);
					saveLevelDataBuilder.append('\n');
				}
			} else if (a instanceof Empty) {
				saveLevelBuilder.append(' ');
			}
			pos++;
		}
		pos = 0;
		// Save moving assets at the right places
		for (final AbstractAsset a : this.movingAssets) {
			if (a instanceof Enemy) {
				saveLevelBuilder.deleteCharAt(pos);
				saveLevelBuilder.insert(pos, 'e');
			} else if (a.killable()) {
				saveLevelBuilder.deleteCharAt(pos);
				saveLevelBuilder.insert(pos, 'p');
			}
			pos++;
		}
		saveLevel = saveLevelBuilder.toString();
		saveLevelData = saveLevelDataBuilder.toString();

		// Write the level to a file
		try (PrintWriter out = new PrintWriter("src/levels/saveLevel.txt")) {
			out.println(saveLevel);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		// Write the data to a file
		try (PrintWriter out = new PrintWriter("src/levels/saveLevelData.txt")) {
			out.println(saveLevelData);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Starta controllers
	/**
	 * Starts the AssetControllers in the world
	 */
	public void startControllers() {
		for (final AssetController c : this.assetContollers) {
			c.startController();
		}
	}

	// Reach assets in controller
	/**
	 * A getter for the list of stationary assets
	 * 
	 * @return Returns the levels stationary assets in an ArrayList
	 */
	public List<AbstractAsset> getAssetList() {
		return this.assets;
	}

	/**
	 * Sets the worlds current stationary assets list to another list containing
	 * stationary assets
	 * 
	 * @param assetList A list of new stationary assets for the world
	 */
	public void setAssetList(final ArrayList<AbstractAsset> assetList) {
		this.assets = assetList;
	}

	/**
	 * A getter for the list of moving assets
	 * 
	 * @return Returns the levels moving assets in an ArrayList
	 */
	public List<AbstractAsset> getMovingAssets() {
		return this.movingAssets;
	}

	/**
	 * Sets the worlds current moving assets list to another list containing moving
	 * assets
	 * 
	 * @param movingAssets A list of new moving assets for the world
	 */
	public void setMovingAssets(final List<AbstractAsset> movingAssets) {
		this.movingAssets = movingAssets;
	}

	/**
	 * Gets the saved levels level number
	 * 
	 * @return Returns the saved levels level
	 */
	public String getCurrentSavedLevel() {
		return this.currentSavedLevel;
	}

	/**
	 * Gives the saved games time
	 * 
	 * @return Time taken until game was saved
	 */
	public long getTime() {
		return this.time;
	}

	/**
	 * Gives the saved games time
	 * 
	 * @return Time taken until game was saved
	 */
	public int getAttempts() {
		return this.attempts;
	}
}