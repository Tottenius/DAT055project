package viewer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.MusicPlayer;

/**
 * A class that is used as an intermediate between the menu and the Main game class. Keeps a track of what State the program is in and also is the main JFrame that we then add our JPanel containing the game into. 
 * 
 * @author Group 10
 *
 */
public class GameWindowTemp extends JFrame {

	private static final long serialVersionUID = 1L;

	// How long it took to complete the level for the player
	Long TimeForCompletion;

	private enum STATE {
		MENU, GAME, DEATHSCREEN, RESTART, WIN, NextLevel, SAVE,
	} 

	// create states
	public static STATE state = STATE.MENU;
	private Menu menu;

	// create a menubar
	private final JMenuBar menubar = new JMenuBar();

	// create a menu
	private final JMenu jmenu = new JMenu("Help");
	private final JMenu jmenu2 = new JMenu("Volume");

	// Menu items
	static JMenuItem m1, m2, m3, m4, m5, m6, m7;

	// local reference to it self
	private final GameWindowTemp window = this;

	// What level do read in
	private String nextLevel = "level1";
	
	 private MusicPlayer musicplayer;

	private String returnNextLevel() {

		return this.nextLevel;
	}


	public GameWindowTemp(final String nextLevel) {
		
		this.nextLevel = nextLevel;
		musicplayer = new MusicPlayer();
		
		if (state == STATE.MENU) {	
			if (GameWindowTemp.this.musicplayer.isMusicRunning()) {
				GameWindowTemp.this.musicplayer.StopMusic();
				GameWindowTemp.this.musicplayer.playMusic("menu");
			} else {
				GameWindowTemp.this.musicplayer.playMusic("menu");
			}		
			this.menu = new Menu(returnNextLevel());
			this.add(this.menu);
		}

		// if gamestate is Game then we start the game;
		else if (state == STATE.GAME) {
			
			final Profile profile = new Profile();
			final String ProfileName = profile.returnProfileName();

			if (!ProfileName.equals("canceled")) {

				this.add(new GamePanel(returnNextLevel(), ProfileName, GameWindowTemp.this.musicplayer));
			}
			// handles if player exists or presses cancel button when inputing profile name
			else if (ProfileName.equals("canceled")) {
				setStateMenu();
				this.menu = new Menu(returnNextLevel());
				this.add(this.menu);
			}
		}

		// Adding menubar
		SetUpMenubar();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void SetUpMenubar() {

		// create menuitems
		m1 = new JMenuItem("Guides");
		m2 = new JMenuItem("Support");
		m3 = new JMenuItem("Restart");
		m4 = new JMenuItem("Quit to main menu");
		m5 = new JMenuItem("Increase Volume");
		m6 = new JMenuItem("Decrease Volume");
		m7 = new JMenuItem("Save Game");
		
		m1.addActionListener(e -> {
			
			final Icon guidesicon = new ImageIcon("src/assets/GuidesIcon.jpg");
			JOptionPane.showMessageDialog(GameWindowTemp.this.window,"For help on getting good at this game, please see: \n https://www.wikihow.com/Become-a-Master-Gamer",
					"Guide",JOptionPane.INFORMATION_MESSAGE,guidesicon);
			
		});
		
		m2.addActionListener(e -> {
			final Icon supporticon = new ImageIcon("src/assets/SupportIcon.png");
			JOptionPane.showMessageDialog(GameWindowTemp.this.window,
					"\n ”Character consists of what you do on the third and fourth tries.” \n -James A. Michener \n \n "
					+ "“Winners never quit, and quitters never win.” \n -Vince Lombardi \n \n "
					+ "“It always seems impossible until it’s done.” \n -Nelson Mandela \n \n "
					+ "“You just can’t beat the person who won’t give up.” \n -Babe Ruth \n \n", 	
					"Support", JOptionPane.INFORMATION_MESSAGE,
					supporticon);
		});

		// Restart the game
		m3.addActionListener(e -> {

			setRestartState();
		});
		// Return to the main menu
		m4.addActionListener(e -> {					
			GameWindowTemp.setStateMenu();
			GameWindowTemp.this.window.dispose();
			new GameWindowTemp("level1");
		});

		// Increasing the audio volume
		m5.addActionListener(e -> {

			musicplayer.increaseVolume();
		});

		// Decreasing the audio volume
		m6.addActionListener(e -> {

			musicplayer.decreaseVolume();
		});
		// Decreasing the audio volume
		m7.addActionListener(e -> {
			if(isGameState() )
			GameWindowTemp.setSaveState();
		});

		// add menu items to menu
		this.jmenu.add(m1);
		this.jmenu.add(m2);
		this.jmenu.add(m3);
		this.jmenu.add(m7);
		this.jmenu.add(m4);		
		this.jmenu2.add(m5);
		this.jmenu2.add(m6);

		// add menu to menubar
		this.menubar.add(this.jmenu);
		this.menubar.add(this.jmenu2);

		// add menubar
		this.setJMenuBar(this.menubar);
	}
	
	/**
	 * Sets the state to WinState.
	 */
	public static void setWinState() {
		state = STATE.WIN;
	}
	/**
	 * Check whether the state is winState.
	 * @return true if state is WinState.
	 */
	public static boolean isWinState() {
		return state == STATE.WIN;
	}
	/**
	 * Sets the state to GameState.
	 */
	public static void setStateGame() {
		state = STATE.GAME;
	}

	/**
	 * Method to tell if the game is in the State Game.
	 * @return True if gamestate is Game, False otherwise.
	 */
	public static boolean isGameState() {
		return state == STATE.GAME;
	}

	/**
	 * Sets the state to MenuState.
	 */
	public static void setStateMenu() {
		state = STATE.MENU;
	}
	/**
	 * Sets the state to DeathScreenState.
	 */
	public static void setDeathScreenState() {
		state = STATE.DEATHSCREEN;
	}
	/**
	 * Check whether the state is DeathScreenState.
	 * @return true if state is DeathScreenState.
	 */
	public static boolean isDeathScreenState() {
		return state == STATE.DEATHSCREEN;
	}
	/**
	 * Sets the state to RestartState.
	 */
	public static void setRestartState() {
		state = STATE.RESTART;
	}
	/**
	 * Check whether the state is RestartState.
	 * @return true if state is RestartState.
	 */
	public static boolean isRestartState() {
		return state == STATE.RESTART;
	}
	/**
	 * Sets the state to NextLevelState.
	 */
	public static void setNextLevelState() {
		state = STATE.NextLevel;
	}
	/**
	 * Check whether the state is NextLevelState.
	 * @return true if state is NextLevelState.
	 */
	public static boolean isNextLevelState() {
		return state == STATE.NextLevel;
	}
	/**
	 * Sets the state to SAVE
	 */
	public static void setSaveState() {
		state = STATE.SAVE;
	}
	/**
	 * Check whether the state is SAVE.
	 * @return true if state is SAVE.
	 */
	public static boolean isSaveLevelState() {
		return state == STATE.SAVE;
	}
}