package server;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author anton
 *
 */
public class UdpServer implements Runnable {

	private String leaderboard;
	private final String leaderboardPath = "src/leaderboard/leaderboard.txt";

	public UdpServer() {
		readInLeaderboard();
	}
/**
 * Reads in a leaderboard representation file.
 * 
 */
	private void readInLeaderboard() {
		this.leaderboard = null;
		try {
			this.leaderboard = new String(Files.readAllBytes(Paths.get(this.leaderboardPath)));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
/**
 * method that writes a given string to a .txt file.
 * 
 * @param stringBuilder
 */
	private void writeToLeaderboard(final StringBuilder stringBuilder) {
		try {
			try (final BufferedWriter writer = new BufferedWriter(new FileWriter(this.leaderboardPath, true))) {
				writer.append(stringBuilder);
				writer.newLine();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
/**
 * 	Starts the server such that it can now receive data from another source, usually a client.
 * 
 * @throws IOException
 */
	public void startServer() throws IOException {
		
		// Step 1 : Create a socket to listen at port 4567
		try (final DatagramSocket socket = new DatagramSocket(4567)) {
			byte[] receive = new byte[65535];

			DatagramPacket DpReceive = null;
			while (true) {

				// Step 2 : create a DatgramPacket to receive the data.
				DpReceive = new DatagramPacket(receive, receive.length);

				// Step 3 : revieve the data in byte buffer.
				socket.receive(DpReceive);

				if (data(receive).toString().equals("getLeaderboard")) {

					readInLeaderboard();

					sendLeaderboardToClient(this.leaderboard);
				}

				// Exit the server if the client sends "bye"
				else if (data(receive).toString().equals("bye")) {
					System.out.println("Client sent bye.....EXITING");
					break;
				} else {
					writeToLeaderboard(data(receive));
				}

				// Clear the buffer after every message.
				receive = new byte[65535];
			}
		}
	}
/**
 * Sends a leaderboard string representation to another source.
 * 
 * @param leaderboard
 * @throws IOException
 */
	// we need to catch that exception
	private static void sendLeaderboardToClient(final String leaderboard) throws IOException {

		// Step 1:Create the socket object for
		// carrying the data.
		try (final DatagramSocket socket = new DatagramSocket()) {

			final InetAddress ip = InetAddress.getLocalHost();
			byte buf[] = null;

			// convert the String input into the byte array.
			buf = leaderboard.getBytes();
			// Step 2 : Create the datagramPacket for sending
			// the data.
			final DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 4568);

			// Step 3 : invoke the send call to actually send
			// the data.
			socket.send(DpSend);
		}

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
	 * Starts the server thread.
	 */
	@Override
	public void run() {
		try {
			startServer();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}