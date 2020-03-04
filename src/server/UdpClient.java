package server;

import java.io.*;
import java.net.*;

public class UdpClient implements Runnable {

	String input = "";

	public void sendMessage(final String message) throws IOException {
		System.out.println(" Vi försöker skicka ett medelande till servern från");
		// Scanner sc = new Scanner(System.in);

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

	public void startClient() throws IOException {
		// Step 1 : Create a socket to listen at port 4568
		try (final DatagramSocket socket = new DatagramSocket(4568)) {
			byte[] receive = new byte[65535];

			DatagramPacket DpReceive = null;
			while (true) {

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
	}

	public void setreceivedInput(final String input) {
		this.input = input;
	}

	public String getreceivedInput() {

		return this.input;
	}

	// A utility method to convert the byte array
	// data into a string representation.
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

	@Override
	public void run() {
		try {
			startClient();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}