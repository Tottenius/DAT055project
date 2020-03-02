package main;

import java.io.IOException;

import javax.swing.SwingUtilities;

import server.UdpClient;
import server.UdpServer;
import viewer.GameWindowTemp;

public class Main {
	public static boolean isRunning;
	
	public static void main(String[] args)  {
		UdpServer server = new UdpServer();
		UdpClient client = new UdpClient();
		Thread serverThread = new Thread(server);
		Thread clientThread = new Thread(client);
		
		serverThread.start();
		clientThread.start();
		

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
