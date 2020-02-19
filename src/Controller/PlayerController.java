package Controller;

import java.util.ArrayList;
import assetclasses.Asset;
import assetclasses.Enemy;
import assetclasses.Player;
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
		if (swapAsset instanceof Tile) {
			// S�tt playerns positon till den nya positionen och ge objectet den nya positionen
			assets.set(newPlayerPos, movingAsset ).setPosition(oldPlayerPos);
			// S�tt objetet vi r�r oss till p� playerns gamla position och ge det playerns gamla position
			assets.set(oldPlayerPos, swapAsset ).setPosition(newPlayerPos);
			// S�tt controllerns position till den nya positionen
			super.setPosition(newPlayerPos);
		}
		else if (swapAsset instanceof Treasure) {
			((Treasure) swapAsset).openTreasure();
		}
		else if (swapAsset instanceof Enemy) {
			// S�tt objetet vi r�r oss till p� playerns gamla position och ge det playerns gamla position
			assets.set(oldPlayerPos, new Tile(oldPlayerPos) ).setPosition(newPlayerPos);
			playerDead();
		}
	}
	
	@Override
    public void run() {
        System.out.println("Startar playertr�d");
        while(GameWindowTemp.isGameState() && playerAlive) {
           // System.out.println("k�r playertr�d");
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