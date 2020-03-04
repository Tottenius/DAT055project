package viewer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindowTemp extends JFrame {

	private static final long serialVersionUID = 1L;

	// How long it took to complete the level for the player
	Long TimeForCompletion;

	private enum STATE {
		MENU, GAME, DEATHSCREEN, RESTART, WIN, NextLevel,
	}

	// gamestate bool
	public static boolean isGameState() {
		return state == STATE.GAME;
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
	static JMenuItem m1, m2, m3, m4, m5, m6;

	// local reference to it self
	private final GameWindowTemp window = this;

	// menu sound path
	private final String menuSongPath = "src/Music/MainMenuSong.aifc";

	// What level do read in
	String nextLevel = "levelewfkjwofkjiewkijef";

	public String returnNextLevel() {

		return this.nextLevel;
	}

	public GameWindowTemp(final String nextLevel) {

		this.nextLevel = nextLevel;

		System.out.println("Vi gör ett window");
		System.out.println(state + " GameWindowTemp");
		if (state == STATE.MENU) {

			if (MusicPlayer.isMusicRunning()) {
				MusicPlayer.StopMusic();
				MusicPlayer.playSound(this.menuSongPath);
			} else {
				MusicPlayer.playSound(this.menuSongPath);
			}

			this.menu = new Menu();
			this.add(this.menu);
		}

		// if gamestate is Game then we start the game;
		else if (state == STATE.GAME) {

			final Profile profile = new Profile();
			final String ProfileName = profile.returnProfileName();

			if (!ProfileName.equals("canceled")) {

				this.add(new GamePanel(returnNextLevel(), ProfileName));
				System.out.println("Vi startar en ny gamePanel");
			}
			// handles if player exists or presses cancel button when inputing profile name
			else if (ProfileName.equals("canceled")) {
				setStateMenu();
				this.menu = new Menu();
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

	public void SetUpMenubar() {

		// create menuitems
		m1 = new JMenuItem("Guides");
		m2 = new JMenuItem("Support");
		m3 = new JMenuItem("Restart");
		m4 = new JMenuItem("Quit to main menu");
		m5 = new JMenuItem("Increase Volume");
		m6 = new JMenuItem("Decrease Volume");

		// Restart the game
		m3.addActionListener(e -> {

			System.out.println("Restart");
			setRestartState();
		});
		// Return to the main menu
		m4.addActionListener(e -> {

			System.out.println("Go to main menu pressed");
			GameWindowTemp.setStateMenu();
			GameWindowTemp.this.window.dispose();
			new GameWindowTemp(null);
		});

		// Increasing the audio volume
		m5.addActionListener(e -> {

			MusicPlayer.increaseVolume();
			System.out.println("volume increased!");
		});

		// Decreasing the audio volume
		m6.addActionListener(e -> {

			MusicPlayer.decreaseVolume();
			System.out.println("volume Decreased!");
		});

		// add menu items to menu
		this.jmenu.add(m1);
		this.jmenu.add(m2);
		this.jmenu.add(m3);
		this.jmenu.add(m4);
		this.jmenu2.add(m5);
		this.jmenu2.add(m6);

		// add menu to menubar
		this.menubar.add(this.jmenu);
		this.menubar.add(this.jmenu2);

		// add menubar
		this.setJMenuBar(this.menubar);
	}

	public static void setState(final STATE s) {

		state = s;
	}

	public static void setWinState() {
		state = STATE.WIN;
	}

	public static boolean isWinState() {
		return state == STATE.WIN;
	}

	public static void setStateGame() {
		state = STATE.GAME;
	}

	public static void setStateMenu() {
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

	public static void setNextLevelState() {
		System.out.println("we are isnide setnextlevelstate");
		state = STATE.NextLevel;
	}

	public static boolean isNextLevelState() {
		return state == STATE.NextLevel;
	}
}