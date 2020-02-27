package Controller;

import java.util.Timer;
import java.util.TimerTask;

import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

public class SpikeController extends AssetController{
	
	private Tile tile;
	private Spikes spikes;
	
	private boolean isSpike = true;
	
	public SpikeController(int pos, ReadInWorld world) {
		super(pos, world);
		tile = new Tile(position);
		spikes = new Spikes(position);
		assets.add(pos, spikes);
		GamePanel.numberOfControllers ++;
		// K�r timerTasken b k�rs varannan sek, startar efter 1 sek
		b.scheduleAtFixedRate(c, 1000, 2000);
		
	}
	// Vi k�r en timer ist�llet f�r en busy wait
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
        public void run() {
        	if(GameWindowTemp.isGameState()) {
	        	System.out.println(assets.get(position));
	     
	        	if (assets.get(position).killable()) {
					System.out.println("plz die");
					((Player) assets.get(position)).setAlive(false);
					
				}
				else if (isSpike) {
	        		//System.out.println("nu �r jag en tile");
	        		changeAsset(position, tile);
	        		isSpike = false;
	        	}
	        	else if (!isSpike) {
	        		changeAsset(position, spikes);
	        		isSpike= true;
	        	}

        	}
        	else if(!GameWindowTemp.isGameState()) {
        		System.out.println("St�nger av Spikes");
        		GamePanel.numberOfControllers --;
        		this.cancel();
        	}
		}
    };
}