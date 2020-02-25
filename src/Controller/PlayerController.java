package Controller;

import java.util.Timer;
import java.util.TimerTask;

import assetclasses.AbstractAsset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import assetclasses.Treasure;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

public class PlayerController extends AssetController  {
	//Number of opened treasures
	private int openedTreasures = 0;

	//private static ArrayList<Asset> assets = GamePanel.getAssetList();
	private Player player;	
	private static boolean playerAlive;

	
	public static void playerDead() {
		playerAlive = false;
		//((Player) assets.get(super.getPosition())).setAlive(false);
		GameWindowTemp.SetDeathScreenState();
	}
	
	public static boolean isPlayerAlive() {
		return playerAlive;
	}
	
	public PlayerController(int pos, ReadInWorld world) {
		super(pos, world);
		player = new Player(pos);
		assets.add(player);
		playerAlive = true;
		// K�r timerTasken b efter 150ms
		b.scheduleAtFixedRate(c, 1000, 150);
	}

	public void moveDirection( Direction direction) {
		
		// Right now just player pos
		int oldPlayerPos = super.getPosition();
		//System.out.println(oldPlayerPos);
		//System.out.println(direction);
		AbstractAsset movingAsset = assets.get(oldPlayerPos);
		AbstractAsset swapAsset = null;
		 
		int newPlayerPos = oldPlayerPos + GamePanel.getDirection().getXDelta() +  GamePanel.getDirection().getYDelta();
		
		// Alla interaktioner med assets
		swapAsset = assets.get(newPlayerPos);
		// If tile, move to the tile
		if (swapAsset instanceof Tile) {
			super.moveAsset(newPlayerPos, oldPlayerPos, movingAsset, swapAsset);
		}
		// If treasure, open treasure
		else if (swapAsset instanceof Treasure) {
			// Om skatten inte �r �ppen, �ppna den och �ka m�ngden �ppnande kistor
			if( !((Treasure) swapAsset).treasureIsOpen()) {
				openedTreasures++;
				((Treasure) swapAsset).openTreasure();
				// Om du �pnnat alla kistor vinn
				if( openedTreasures == world.numberOfTresures) {
					GameWindowTemp.SetWinState();
				}
			}	
		}
			
		// If enemy, kill player :(
		else if (swapAsset instanceof Enemy) {
			super.dieWhileMovingIntoDanger(oldPlayerPos, newPlayerPos);
			playerDead();
		}
		// if spikes, die
		else if (swapAsset instanceof Spikes) {
			super.dieWhileMovingIntoDanger(oldPlayerPos, newPlayerPos);
			playerDead();
		}
	}
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
        public void run() {
        	
        	if(GameWindowTemp.isGameState() && GamePanel.isKeyPressed()) {
        		moveDirection(direction);
        		GamePanel.setKeyPressed(false);
			}

        	else if(!GameWindowTemp.isGameState() ) {
        		System.out.println("St�nger av Player");
        		this.cancel();
        	}
        }
    };
    
	/*
	@Override
    public void run() {
		// Kolla om det g�r att l�sa med en k�. 
        //System.out.println("Startar playertr�d");
        while(GameWindowTemp.isGameState() && playerAlive) {
           // System.out.println("k�r playertr�d");
            if(GamePanel.isKeyPressed()) {
                moveDirection(direction);
    			try {
    				Thread.sleep(150);
    			} catch (InterruptedException e) {
    				Thread.currentThread().interrupt();
    				break;
    			}	
                GamePanel.setKeyPressed(false);
            }

        }
    }
    */
}