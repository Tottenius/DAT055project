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

import button.PlayState;

public class GameWindowTemp extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// La till knappar och storlek  f�r rutan s� jag kunde leka lite med en meny. Men det uppstod komplikationer
	final static int HEIGHT = 640;
	final static int WIDTH = 480;
	private JButton start = new JButton("Start");
	private JButton quit = new JButton("Quit");
	

	//start.setFocusable(false);
	//quit.setFocusable(false);
	
	public GameWindowTemp(){
	
		/*
		this.getContentPane().setLayout(new GridLayout());
		this.getContentPane().setPreferredSize(new Dimension ( HEIGHT, WIDTH));
		this.getContentPane().add(start);

		this.getContentPane().add(quit);
		addAllActionListners(); 
		*/
		
		
		this.add(new GamePanel());
		
		this.pack();		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	//F�rs�kt att g�ra s� att man startar spelet p� en knapp. Men keylistners f�r spelet funkar inte n�r man genererar GamePanel s�h�r av n�gon anledning....
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
	
}
