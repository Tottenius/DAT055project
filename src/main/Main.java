package main;

import javax.swing.SwingUtilities;

import viewer.GameWindowTemp;

public class Main {
	public static boolean isRunning;

	@SuppressWarnings("unused")
	public static void main(final String[] args) {

		SwingUtilities.invokeLater(() -> {
			isRunning = true;
			new GameWindowTemp("level10");
		});
	}
}
