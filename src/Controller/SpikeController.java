package Controller;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import assetclasses.Player;
import assetclasses.Spikes;
import assetclasses.Tile;
import viewer.GamePanel;
import viewer.GameWindowTemp;

public class SpikeController extends AssetController{
	
	private Tile tile;
	private Spikes spikes;
	
	private boolean isSpike = true;
	
	public SpikeController(int position) {
		super(position);
		tile = new Tile(position);
		spikes = new Spikes(position);
		assets.add(spikes);
		// Kör timerTasken b körs varannan sek, startar efter 1 sek
		b.scheduleAtFixedRate(c, 1000, 2000);
		
	}
	// Vi kör en timer istället för en busy wait
    Timer b = new Timer();
    
    TimerTask c = new TimerTask() {
        public void run() {
        	if(GameWindowTemp.isGameState() && GamePanel.levelRead) {
	        	System.out.println(assets.get(position));
				//Fulfix player är inte längre spikes :(
	        	
	        	if (assets.get(position) instanceof Player) {
					System.out.println("plz die");
					PlayerController.playerDead();
					
				}
				else if (isSpike) {
	        		//System.out.println("nu är jag en tile");
	        		changeAsset(position, tile);
	        		isSpike = false;
	        	}
	        	else if (!isSpike) {
	        		changeAsset(position, spikes);
	        		isSpike= true;
	        	}
	        	else if(!GameWindowTemp.isGameState() && GamePanel.levelRead) {
	        		this.cancel();
	        	}
        	}

			
			
			//System.out.println("nu är jag en spike");
			}
    };

	/*
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
	*/
	
	
	

}
