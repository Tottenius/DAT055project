package main;

import java.io.IOException;

import javax.swing.SwingUtilities;

import server.UdpClient;
import server.UdpServer;
import viewer.GameWindowTemp;

public class Main {
	public static boolean isRunning;
	
	public static void main(String[] args) throws IOException  {

    	
		SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	    	isRunning = true;
    	    	System.out.println(GameWindowTemp.state + " Main");
    	    	new GameWindowTemp("level1");  	    	
    	    }
    	  });
	}
}
