package Controller;

import java.util.Timer;
import java.util.TimerTask;

import model.Player;
import model.ReadInWorld;
import model.Spikes;
import viewer.GamePanel;
import viewer.GameWindowTemp;
/**
 * A controller for the Spikes asset
 * 
 * @author Group 10
 * @version 2020-03-06
 *
 */
public class SpikeController extends AssetController {

	// private Tile tile;
	private final Spikes spikes;
	/**
	 * Controller for the Spikes asset
	 * 
	 * @param pos
	 * Position for the controller
	 * @param world
	 * World in which the controller should exist
	 */
	public SpikeController(final int pos, final ReadInWorld world) {
		super(pos, world);
		// tile = new Tile(position);
		this.spikes = new Spikes(this.position);
		this.assets.add(pos, this.spikes);
		GamePanel.numberOfControllers++;
	}

	// Vi kör en timer istället för en busy wait
	private Timer b = new Timer();

	private TimerTask c = new TimerTask() {
		@Override
		public void run() {
			if (GameWindowTemp.isGameState()) {
				
				// Om det är en player på. Döda den
				if (SpikeController.this.movingAssets.get(SpikeController.this.position).killable()) {
					((Player) SpikeController.this.movingAssets.get(SpikeController.this.position)).setAlive(false);
				}
				// Byt om vart annat mellan spike och passable
				else {
					SpikeController.this.spikes.changeUpOrDown();
				}
				SpikeController.this.spikes.setPosition(SpikeController.this.position);

			} else if (!GameWindowTemp.isGameState()) {
				GamePanel.numberOfControllers--;
				this.cancel();
			}
		}
	};
	/**
	 * Start the SpikeController
	 */
	@Override
	public void startController() {
		this.b.scheduleAtFixedRate(this.c, 1000, 2000);

	}
}