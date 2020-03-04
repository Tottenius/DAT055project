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

public class ReadInWorld {

	// Amount of treasures win condition
	public static int numberOfTresures = 0;

	// list with assets
	private List<AbstractAsset> assets = new ArrayList<>();
	private List<AbstractAsset> movingAssets = new ArrayList<>();
	// list with assets for quick restart
	private List<AbstractAsset> restartAssets = new ArrayList<>();

	HashMap<String, String> levels = new HashMap<>();
	String nextLevel;

	public void initLevelPaths() {

		this.levels.put("level1", "src/levels/level1.txt");
		this.levels.put("level2", "src/levels/level2.txt");
		this.levels.put("level3", "src/levels/level3.txt");
		this.levels.put("level4", "src/levels/level4.txt");
		this.levels.put("level5", "src/levels/level5.txt");
	}

	// DETTA ÄR SKIT MANNEN FIXA! TACK PELLE
	@SuppressWarnings("unused")
	public void restartGame() {
		this.assets = this.restartAssets;
		System.out.println(this.assets);
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
		System.out.println("skrivit ut de nya controllersen");
	}

	// level paths
	String level = "";
	// pos int map
	int posInList = 0;

	// Asset list
	private final List<AssetController> assetContollers = new ArrayList<>();

	// Symbols
	// private AbstractAsset AbstractAsset;

	public ReadInWorld(final String thisLevel) {
		numberOfTresures = 0;
		Treasure.setOpenedTreasures(0);
		initLevelPaths();
		System.out.println("am inside ReadInWorld Class Constructor");
		readInlevel(thisLevel);
		System.out.println(this.restartAssets);
		this.restartAssets = this.assets;
		System.out.println(this.restartAssets);
	}

	public void readInlevel(final String path) {
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
	public void startControllers() {
		for (final AssetController c : this.assetContollers) {
			c.startController();
		}
	}

	// Reach assets in controller
	public List<AbstractAsset> getAssetList() {
		return this.assets;
	}

	public void setAssetList(final ArrayList<AbstractAsset> assetList) {
		this.assets = assetList;
	}

	public List<AbstractAsset> getMovingAssets() {
		return this.movingAssets;
	}

	public void setMovingAssets(final List<AbstractAsset> movingAssets) {
		this.movingAssets = movingAssets;
	}
}