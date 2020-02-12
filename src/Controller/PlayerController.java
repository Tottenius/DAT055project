package Controller;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import assetclasses.Asset;
import assetclasses.Player;
import assetclasses.Tile;
import assetclasses.Treasure;
import viewer.GamePanel;
import viewer.GamePanel.Direction;


public class PlayerController extends AssetController implements Runnable  {
	
	private static ArrayList<Asset> assets = GamePanel.getAssetList();
	private Player player;
	private PlayerController me = this;
	
	
	public PlayerController(Direction direction, int pos) {
		super(direction, assets, pos);
		player = new Player(pos);
		assets.set(pos,player);
	}
/*
	private int assetLocation() {

		for (int i = 0; i < assets.size(); i++){
			
			if(assets.get(i) instanceof Player) 

				return i;
			
		}
		return 0 ; //this mean player can never be at zero but we can change this later
	}
	*/
	public void moveDirection( Direction direction) {
		// Right now just player pos
		int firstplayerpos = super.getPosition();
		System.out.println(firstplayerpos);
		System.out.println(direction);
		Asset movingAsset = assets.get(firstplayerpos);
		Asset swapAsset = null;
		int up = firstplayerpos - (WIDTH/SPACE);
		int down = firstplayerpos + (WIDTH/SPACE);
		int left = firstplayerpos -1;
		int right = firstplayerpos + 1;
		
		int dir = 0;
		
		if(direction == Direction.UP ) {
				dir = up;
		}
		if(direction == Direction.DOWN ) {		
				dir = down;
			
		}
		if(direction == Direction.LEFT ) {
				dir = left;
		}
		if(direction == Direction.RIGHT ) {
				dir = right;
			
		}
		
		// Alla interaktioner med assets
		swapAsset = assets.get(dir);
		if (swapAsset instanceof Tile) {
			assets.set(dir, movingAsset ).setPosition(firstplayerpos);
			assets.set(firstplayerpos, swapAsset ).setPosition(dir);
			super.setPosition(dir);
		}
		if (swapAsset instanceof Treasure) {
			((Treasure) swapAsset).openTreasure();
		}
		
		
	}
	
	public static Semaphore sem = new Semaphore(0, true);
	@Override
	public synchronized void run() {
		while(true) {
			
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				moveDirection(GamePanel.getDirection());
				try {
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}	


}
