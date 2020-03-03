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
	
	//Amount of treasures win condition
	public static int numberOfTresures = 0;
	
	// list with assets
	private List<AbstractAsset> assets = new ArrayList<AbstractAsset>();
	private List<AbstractAsset> movingAssets = new ArrayList<AbstractAsset>();
		//list with assets for quick restart
	private List<AbstractAsset> restartAssets = new ArrayList<AbstractAsset>();
	
	HashMap<String, String> levels = new HashMap<String, String>();
	String nextLevel;

	public void initLevelPaths() {
		
		levels.put("level1","src/levels/level1.txt");
		levels.put("level2","src/levels/level2.txt");
		levels.put("level3","src/levels/level3.txt");
		levels.put("level4","src/levels/level4.txt");
	}
	

	// DETTA ÄR SKIT MANNEN FIXA! TACK PELLE
	public void restartGame() {
		assets = restartAssets;
		System.out.println(assets);
		for (int i = 0; i < assets.size(); i++) {
			if(restartAssets.get(i) instanceof Enemy) {
				assets.remove(i);
				new EnemyController(i,this);
			}
			else if(restartAssets.get(i) instanceof Spikes) {
				assets.remove(i);
				new SpikeController(i,this);
			}
			else if(restartAssets.get(i) instanceof Player) {
				assets.remove(i);
				new PlayerController(i,this);
			}
			
			
		}
		System.out.println("skrivit ut de nya controllersen");
	}

	// level paths
	String level = "";
	// pos int map
	int posInList = 0;	
	
	//Asset list
	private List<AssetController> assetContollers = new ArrayList<AssetController>();

	// Symbols
	//private AbstractAsset AbstractAsset;
	
	public ReadInWorld(String thisLevel) {
		 numberOfTresures = 0;
		 Treasure.setOpenedTreasures(0);
		initLevelPaths();
		System.out.println("am inside ReadInWorld Class Constructor");
		readInlevel(thisLevel);
		System.out.println(restartAssets);
		restartAssets = assets;
		System.out.println(restartAssets);
	}
	
	public void readInlevel(String path) {
		//Read in the level
		try {
			level = new String(Files.readAllBytes(Paths.get(levels.get(path))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < level.length(); i++) {
			// Load in walls
			if (level.charAt(i) == '#') {
				movingAssets.add(new Empty(posInList));
				assets.add(new Wall(posInList));
				posInList++;
			} 
			// Load in tiles
			else if (level.charAt(i) == ' ') {
				movingAssets.add(new Empty(posInList));
				assets.add(new Tile(posInList));
				posInList++;
			}
			// Load in closed treasures
			else if (level.charAt(i) == 't') {
				movingAssets.add(new Empty(posInList));
				assets.add(new Treasure(posInList));
				numberOfTresures ++;
				posInList++;
			}

			// Load in opened treasures
			else if (level.charAt(i) == 'o') {
				movingAssets.add(new Empty(posInList));
				assets.add(new Treasure(posInList));
				posInList++;
			}
			
			// Load in door
			else if (level.charAt(i) == 'd') {
				movingAssets.add(new Empty(posInList));
				assets.add(new Door(posInList));
				posInList++;
			}
			
			
			// Load in spikes
			else if (level.charAt(i) == 's') {
				// Make a list of all spikes
				movingAssets.add(new Empty(posInList));
				assetContollers.add( new SpikeController(posInList,this));
				posInList++;
			
			
			} else if (level.charAt(i) == 'p') {
				assets.add(new Tile(posInList));
				assetContollers.add( new PlayerController(posInList,this));

				posInList++;
			}
			// Load in enemies
			else if (level.charAt(i) == 'e') {
				// Make a list of all enemies
				assets.add(new Tile(posInList));
				assetContollers.add(new EnemyController(posInList,this));
				posInList++;
			}					
		}	
	}
	// Starta controllers
	public void startControllers() {
		for (AssetController c : assetContollers) {
			c.startController();
		}
	}
	
	// Reach assets in controller
	public List<AbstractAsset> getAssetList() {
		return assets;
	}

	public void setAssetList(ArrayList<AbstractAsset> assetList) {
		assets = assetList;
	}

	public List<AbstractAsset> getMovingAssets() {
		return movingAssets;
	}

	public void setMovingAssets(List<AbstractAsset> movingAssets) {
		this.movingAssets = movingAssets;
	}
}