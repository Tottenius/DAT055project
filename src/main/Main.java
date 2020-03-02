package main;

import java.io.IOException;

import javax.swing.SwingUtilities;

import server.UdpClient;
import server.UdpServer;
import viewer.GameWindowTemp;

public class Main {
	public static boolean isRunning;
	
	public static void main(String[] args) throws IOException  {
		UdpServer server = new UdpServer();
		UdpClient client = new UdpClient();
		Thread serverThread = new Thread(server);
		
		serverThread.start();
	
		client.sendMessage("hello");
		client.sendMessage("hello 2nd message");
		client.sendMessage("Känns ju som det här typ funkar :)");

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
