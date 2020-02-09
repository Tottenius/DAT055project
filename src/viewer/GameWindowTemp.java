package viewer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;



public class GameWindowTemp extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private enum STATE{
		MENU,
		GAME
	};
	
	private static STATE State = STATE.MENU;
	private Menu menu;
	
	// La till knappar och storlek  f�r rutan s� jag kunde leka lite med en meny. Men det uppstod komplikationer
	final static int HEIGHT = 640;
	final static int WIDTH = 480;
//	private JButton start = new JButton("Start");
//	private JButton quit = new JButton("Quit");

	
	public GameWindowTemp(){

		if (State == STATE.MENU) { 
			menu = new Menu();
			this.add(menu);
		}
		
		//if gamestate is Game then we start the game;
		if (State == STATE.GAME) { 
		this.add(new GamePanel());
		}
		
		this.pack();		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void SetStateGame(){
		
			State = STATE.GAME;
		
	}
	
	public static void SetStateMenu(){
		
		State = STATE.MENU;
	
}
}
	