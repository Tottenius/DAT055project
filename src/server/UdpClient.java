package server;

import java.io.*;
import java.net.*;
 
public class UdpClient implements Runnable { 
   
	String input = "";
	public void sendMessage(String message) throws IOException { 
		System.out.println(" Vi försöker skicka ett medelande till servern från");
       // Scanner sc = new Scanner(System.in); 
  
        // Step 1:Create the socket object for 
        // carrying the data. 
        DatagramSocket socket = new DatagramSocket(); 
        this.setreceivedInput(message); 
        
        InetAddress ip = InetAddress.getLocalHost(); 
        byte buf[] = null; 
        input = getreceivedInput();
        
        // convert the String input into the byte array. 
        buf = input.getBytes(); 

        // Step 2 : Create the datagramPacket for sending 
        // the data. 
        DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 4567); 

        // Step 3 : invoke the send call to actually send 
        // the data. 
        socket.send(DpSend);
        socket.close();
    }
	
	public void startClient() throws IOException 
    { 
		// Step 1 : Create a socket to listen at port 4568 
        DatagramSocket socket = new DatagramSocket(4568); 
        byte[] receive = new byte[65535]; 
  
        DatagramPacket DpReceive = null; 
        while (true) 
        { 
  
            // Step 2 : create a DatgramPacket to receive the data. 
            DpReceive = new DatagramPacket(receive, receive.length); 
  
            // Step 3 : revieve the data in byte buffer. 
            socket.receive(DpReceive); 
            
            System.out.println("we where here");
            System.out.println(data(receive).toString());
            
            // Clear the buffer after every message. 
            receive = new byte[65535]; 
        } 
     
    }
	
    public void setreceivedInput(String input) {
    	this.input = input;   	
    }
    
    public String getreceivedInput() {
    	
    	return this.input;
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
			startClient();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	} 
} 