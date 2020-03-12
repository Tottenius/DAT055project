package Controller;

import java.util.Timer;
import java.util.TimerTask;

import assetclasses.AbstractAsset;
import assetclasses.Enemy;
import assetclasses.Player;
import model.ReadInWorld;
import viewer.GamePanel;
import viewer.GameWindowTemp;
/**
 * A controller for the Enemy asset
 * 
 * @author Group 10
 * @version 2020-03-06
 *
 */
public class EnemyController extends AssetController {
	private final Enemy enemy;

	// True is going to the right
	private boolean goingToTheRight = true;
/**
 * Controller for the Enemy asset
 * 
 * @param pos
 * Position for the controller
 * @param world
 * World in which the controller should exist
 */
	public EnemyController(final int pos, final ReadInWorld world) {
		super(pos, world);
		this.enemy = new Enemy(pos);
		this.movingAssets.add(pos, this.enemy);
		// Kör timerTasken b efter 300ms
		GamePanel.numberOfControllers++;
		// startController();
	}

	private void moveDirection() {
		// Right now just player pos
		final int oldEnemyPos = super.getPosition();
		int newEnemyPos = 0;
		final AbstractAsset enemy = this.movingAssets.get(oldEnemyPos);
		AbstractAsset newEnemyLocationMovingLayer = null;
		AbstractAsset newEnemyLocationStationaryLayer = null;

		// Going to the right
		if (this.goingToTheRight) {
			newEnemyPos = oldEnemyPos + 1;
			newEnemyLocationMovingLayer = this.movingAssets.get(newEnemyPos);
		}

		// Going to the left
		else {
			newEnemyPos = oldEnemyPos - 1;
			newEnemyLocationMovingLayer = this.movingAssets.get(newEnemyPos);
		}

		// Alla interaktioner med assets
		// If tile, move to tile
		newEnemyLocationStationaryLayer = this.assets.get(newEnemyPos);
		if (newEnemyLocationStationaryLayer.canWalkOn() && newEnemyLocationMovingLayer.canWalkOn()) {
			super.moveAsset(enemy, newEnemyLocationMovingLayer);
		}
		// If player, kill player
		else if (newEnemyLocationMovingLayer.killable()) {
			super.killAsset(newEnemyPos, enemy);
			((Player) newEnemyLocationMovingLayer).setAlive(false);
		}
		// else turn around
		else {
			this.goingToTheRight = !this.goingToTheRight;
		}
	}

	private Timer b = new Timer();

	private TimerTask c = new TimerTask() {
		@Override
		public void run() {

			if (GameWindowTemp.isGameState()) {
				moveDirection();
			}

			else if (!GameWindowTemp.isGameState()) {
				GamePanel.numberOfControllers--;
				this.cancel();
			}
		}
	};
	/**
	 * Starts the EnemyController
	 */
	@Override
	public void startController() {
		this.b.scheduleAtFixedRate(this.c, 1000, 300);

	}
}