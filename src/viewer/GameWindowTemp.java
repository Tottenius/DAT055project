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
	
	// La till knappar och storlek  för rutan så jag kunde leka lite med en meny. Men det uppstod komplikationer
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
		
	}
	
	
	public static void SetStateGame(){
		
			State = STATE.GAME;
		
	}
	
	public static void SetStateMenu(){
		
		State = STATE.MENU;
	
}
	
	/*
	//Försökt att göra så att man startar spelet på en knapp. Men keylistners för spelet funkar inte när man genererar GamePanel såhär av någon anledning....
	private void addAllActionListners() {
		start.addActionListener(
	            new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	getContentPane().removeAll();
	                	getContentPane().add(new GamePanel());
	                	getContentPane().revalidate();
	                }
	            }
	        );
		quit.addActionListener(
	            new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	System.exit(0);
	                }
	            }
	        );
	}
	
	/*
	this.getContentPane().setLayout(new GridLayout());
	this.getContentPane().setPreferredSize(new Dimension ( HEIGHT, WIDTH));
	this.getContentPane().add(start);

	this.getContentPane().add(quit);
	addAllActionListners(); 
	*/
	
}
