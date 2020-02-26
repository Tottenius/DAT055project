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
	//private int openedTreasures = 0;
	
	// Player
	private Player player;	

	public PlayerController(int pos, ReadInWorld world) {
		super(pos, world);
		player = new Player(pos);
		assets.add(pos, player);
		GamePanel.numberOfControllers ++;
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
		else if (swapAsset.intractable()) {
			//hej
			//System.out.println("tjo");
		}
			
		// If enemy, kill player :(
		else if (swapAsset.canKill()) {
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
        	else if (!GameWindowTemp.isGameState()){
        		System.out.println("St�nger av Player vi g�r till main menu");
        		GamePanel.numberOfControllers --;
        		this.cancel();
        	}
        	else if(!player.isAlive() ) {
        		System.out.println("St�nger av Player player d�d");
        		GameWindowTemp.setDeathScreenState();
        		GamePanel.numberOfControllers --;
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