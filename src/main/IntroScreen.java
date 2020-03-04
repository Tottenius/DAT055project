package main;

import java.net.MalformedURLException;
import viewer.GameWindowTemp;

public class IntroScreen {

	@SuppressWarnings("unused")
	public static void main(final String[] args) throws MalformedURLException {

		/*
		 * JWindow window = new JWindow(); window.getContentPane().add( new JLabel("",
		 * new ImageIcon(new URL("https://i.imgur.com/fVefx9v.gif")),
		 * SwingConstants.CENTER)); //intro screen maybe use splashscreen class instead
		 * window.setBounds(400, 100, 1200, 800); window.setVisible(true); try {
		 * Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
		 * window.dispose();
		 */

		new GameWindowTemp("level1");

	}
}