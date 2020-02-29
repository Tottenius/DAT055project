package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindowTemp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//How long it took to complete the level for the player 
	Long TimeForCompletion ;
	 
    private static final String GameOverpath = "src/assets/GameOverScreen.jpg";
    private static final String Winpath = "src/assets/WinScreenTemp.jpg";
	
	private enum STATE{
		MENU,
		GAME,
		DEATHSCREEN,
		RESTART,
		WIN,
	};

	// gamestate bool
	public static boolean isGameState() {
		return state == STATE.GAME;
	}

	// create states
	public static STATE state = STATE.MENU;
	private Menu menu;

    // create a menubar
	private JMenuBar menubar = new JMenuBar();

    // create a menu 
	private JMenu jmenu = new JMenu("Help");
	private JMenu jmenu2 = new JMenu("Volume");
    
    // Menu items 
    static JMenuItem m1, m2, m3, m4,m5,m6; 

    // local reference to it self
    private GameWindowTemp window = this;
    
    //Music player obj so we avoid statics
  //private final  MusicPlayer musicplayer = new MusicPlayer();
	
	public GameWindowTemp(){
		System.out.println("Vi gör ett window");
		System.out.println(state + " GameWindowTemp");
		if (state == STATE.MENU) { 
			menu = new Menu();
			this.add(menu);
		}
		
		//if gamestate is Game then we start the game;
		else if (state == STATE.GAME) {
			this.add(new GamePanel());	
			System.out.println("Vi startar en ny gamePanel");
		}
		
		else if (state == STATE.DEATHSCREEN) {
			System.out.println("am here");
			window.dispose();
			ReactionScreen gos = new ReactionScreen(GameOverpath, "YOU LOST!");
			this.add(gos);
		}
		
		else if (state == STATE.WIN) {
			System.out.println("am here in win state");
			Long TimeForCompletion = StopWatch.stopTimer(); 
			window.dispose();
			ReactionScreen gos = new ReactionScreen(Winpath, "You won the Game, nice work! It took you: " + TimeForCompletion + " seconds to beat the level!");
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
        m1 = new JMenuItem("Guides");	m2 = new JMenuItem("Support"); 
        m3 = new JMenuItem("Restart"); 	m4 = new JMenuItem("Quit to main menu"); 
        m5 = new JMenuItem("Increase Volume");	
        m6 = new JMenuItem("Decrease Volume");
         
        //Restart the game
        m3.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
	           
  			  	System.out.println("Restart");
	            setRestartState(); 
			  } 
			} 
	 );
        //Return to the main menu
        m4.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
	           
  			  	System.out.println("Go to main menu pressed");
	            GameWindowTemp.setStateMenu();
	            window.dispose();
	            new GameWindowTemp(); 
			  } 
			} 
	 );
        //Increasing the audio volume
        m4.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
	            
  			  	System.out.println("Go to main menu pressed");
	            GameWindowTemp.setStateMenu();
	            window.dispose();
	            new GameWindowTemp(); 
			  } 
			} 
	 );
        //Increasing the audio volume
        m5.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
  			
  			  MusicPlayer.increaseVolume(); 			  
  			  System.out.println("volume increased!");
			  } 
			} 
	 );
        
        //Decreasing the audio volume
        m6.addActionListener(new ActionListener() { 
  		  public void actionPerformed(ActionEvent e) { 
	            
  			  MusicPlayer.decreaseVolume();
  			  System.out.println("volume Decreased!");
			  } 
			} 
	 );
  
        // add menu items to menu 
        jmenu.add(m1);	jmenu.add(m2);	
        jmenu.add(m3);  jmenu.add(m4);  
        jmenu2.add(m5); jmenu2.add(m6);
        
        //add menu to menubar
        menubar.add(jmenu);
        menubar.add(jmenu2);
        
        //add menubar
        this.setJMenuBar(menubar);
	}
	
	public static void setState( STATE s) {
		state = s;
	}
	public static void setWinState(){
		state = STATE.WIN;	
}

	public static boolean isWinState() {
		return state == STATE.WIN;
	}
	
	public static void setStateGame(){
			state = STATE.GAME;	
	}
	
	public static void setStateMenu(){	
		state = STATE.MENU;	
	}
	
	public static void setDeathScreenState() {
		state = STATE.DEATHSCREEN;		
	}
	
	public static boolean isDeathScreenState() {
		return state == STATE.DEATHSCREEN;
	}
	
	public static void setRestartState() {
		state = STATE.RESTART;		
	}	
	
	public static boolean isRestartState() {
		return state == STATE.RESTART;
	}
}