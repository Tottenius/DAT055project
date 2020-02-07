package main;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import viewer.GamePanel;
import viewer.GameWindowTemp;

public class Main {

	public static void main(String[] args) throws MalformedURLException {

    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	    	new GameWindowTemp();
    	    }
    	  });
		
		
		//new GameWindowTemp();
	}

}
