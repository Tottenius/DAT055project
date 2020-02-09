package viewer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

    // create a menubar
	private JMenuBar menubar = new JMenuBar();

    // create a menu 
	private JMenu jmenu = new JMenu("Help"); 
    
    // Menu items 
    static JMenuItem m1, m2, m3; 

	//final static int HEIGHT = 640;
	//final static int WIDTH = 480;
	
	public GameWindowTemp(){

		if (State == STATE.MENU) { 
			menu = new Menu();
			this.add(menu);
		}
		
		//if gamestate is Game then we start the game;
		if (State == STATE.GAME) { 
		this.add(new GamePanel());
		}
		
		//Adding menubar
		SetUpMenubar();
		
		this.pack();		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void SetUpMenubar() {
		
		// create menuitems 
        m1 = new JMenuItem("Guides"); 
        m2 = new JMenuItem("Support"); 
        m3 = new JMenuItem("Playbetteritsthateasy"); 
  
        // add menu items to menu 
        jmenu.add(m1); 
        jmenu.add(m2); 
        jmenu.add(m3); 
        
        //add menu to menubar
        menubar.add(jmenu);
        
        //add menubar
        this.setJMenuBar(menubar);
	}
	
	public static void SetStateGame(){
			State = STATE.GAME;	
	}
	
	public static void SetStateMenu(){
		
		State = STATE.MENU;	
}
}
	