package server;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
			this.leaderboard = new String(Files.readAllBytes(Paths.get(leaderboardPath)));
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
            	
            	
            	readInLeaderboard();
            	
            	sendLeaderboardToClient(this.leaderboard);
            }
            
            else
            
           // Exit the server if the client sends "bye" 
                if (data(receive).toString().equals("bye")) 
                { 
                    System.out.println("Client sent bye.....EXITING"); 
                    break; 
                } 
            	
            	// System.out.println("Client:-" + data(receive)); 
            writeToLeaderboard(data(receive));
  
            // Clear the buffer after every message. 
            receive = new byte[65535]; 
        } 
    }
    
    //we need to catch that exception
   private void sendLeaderboardToClient(String leaderboard) throws IOException {
	   
   
       // Step 1:Create the socket object for 
       // carrying the data. 
       DatagramSocket socket = new DatagramSocket(); 
       
       InetAddress ip = InetAddress.getLocalHost(); 
       byte buf[] = null; 

       
       // convert the String input into the byte array. 
       buf = leaderboard.getBytes();
       // Step 2 : Create the datagramPacket for sending 
       // the data. 
       DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 4568); 
       
       // Step 3 : invoke the send call to actually send 
       // the data. 
       socket.send(DpSend);
       socket.close();
	   
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