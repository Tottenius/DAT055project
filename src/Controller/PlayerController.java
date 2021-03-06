package Controller;

import java.util.Timer;

import java.util.TimerTask;
import model.AbstractAsset;
import model.Player;
import model.ReadInWorld;
import viewer.GamePanel;
import viewer.ProgramStateHandeler;
/**
 * A controller for the Player asset
 * 
 * @author Group 10
 * @version 2020-03-06
 *
 */
public class PlayerController extends AssetController {

	// Player
	private final Player player;
	/**
	 * Controller for the Player asset
	 * 
	 * @param pos
	 * Position for the controller
	 * @param world
	 * World in which the controller should exist
	 */
	public PlayerController(final int pos, final ReadInWorld world) {
		super(pos, world);
		this.player = new Player(pos);
		this.movingAssets.add(pos, this.player);
		GamePanel.numberOfControllers++;
	}

	private void moveDirection() {

		// Right now just player pos
		final int oldPlayerPos = super.getPosition();

		final AbstractAsset playerAsset = this.movingAssets.get(oldPlayerPos);
		AbstractAsset newPlayerLocationMovingLayer = null;
		AbstractAsset stationaryAsset = null;

		final int newPlayerPos = oldPlayerPos + KeyListenerController.getDirection().getXDelta()
				+ KeyListenerController.getDirection().getYDelta();

		// Alla interaktioner med assets
		newPlayerLocationMovingLayer = this.movingAssets.get(newPlayerPos);
		stationaryAsset = this.assets.get(newPlayerPos);
		// If tile, move to the tile
		if (stationaryAsset.canWalkOn() && newPlayerLocationMovingLayer.canWalkOn()) {
			super.moveAsset(playerAsset, newPlayerLocationMovingLayer);
		}
		// If treasure, open treasure
		else if (stationaryAsset.intractable()) {
			// hej
		}

		// If enemy, kill player :(
		else if (newPlayerLocationMovingLayer.canKill() || stationaryAsset.canKill()) {
			super.dieWhileMovingIntoDanger(oldPlayerPos);
			this.player.setAlive(false);
		}
	}

	private Timer b = new Timer();

	private TimerTask c = new TimerTask() {
		@Override
		public void run() {

			if (PlayerController.this.player.isAlive() && ProgramStateHandeler.isGameState() && KeyListenerController.isKeyPressed()) {
				moveDirection();
				// player.setCoords( position);
				// PlayerController.this.player.setPrevPos();
				KeyListenerController.setKeyPressed(false);

			} else if (!ProgramStateHandeler.isGameState() && !ProgramStateHandeler.isSaveLevelState()) {
				GamePanel.numberOfControllers--;
				this.cancel();
			} else if (!PlayerController.this.player.isAlive()) {
				ProgramStateHandeler.setDeathScreenState();
				GamePanel.numberOfControllers--;
				this.cancel();
			}
		}
	};
	/**
	 * Starts the PlayerController
	 */
	@Override
	public void startController() {
		this.b.scheduleAtFixedRate(this.c, 1000, 10);

	}
}