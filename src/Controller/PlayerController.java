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
		movingAssets.add(pos, player);
		GamePanel.numberOfControllers ++;
		// Kör timerTasken b efter 150ms
		//startController();
	}

	public void moveDirection( Direction direction) {
		
		// Right now just player pos
		int oldPlayerPos = super.getPosition();
		//System.out.println(oldPlayerPos);
		//System.out.println(direction);
		AbstractAsset playerAsset = movingAssets.get(oldPlayerPos);
		AbstractAsset newPlayerLocationMovingLayer = null;
		AbstractAsset stationaryAsset = null;
		 
		int newPlayerPos = oldPlayerPos + GamePanel.getDirection().getXDelta() +  GamePanel.getDirection().getYDelta();
		
		// Alla interaktioner med assets
		newPlayerLocationMovingLayer = movingAssets.get(newPlayerPos);
		stationaryAsset = assets.get(newPlayerPos);
		// If tile, move to the tile
		if (stationaryAsset.canWalkOn() && newPlayerLocationMovingLayer.canWalkOn()) {
			super.moveAsset(newPlayerPos, oldPlayerPos, playerAsset, newPlayerLocationMovingLayer);
		}
		// If treasure, open treasure
		else if (stationaryAsset.intractable()) {
			//hej
			//System.out.println("tjo");
		}
			
		// If enemy, kill player :(
		else if (newPlayerLocationMovingLayer.canKill() || stationaryAsset.canKill() ) {
			super.dieWhileMovingIntoDanger(oldPlayerPos, newPlayerPos);
			player.setAlive(false);
		}
	}
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
        public void run() {
        	
        	if(player.isAlive() && GameWindowTemp.isGameState() && GamePanel.isKeyPressed()) {
        		moveDirection(direction);
        		//player.setCoords( position);
        		player.setPrevPos();
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

	@Override
	public void startController() {
		b.scheduleAtFixedRate(c, 1000, 150);
		
	}
}