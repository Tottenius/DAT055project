package viewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Controller.EnemyController;
import Controller.PlayerController;
import Controller.SpikeController;
import assetclasses.AbstractAsset;
import assetclasses.Tile;
import assetclasses.Treasure;
import assetclasses.Wall;

public class ReadInWorld {
	
	//Amount of treasures win condition
	public int numberOfTresures = 0;
	
	// list with assets
	private List<AbstractAsset> assets = new ArrayList<AbstractAsset>();
		//list with assets for quick restart
	private List<AbstractAsset> restartAssets = new ArrayList<AbstractAsset>();
	
	public void restartGame() {
		assets = restartAssets;
	}

	// level paths
	String level = "";

	
	// pos int map
	int posInList = 0;
	// pos in enemy list
	int enemyList = 0;
	// pos in player list
	int playerList = 0;
	// pos in spike list
	int spikeList = 0;
	
	// Players
	private List<PlayerController> players = new ArrayList<PlayerController>();
	// Enemys
	private List<EnemyController> enemies = new ArrayList<EnemyController>();
	// Enemy threads
	private List<Thread> enemyThreads = new ArrayList<Thread>();
	// Player threads
	private List<Thread> playerThreads = new ArrayList<Thread>();
	// Spikes
	private List<SpikeController> spikes = new ArrayList<SpikeController>();

	// Symbols
	private AbstractAsset AbstractAsset;
	
	public ReadInWorld(String thisLevel) {
		System.out.println("am inside ReadInWorld CLass Constructor");
		readInlevel(thisLevel);
		restartAssets = assets;
		//startInGameThreads();
	}
	
	public void readInlevel(String path) {
		
		try {
			level = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("JA vi är här");
		// clear previous games if there are any
		//clearAllGameInfo();
		for (int i = 0; i < level.length(); i++) {
			// Load in walls
			if (level.charAt(i) == '#') {
				assets.add(new Wall(posInList));
				posInList++;
			} 
			// Load in tiles
			else if (level.charAt(i) == ' ') {
				assets.add(new Tile(posInList));
				posInList++;
			}
			// Load in closed treasures
			else if (level.charAt(i) == 't') {
				assets.add(new Treasure(posInList));
				numberOfTresures ++;
				posInList++;
			}

			// Load in opened treasures
			else if (level.charAt(i) == 'o') {
				assets.add(new Treasure(posInList));
				posInList++;
			}
			// Load in spikes
			else if (level.charAt(i) == 's') {
				// Make a list of all spikes
				spikes.add(spikeList, new SpikeController(posInList,this));
				// Add threads to all players
				//spikeThreads.add(spikeList, new Thread(spikes.get(spikeList)));
				// Change to next pos in the player list
				spikeList++;
				posInList++;
			
			
			} else if (level.charAt(i) == 'p') {
				// Make a list of all players
				System.out.println("Making a new player therad");
				players.add(playerList, new PlayerController(posInList,this));
				// Add threads to all players
				playerThreads.add(playerList, new Thread(players.get(playerList)));
				// Change to next pos in the player list
				playerList++;
				posInList++;
			}
			// Load in enemies
			else if (level.charAt(i) == 'e') {
				// Make a list of all enemies
				enemies.add(new EnemyController(posInList,this));
				// Add threads to all enemies
				//enemyThreads.add(new Thread(enemies.get(enemyList)));
				// change to next pos in the enemy list
				enemyList++;
				posInList++;
			}
					
		}
	
	}
	
	protected void startInGameThreads() {
		// start player
		for (Thread t : playerThreads) {
			t.start();
		}
		// starts enemies
		for (Thread t : enemyThreads) {
			t.start();
		}


	}
	
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
		//levelRead = false;
	} 
	
	// Reach assets in controller
	public List<AbstractAsset> getAssetList() {
		return assets;
	}

	public void setAssetList(ArrayList<AbstractAsset> assetList) {
		assets = assetList;
	}


}
