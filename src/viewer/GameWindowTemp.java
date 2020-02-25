package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindowTemp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private enum STATE{
		MENU,
		GAME,
		DEATHSCREEN,
		RESTART,
		WIN,
	};

	// gamestate bool
	public static boolean isGameState() {
		return State == STATE.GAME;
	}

	// create states
	private static STATE State = STATE.MENU;
	private Menu menu;

    // create a menubar
	private JMenuBar menubar = new JMenuBar();

    // create a menu 
	private JMenu jmenu = new JMenu("Help"); 
    
    // Menu items 
    static JMenuItem m1, m2, m3, m4; 

    // local reference to it self
    private GameWindowTemp window = this;
    
    // The game panel
    GamePanel gpanel = new GamePanel();
    

    

	
	public GameWindowTemp(){

		if (State == STATE.MENU) { 
			menu = new Menu();
			this.add(menu);
		}
		
		//if gamestate is Game then we start the game;
		if (State == STATE.GAME) {
			this.add(gpanel);
			System.out.println("Vi startar en ny gamePanel");

		}
		
		if (State == STATE.DEATHSCREEN) {
			System.out.println("am here");
			window.dispose();
			GameOverScreen gos = new GameOverScreen();
			this.add(gos);
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
        m3 = new JMenuItem("Restart");
        m4 = new JMenuItem("Quit to main menu");
        
        // Add listeners to buttons
        // Går nog att göra dessa på ett snyggare sätt, var mest för test
        
        //Restart the game
        m3.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
	            System.out.println("Restart");
	           // SetRestartState(); 
	            GameWindowTemp.SetStateGame();
	            window.dispose();
	            new GameWindowTemp(); //we are actually opening another windows this way and keeping options window open can be changed by having start game in own method inside windowtemp
			  } 
			} 
	 );
        //Return to the main menu
        m4.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
	            System.out.println("Go to main menu pressed");
	            GameWindowTemp.SetStateMenu();
	            window.dispose();
	            //Remove all game info
	            //GamePanel.clearAllGameInfo();
	            new GameWindowTemp(); //we are actually opening another windows this way and keeping options window open can be changed by having start game in own method inside windowtemp
			  } 
			} 
	 );
  
        // add menu items to menu 
        jmenu.add(m1); 
        jmenu.add(m2); 
        jmenu.add(m3);
        jmenu.add(m4);      
        
        //add menu to menubar
        menubar.add(jmenu);
        
        //add menubar
        this.setJMenuBar(menubar);
	}
	
	public static void setState( STATE s) {
		State = s;
	}
	public static void SetWinState(){
		State = STATE.WIN;	
}

	public static boolean isWinState() {
		return State == STATE.WIN;
	}
	
	public static void SetStateGame(){
			State = STATE.GAME;	
	}
	
	public static void SetStateMenu(){
		
		State = STATE.MENU;	
}
	
	public static void SetDeathScreenState() {
		State = STATE.DEATHSCREEN;		
	}
	public static boolean isDeathScreenState() {
		return State == STATE.DEATHSCREEN;
	}
	
	public static void SetRestartState() {
		State = STATE.RESTART;		
	}	
	public static boolean isRestartState() {
		return State == STATE.RESTART;
	}
	/*public  void GameOver() {
		this.add(new GameOverScreen());
	}
	*/
	
}
	