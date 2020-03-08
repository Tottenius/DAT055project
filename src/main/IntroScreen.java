package main;


import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import viewer.GameWindowTemp;
/**
 * An class that plays a short intro, by reading a gif file from an URL
 * 
 * @author Grupp 10
 *
 */
public class IntroScreen {

	@SuppressWarnings("unused")
	public static void main(final String[] args) throws MalformedURLException {
	
		  JWindow window = new JWindow(); window.getContentPane().add( new JLabel("",
		  new ImageIcon(new URL("https://i.imgur.com/KuN4n3N.gif")),SwingConstants.CENTER)); 
		  window.setBounds(400, 100, 1200, 800); window.setVisible(true); try {
		  Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
		 window.dispose();
		 

		new GameWindowTemp("level1");

	}
}