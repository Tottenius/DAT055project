package Controller;

import java.util.Timer;
import java.util.TimerTask;

import assetclasses.AbstractAsset;
import assetclasses.Enemy;
import assetclasses.Player;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

public class EnemyController extends AssetController {
	private Enemy enemy;
	
	//True is going to the right
	private boolean goingToTheRight = true;
	
	public EnemyController(int pos, ReadInWorld world) {
		super( pos,world);
		enemy = new Enemy(pos);
		movingAssets.add(pos, enemy);
		// Kör timerTasken b efter 300ms
		System.out.println("Gör nya monster controllers");
		GamePanel.numberOfControllers ++;
		//startController();
	}
	
	private void moveDirection() {
		// Right now just player pos
		int oldEnemyPos = super.getPosition();
		int newEnemyPos = 0;
		//System.out.println(oldEnemyPos);
		AbstractAsset enemy = movingAssets.get(oldEnemyPos);
		AbstractAsset newEnemyLocationMovingLayer = null;
		AbstractAsset newEnemyLocationStationaryLayer = null;
			
			// Going to the right
			if(goingToTheRight) {
				newEnemyPos = oldEnemyPos + 1;
				newEnemyLocationMovingLayer = movingAssets.get(newEnemyPos);
			}
			
			// Going to the left
			else {
				newEnemyPos = oldEnemyPos - 1;
				newEnemyLocationMovingLayer = movingAssets.get(newEnemyPos);
			}
			
			// Alla interaktioner med assets
			// If tile, move to tile
			newEnemyLocationStationaryLayer = assets.get(newEnemyPos);
			if (newEnemyLocationStationaryLayer.canWalkOn() && newEnemyLocationMovingLayer.canWalkOn()) {
				super.moveAsset(newEnemyPos, oldEnemyPos, enemy, newEnemyLocationMovingLayer);
			}
			// If player, kill player
			else if (newEnemyLocationMovingLayer.killable()  ) {
				super.killAsset(newEnemyPos, oldEnemyPos, enemy);
				((Player) newEnemyLocationMovingLayer).setAlive(false);
			}
			// else turn around
			else  {
				goingToTheRight = !goingToTheRight;
			}		
	}
	
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
        public void run() {
        	
        	if(GameWindowTemp.isGameState()) {
        		moveDirection();
        		//System.out.println(enemy.getCoords());
			}

        	else if(!GameWindowTemp.isGameState() ) {
        		System.out.println("Stänger av Monster");
        		GamePanel.numberOfControllers --;
        		this.cancel();
        	}
        }
    };

	@Override
	public void startController() {
		b.scheduleAtFixedRate(c, 1000, 300);
		
	}
}