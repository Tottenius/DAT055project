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
	
	// Player
	private Player player;	

	public PlayerController(int pos, ReadInWorld world) {
		super(pos, world);
		player = new Player(pos);
		assets.add(player);
		// Kör timerTasken b efter 150ms
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
			// Om skatten inte är öppen, öppna den och öka mängden öppnande kistor
			if( !((Treasure) swapAsset).treasureIsOpen()) {
				openedTreasures++;
				((Treasure) swapAsset).openTreasure();
				// Om du öpnnat alla kistor vinn
				if( openedTreasures == world.numberOfTresures) {
					GameWindowTemp.SetWinState();
				}
			}	
		}
			
		// If enemy, kill player :(
		else if (swapAsset instanceof Enemy) {
			super.dieWhileMovingIntoDanger(oldPlayerPos, newPlayerPos);
			player.setAlive(false);
		}
		// if spikes, die
		else if (swapAsset instanceof Spikes) {
			super.dieWhileMovingIntoDanger(oldPlayerPos, newPlayerPos);
			player.setAlive(false);
		}
	}
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
        public void run() {
        	
        	if(player.isAlive() && GameWindowTemp.isGameState() && GamePanel.isKeyPressed()) {
        		moveDirection(direction);
        		GamePanel.setKeyPressed(false);
			}

        	else if(!GameWindowTemp.isGameState() || !player.isAlive() ) {
        		System.out.println("Stänger av Player");
        		GameWindowTemp.SetDeathScreenState();
        		this.cancel();
        	}
        }
    };
    
	/*
	@Override
    public void run() {
		// Kolla om det går att lösa med en kö. 
        //System.out.println("Startar playertråd");
        while(GameWindowTemp.isGameState() && playerAlive) {
           // System.out.println("kör playertråd");
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