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
		assets.add(pos, enemy);
		// Kör timerTasken b efter 300ms
		GamePanel.numberOfControllers ++;
		b.scheduleAtFixedRate(c, 1000, 300);
	}
	
	private void moveDirection() {
		// Right now just player pos
		int oldEnemyPos = super.getPosition();
		int newEnemyPos = 0;
		//System.out.println(oldEnemyPos);
		AbstractAsset enemy = assets.get(oldEnemyPos);
		AbstractAsset newEnemyLocation = null;
			
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
			// If tile, move to tile
			if (newEnemyLocation.canWalkOn() ) {
				super.moveAsset(newEnemyPos, oldEnemyPos, enemy, newEnemyLocation);
			}
			// If player, kill player
			else if (newEnemyLocation.killable()  ) {
				super.killAsset(newEnemyPos, oldEnemyPos, enemy);
				((Player) newEnemyLocation).setAlive(false);
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
			}

        	else if(!GameWindowTemp.isGameState() ) {
        		System.out.println("Stänger av Monster");
        		GamePanel.numberOfControllers --;
        		this.cancel();
        	}
        }
    };
}