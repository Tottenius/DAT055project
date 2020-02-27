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
	
	//private Tile tile;
	private Spikes spikes;
	
	private boolean isSpike = true;
	
	public SpikeController(int pos, ReadInWorld world) {
		super(pos, world);
		//tile = new Tile(position);
		spikes = new Spikes(position);
		assets.add(pos, spikes);
		GamePanel.numberOfControllers ++;
		// Kör timerTasken b körs varannan sek, startar efter 1 sek
		b.scheduleAtFixedRate(c, 1000, 2000);
		
	}
	// Vi kör en timer istället för en busy wait
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
        public void run() {
        	if(GameWindowTemp.isGameState()) {
	        	System.out.println("Spikes coords " + spikes.getCoords());
	        	// Om det är en player på. Döda den
	        	if (assets.get(position).killable()) {
					((Player) assets.get(position)).setAlive(false);					
				}
	        	// Byt om vart annat mellan spike och passable
				else  {
	        		spikes.changeUpOrDown();
				}
	        	spikes.setPosition(position);

        	}
        	else if(!GameWindowTemp.isGameState()) {
        		System.out.println("Stänger av Spikes");
        		GamePanel.numberOfControllers --;
        		this.cancel();
        	}
		}
    };
}