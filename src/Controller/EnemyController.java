package Controller;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import assetclasses.Asset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Tile;
import assetclasses.Treasure;
import viewer.GamePanel;
import viewer.GamePanel.Direction;

public class EnemyController extends AssetController implements Runnable  {
	
	private static ArrayList<Asset> assets = GamePanel.getAssetList();
	private Enemy enemy;
	private int steps = 4;
	
	
	public EnemyController(Direction direction, int pos) {
		super(direction, assets, pos);
		enemy = new Enemy(pos);
		assets.set(pos,enemy);
	}

	
	public void moveDirection() {
		// Right now just player pos
		int enemyPos = super.getPosition();
		System.out.println(enemyPos);
		Asset movingAsset = assets.get(enemyPos);
		Asset swapAsset = null;
		int up = enemyPos - (WIDTH/SPACE);
		int down = enemyPos + (WIDTH/SPACE);
		int left = enemyPos -1;
		int right = enemyPos + 1;
		
		if (steps > 0) {
			swapAsset = assets.get(left);
			assets.set(left, movingAsset);
			assets.set(enemyPos, swapAsset);
			steps--;
		}
		if (steps < 0) {
			swapAsset = assets.get(right);
			assets.set(left, movingAsset);
			assets.set(enemyPos, swapAsset);
			steps--;
		}
		
		
		
	}
	
	//public static Semaphore sem = new Semaphore(0, true);
	@Override
	public synchronized void run() {

	}	

}