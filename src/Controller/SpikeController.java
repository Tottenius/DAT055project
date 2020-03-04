package Controller;

import java.util.Timer;
import java.util.TimerTask;

import assetclasses.Player;
import assetclasses.Spikes;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

public class SpikeController extends AssetController {

	// private Tile tile;
	private final Spikes spikes;

	public SpikeController(final int pos, final ReadInWorld world) {
		super(pos, world);
		// tile = new Tile(position);
		this.spikes = new Spikes(this.position);
		this.assets.add(pos, this.spikes);
		GamePanel.numberOfControllers++;
		// Kör timerTasken b körs varannan sek, startar efter 1 sek
		// startController();

	}

	// Vi kör en timer istället för en busy wait
	Timer b = new Timer();

	TimerTask c = new TimerTask() {
		@Override
		public void run() {
			if (GameWindowTemp.isGameState()) {
				// System.out.println("Spikes coords " + spikes.getCoords());
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
				System.out.println("Stänger av Spikes");
				GamePanel.numberOfControllers--;
				this.cancel();
			}
		}
	};

	@Override
	public void startController() {
		this.b.scheduleAtFixedRate(this.c, 1000, 2000);

	}
}