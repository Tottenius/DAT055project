package Controller;

import java.util.ArrayList;
import assetclasses.Asset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Tile;
import assetclasses.Treasure;
import main.Main;
import viewer.GameOverScreen;
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
		new GameWindowTemp();
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
			// Sätt playerns positon till den nya positionen och ge objectet den nya positionen
			assets.set(newPlayerPos, movingAsset ).setPosition(oldPlayerPos);
			// Sätt objetet vi rör oss till på playerns gamla position och ge det playerns gamla position
			assets.set(oldPlayerPos, swapAsset ).setPosition(newPlayerPos);
			// Sätt controllerns position till den nya positionen
			super.setPosition(newPlayerPos);
		}
		else if (swapAsset instanceof Treasure) {
			((Treasure) swapAsset).openTreasure();
		}
		else if (swapAsset instanceof Enemy) {
			// Sätt objetet vi rör oss till på playerns gamla position och ge det playerns gamla position
			assets.set(oldPlayerPos, new Tile(oldPlayerPos) ).setPosition(newPlayerPos);
			playerAlive = false;
		}
	}
	
	@Override
    public void run() {
        System.out.println("Startar playertråd");
        while(GameWindowTemp.isGameState() && playerAlive) {
            System.out.println("kör playertråd");
            if(GamePanel.isKeyPressed()) {
                moveDirection(direction);
                GamePanel.setKeyPressed(false);
            }

        }
    }
}