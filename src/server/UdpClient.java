package server;

import java.io.*;
import java.net.*;
/**
 * A client class used for UDP communication. 
 * 
 * @author Anton, Rishabh Mahrsee and MarkShanks 
 * @see https://www.geeksforgeeks.org/working-udp-datagramsockets-java/
 *
 */
public class UdpClient implements Runnable {

	String input = "";
	int previouslyRan = 0;
/**
 * Method takes a message and sends it via standard UDP protocol.
 * 
 * @param message
 * @throws IOException
 */
	public void sendMessage(final String message) throws IOException {

		// Step 1:Create the socket object for
		// carrying the data.
		try (final DatagramSocket socket = new DatagramSocket()) {
			this.setreceivedInput(message);

			final InetAddress ip = InetAddress.getLocalHost();
			byte buf[] = null;
			this.input = getreceivedInput();

			// convert the String input into the byte array.
			buf = this.input.getBytes();

			// Step 2 : Create the datagramPacket for sending
			// the data.
			final DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 4567);

			// Step 3 : invoke the send call to actually send
			// the data.
			socket.send(DpSend);
		}
	}
/**
 * Starts the client such that it can now receive data from another source
 * 
 * @throws IOException
 */
	public void startClient() throws IOException {
	
		// Step 1 : Create a socket to listen at port 4568
		try (final DatagramSocket socket = new DatagramSocket(4568)) {
			byte[] receive = new byte[65535];

			DatagramPacket DpReceive = null;
			while (previouslyRan == 0) {

				// Step 2 : create a DatgramPacket to receive the data.
				DpReceive = new DatagramPacket(receive, receive.length);

				// Step 3 : revieve the data in byte buffer.
				socket.receive(DpReceive);


				// Clear the buffer after every message.
				receive = new byte[65535];
			}
			
		}		
	}
	/**
	 * Closes the socket, precaution for starting new game. 
	 */
	public void stopSocket() {
		
	this.previouslyRan = 1;	
	}
	
/**
 * Sets the received input from another source.
 * 
 * @param input
 */
	public void setreceivedInput(final String input) {
		this.input = input;
	}
/**
 * Get the input that was last received from another source.
 * 
 * @return String of data from another source.
 */
	public String getreceivedInput() {

		return this.input;
	}

/**
 *  A utility method to convert the byte array "a" data into a string representation "ret".
 * @author Rishabh Mahrsee and MarkShanks 
 * @param 
 * @return String 
 */
	public static StringBuilder data(final byte[] a) {
		if (a == null) {
			return null;
		}
		final StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0) {
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
/**
 * Starts the client thread.
 */
	@Override
	public void run() {
		try {
			startClient();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}