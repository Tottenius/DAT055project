package server;

import java.io.*;
import java.net.*;
import java.util.Scanner;
 

public class UdpClient implements Runnable { 
   
	String input = "";
	
	public void startClient(String input) throws IOException 
    { 
		System.out.println(" Vi försöker starta clienten");
       // Scanner sc = new Scanner(System.in); 
  
        // Step 1:Create the socket object for 
        // carrying the data. 
        DatagramSocket socket = new DatagramSocket(); 
  
        InetAddress ip = InetAddress.getLocalHost(); 
        byte buf[] = null; 

        
        // loop while user not enters "bye" 
        while (true) 
        { 
           // String inp = sc.nextLine(); 
  
            // convert the String input into the byte array. 
            buf = input.getBytes(); 
  
            // Step 2 : Create the datagramPacket for sending 
            // the data. 
            DatagramPacket DpSend = 
                  new DatagramPacket(buf, buf.length, ip, 4567); 
  
            // Step 3 : invoke the send call to actually send 
            // the data. 
            socket.send(DpSend); 
  
            // break the loop if user enters "bye" 
            if (input.equals("bye")) 
                break; 
        }
       
    }
	
    public void setreceivedInput(String input) {
    	this.input = input;
    	
    }
    
    public String getreceivedInput() {
    	
    	return this.input;
    }

	@Override
	public void run() {
		try {
			input = getreceivedInput();
			if(input != "")
			startClient(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
} 