package server;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

 

public class UdpServer implements Runnable{
	
	private String leaderboard;
	private final String leaderboardPath = "src/leaderboard/leaderboard.txt";
	
	//private File leaderboardFile = new File("src/leaderboard/leaderboard.txt");
	
	public UdpServer() {
		readInLeaderboard();
	}
	
	private void readInLeaderboard() {
		leaderboard = null;
		try {
			leaderboard = new String(Files.readAllBytes(Paths.get(leaderboardPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void writeToLeaderboard(StringBuilder stringBuilder) {
		FileWriter write = null;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(leaderboardPath, true));
			writer.append(stringBuilder);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void startServer() throws IOException { 
    	//System.out.println(" Vi försöker starta servern");
        // Step 1 : Create a socket to listen at port 4567 
        DatagramSocket socket = new DatagramSocket(4567); 
        byte[] receive = new byte[65535]; 
  
        DatagramPacket DpReceive = null; 
        while (true) 
        { 
  
            // Step 2 : create a DatgramPacket to receive the data. 
            DpReceive = new DatagramPacket(receive, receive.length); 
  
            // Step 3 : revieve the data in byte buffer. 
            socket.receive(DpReceive); 
            
            if (data(receive).toString().equals("getLeaderboard")) {
            	
            	sendLeaderboardToClient();
            }
            
            else
           // System.out.println("Client:-" + data(receive)); 
            writeToLeaderboard(data(receive));
  
            // Exit the server if the client sends "bye" 
            if (data(receive).toString().equals("bye")) 
            { 
                System.out.println("Client sent bye.....EXITING"); 
                break; 
            } 
  
            // Clear the buffer after every message. 
            receive = new byte[65535]; 
        } 
    }
    
   private void sendLeaderboardToClient() {
    	
	   //send the leaderboard to the client
	   
    }
  
    // A utility method to convert the byte array 
    // data into a string representation. 
    public static StringBuilder data(byte[] a) 
    { 
        if (a == null) 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) 
        { 
            ret.append((char) a[i]); 
            i++; 
        } 
        return ret; 
    }

	@Override
	public void run() {
		try {
			startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
} 