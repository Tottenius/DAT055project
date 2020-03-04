package Controller;

import java.util.Timer;
import java.util.TimerTask;
import assetclasses.AbstractAsset;
import assetclasses.Player;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

public class PlayerController extends AssetController {

	// Player
	private final Player player;

	public PlayerController(final int pos, final ReadInWorld world) {
		super(pos, world);
		this.player = new Player(pos);
		this.movingAssets.add(pos, this.player);
		GamePanel.numberOfControllers++;
		// Kör timerTasken b efter 150ms
		// startController();
	}

	public void moveDirection() {

		// Right now just player pos
		final int oldPlayerPos = super.getPosition();
		// System.out.println(oldPlayerPos);
		// System.out.println(direction);
		final AbstractAsset playerAsset = this.movingAssets.get(oldPlayerPos);
		AbstractAsset newPlayerLocationMovingLayer = null;
		AbstractAsset stationaryAsset = null;

		final int newPlayerPos = oldPlayerPos + GamePanel.getDirection().getXDelta()
				+ GamePanel.getDirection().getYDelta();

		// Alla interaktioner med assets
		newPlayerLocationMovingLayer = this.movingAssets.get(newPlayerPos);
		stationaryAsset = this.assets.get(newPlayerPos);
		// If tile, move to the tile
		if (stationaryAsset.canWalkOn() && newPlayerLocationMovingLayer.canWalkOn()) {
			super.moveAsset(newPlayerPos, oldPlayerPos, playerAsset, newPlayerLocationMovingLayer);
		}
		// If treasure, open treasure
		else if (stationaryAsset.intractable()) {
			// hej
			// System.out.println("tjo");
		}

		// If enemy, kill player :(
		else if (newPlayerLocationMovingLayer.canKill() || stationaryAsset.canKill()) {
			super.dieWhileMovingIntoDanger(oldPlayerPos);
			this.player.setAlive(false);
		}
	}

	Timer b = new Timer();

	TimerTask c = new TimerTask() {
		@Override
		public void run() {

			if (PlayerController.this.player.isAlive() && GameWindowTemp.isGameState() && GamePanel.isKeyPressed()) {
				moveDirection();
				// player.setCoords( position);
				// PlayerController.this.player.setPrevPos();
				System.out.println(PlayerController.this.player.getCoords());
				GamePanel.setKeyPressed(false);

			} else if (!GameWindowTemp.isGameState()) {
				System.out.println("Stänger av Player vi går till main menu");
				GamePanel.numberOfControllers--;
				this.cancel();
			} else if (!PlayerController.this.player.isAlive()) {
				System.out.println("Stänger av Player player död");
				GameWindowTemp.setDeathScreenState();
				GamePanel.numberOfControllers--;
				this.cancel();
			}
		}
	};

	@Override
	public void startController() {
		this.b.scheduleAtFixedRate(this.c, 1000, 150);

	}
}