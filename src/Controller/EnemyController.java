package Controller;

import java.util.ArrayList;
import assetclasses.Asset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Tile;
import viewer.GamePanel;
import viewer.GameWindowTemp;

public class EnemyController extends AssetController implements Runnable  {
	
	private static ArrayList<Asset> assets = GamePanel.getAssetList();
	private Enemy enemy;
	//True is going to the right
	private boolean goingToTheRight = true;
	// Is the enemy alive?
	private boolean isAlive = true;
	
	public EnemyController(int pos) {
		super( pos);
		enemy = new Enemy(pos);
		assets.add(enemy);
	}
	
	public void moveDirection() {
		// Right now just player pos
		int oldEnemyPos = super.getPosition();
		int newEnemyPos = 0;
		//System.out.println(oldEnemyPos);
		Asset enemy = assets.get(oldEnemyPos);
		Asset newEnemyLocation = null;
			
			// Going to the right
			if(goingToTheRight) {
				newEnemyPos = oldEnemyPos + 1;
				newEnemyLocation = assets.get(newEnemyPos);
			}
			
			// Going to the left
			else {
				newEnemyPos = oldEnemyPos - 1;
				newEnemyLocation = assets.get(newEnemyPos);
			}
			
			// Alla interaktioner med assets
			// Borde göras till en allmän moveTo() som kan användas för allt som rör sig. Svårläst såhär
			if (newEnemyLocation instanceof Tile ) {
				// Den nya positionen
				assets.set(newEnemyPos, enemy ).setPosition(oldEnemyPos);
				// Den gamla positionen
				assets.set(oldEnemyPos, newEnemyLocation ).setPosition(newEnemyPos);
				super.setPosition(newEnemyPos);
			}
			else if (newEnemyLocation instanceof Player  ) {
				// Den nya positionen
				assets.set(newEnemyPos, enemy ).setPosition(oldEnemyPos);
				// Den gamla positionen
				assets.set(oldEnemyPos, new Tile(oldEnemyPos) ).setPosition(newEnemyPos);
				super.setPosition(newEnemyPos);
				PlayerController.playerDead();
			}
			else  {
				goingToTheRight = !goingToTheRight;
			}		
	}
	
	@Override
	public void run() {
		while(GameWindowTemp.isGameState() && isAlive ) {		
			moveDirection();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}	
}