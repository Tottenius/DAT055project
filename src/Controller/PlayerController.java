package Controller;

import java.util.ArrayList;
import assetclasses.Asset;
import assetclasses.Player;
import assetclasses.Tile;
import assetclasses.Treasure;
import viewer.GamePanel;
import viewer.GamePanel.Direction;

public class PlayerController extends AssetController implements Runnable  {
	
	private static ArrayList<Asset> assets = GamePanel.getAssetList();
	private Player player;	
	
	public PlayerController(Direction direction, int pos) {
		super(direction, assets, pos);
		player = new Player(pos);
		assets.set(pos,player);
	}

	public void moveDirection( Direction direction) {
		// Right now just player pos
		int oldPlayerPos = super.getPosition();
		System.out.println(oldPlayerPos);
		System.out.println(direction);
		Asset movingAsset = assets.get(oldPlayerPos);
		Asset swapAsset = null;
		 
		int newPlayerPos = oldPlayerPos + GamePanel.getDirection().getXDelta() +  GamePanel.getDirection().getYDelta();
		
		// Alla interaktioner med assets
		swapAsset = assets.get(newPlayerPos);
		if (swapAsset instanceof Tile) {
			assets.set(newPlayerPos, movingAsset ).setPosition(oldPlayerPos);
			assets.set(oldPlayerPos, swapAsset ).setPosition(newPlayerPos);
			super.setPosition(newPlayerPos);
		}
		if (swapAsset instanceof Treasure) {
			((Treasure) swapAsset).openTreasure();
		}	
	}
	
	@Override
	public void run() {			
		//moveDirection(GamePanel.getDirection());
	}	
}