package main;

import javax.swing.SwingUtilities;

import viewer.GameWindowTemp;

public class Main {

	public static void main(String[] args)  {

    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	    	new GameWindowTemp();
    	    }
    	  });
		
		
		//new GameWindowTemp();
	}

}
