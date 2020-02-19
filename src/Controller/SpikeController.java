package Controller;

import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import viewer.GameWindowTemp;

public class SpikeController extends AssetController implements Runnable{
	
	private Tile tile;
	private Spikes spikes;
	
	public SpikeController(int position) {
		super(position);
		tile = new Tile(position);
		spikes = new Spikes(position);
		assets.add(spikes);
	}


	
	@Override
	public void run() {
		while(GameWindowTemp.isGameState()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("nu är jag en tile");
			changeAsset(position, tile);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Fulfix player är inte längre spikes :(
			if(assets.get(position) instanceof Player) {
				PlayerController.playerDead();
			}
			changeAsset(position, spikes);
			
			//System.out.println("nu är jag en spike");
		}
	}
	
	
	

}
