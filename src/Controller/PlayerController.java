package Controller;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import assetclasses.AbstractAsset;
import assetclasses.Player;
import viewer.GamePanel;
import viewer.GameSettings;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

public class PlayerController extends AssetController  {
	
	// Player
	private Player player;	

	public PlayerController(int pos, ReadInWorld world) {
		super(pos, world);
		player = new Player(pos);
		assets.add(pos, player);
		GamePanel.numberOfControllers ++;
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
		if (swapAsset.canWalkOn()) {
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
        		player.setCoords( position);
        		System.out.println(player.getCoords());
        		GamePanel.setKeyPressed(false);
        	
			}
        	else if (!GameWindowTemp.isGameState()){
        		System.out.println("Stänger av Player vi går till main menu");
        		GamePanel.numberOfControllers --;
        		this.cancel();
        	}
        	else if(!player.isAlive() ) {
        		System.out.println("Stänger av Player player död");
        		GameWindowTemp.setDeathScreenState();
        		GamePanel.numberOfControllers --;
        		this.cancel();
        	}
        }
    };
}