package Controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import assetclasses.AbstractAsset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Tile;
import viewer.GamePanel;
import viewer.GameWindowTemp;

public class EnemyController extends AssetController {
	private Enemy enemy;
	//True is going to the right
	private boolean goingToTheRight = true;
	// Is the enemy alive?
	private boolean isAlive = true;
	
	public EnemyController(int pos) {
		super( pos);
		enemy = new Enemy(pos);
		assets.add(enemy);
		// Kör timerTasken b efter 300ms
		b.scheduleAtFixedRate(c, 1000, 300);
	}
	
	public void moveDirection() {
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
			if (newEnemyLocation instanceof Tile ) {
				super.moveAsset(newEnemyPos, oldEnemyPos, enemy, newEnemyLocation);
			}
			// If player, kill player
			else if (newEnemyLocation instanceof Player  ) {
				super.killAsset(newEnemyPos, oldEnemyPos, enemy);
				PlayerController.playerDead();
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
        		this.cancel();
        	}
        }
    };
	/*
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
	*/	
}