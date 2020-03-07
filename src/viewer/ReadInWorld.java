package viewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Controller.AssetController;
import Controller.EnemyController;
import Controller.PlayerController;
import Controller.SpikeController;
import assetclasses.AbstractAsset;

import assetclasses.Empty;

import assetclasses.Door;

import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import assetclasses.Treasure;
import assetclasses.Wall;

/**
 * Creates a game world based on the level chosen in the constructor of the class
 * 
 * @author Group 10
 * @version 2020-03-06
 *
 */
public class ReadInWorld {

	// Amount of treasures win condition
	/**
	 * The amount of treasures that has to be collected to finnish the current level
	 */
	public static int numberOfTresures = 0;

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
 * @param thisLevel
 * A key for the hashmap containing level information
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
	 * @return
	 * Returns the levels stationary assets in an ArrayList
	 */
	public List<AbstractAsset> getAssetList() {
		return this.assets;
	}
	
	/**
	 * Sets the worlds current stationary assets list to another list containing stationary assets
	 * @param assetList
	 * A list of new stationary assets for the world
	 */
	public void setAssetList(final ArrayList<AbstractAsset> assetList) {
		this.assets = assetList;
	}
	/**
	 * A getter for the list of moving assets
	 * @return
	 * Returns the levels moving assets in an ArrayList
	 */
	public List<AbstractAsset> getMovingAssets() {
		return this.movingAssets;
	}
	/**
	 * Sets the worlds current moving assets list to another list containing moving assets
	 * @param movingAssets
	 *  A list of new moving assets for the world
	 */
	public void setMovingAssets(final List<AbstractAsset> movingAssets) {
		this.movingAssets = movingAssets;
	}
}