package Controller;

import java.util.ArrayList;
import assetclasses.Asset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import assetclasses.Treasure;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.GamePanel.Direction;

public class PlayerController extends AssetController implements Runnable  {

	private static ArrayList<Asset> assets = GamePanel.getAssetList();
	private Player player;	
	private static boolean playerAlive;

	
	public static void playerDead() {
		playerAlive = false;
		GameWindowTemp.SetDeathScreenState();
	}
	
	public static boolean isPlayerAlive() {
		return playerAlive;
	}
	
	public PlayerController(int pos) {
		super(pos);
		player = new Player(pos);
		assets.add(player);
		playerAlive = true;
	}

	public void moveDirection( Direction direction) {
		
		// Right now just player pos
		int oldPlayerPos = super.getPosition();
		//System.out.println(oldPlayerPos);
		//System.out.println(direction);
		Asset movingAsset = assets.get(oldPlayerPos);
		Asset swapAsset = null;
		 
		int newPlayerPos = oldPlayerPos + GamePanel.getDirection().getXDelta() +  GamePanel.getDirection().getYDelta();
		
		// Alla interaktioner med assets
		swapAsset = assets.get(newPlayerPos);
		// If tile, move to the tile
		if (swapAsset instanceof Tile) {
			super.moveAsset(newPlayerPos, oldPlayerPos, movingAsset, swapAsset);
		}
		// If treasure, open treasure
		else if (swapAsset instanceof Treasure) {
			((Treasure) swapAsset).openTreasure();
		}
		// If enemy, kill player :(
		else if (swapAsset instanceof Enemy) {
			super.dieWhileMovingIntoDanger(oldPlayerPos, newPlayerPos);
			//assets.set(oldPlayerPos, new Tile(oldPlayerPos) ).setPosition(newPlayerPos);
			playerDead();
		}
		// if spikes, die
		else if (swapAsset instanceof Spikes) {
			super.dieWhileMovingIntoDanger(oldPlayerPos, newPlayerPos);
		}
	}
	
	@Override
    public void run() {
        System.out.println("Startar playertråd");
        while(GameWindowTemp.isGameState() && playerAlive) {
            System.out.println("kör playertråd");
            if(GamePanel.isKeyPressed()) {
                moveDirection(direction);
    			try {
    				Thread.sleep(150);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}	
                GamePanel.setKeyPressed(false);
            }

        }
    }
}