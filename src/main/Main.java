package main;

import javax.swing.SwingUtilities;
import viewer.GameWindowTemp;

public class Main {
	public static boolean isRunning;
	
	public static void main(String[] args)  {

    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	    	isRunning = true;
    	    	//new ReadInWorld();
    	    	System.out.println(GameWindowTemp.state + " Main");
    	    	new GameWindowTemp("level1");  	    	
    	    }
    	  });
	}
}
