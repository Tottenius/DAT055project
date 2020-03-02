package server;

import java.io.*;
import java.net.*;

 

public class UdpServer implements Runnable{ 
	
    public void startServer() throws IOException { 
    	System.out.println(" Vi försöker starta servern");
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
  
            System.out.println("Client:-" + data(receive)); 
  
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