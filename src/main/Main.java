package main;

import javax.swing.SwingUtilities;

import viewer.GameWindowTemp;
/**
 * The main class for the program.
 * @author anton
 *
 */
public class Main {
	public static boolean isRunning;

	@SuppressWarnings("unused")
	public static void main(final String[] args) {

		SwingUtilities.invokeLater(() -> {
			isRunning = true;
			new GameWindowTemp("level1");
		});
	}
}
