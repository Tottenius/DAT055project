package viewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.GameSettings;
import model.MusicPlayer;
/**
 * Class that is a JPanel used for specific events, such as a win or death screen for a game.
 * 
 * @author Group 10
 * @version 2020-03-03
 */
public class ReactionScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton RestartButton = new JButton("Restart");
	JButton MenuButton = new JButton("Go to Menu");
	JButton QuitButton = new JButton("Quit");

	// Window size
	private static final int WIDTH = GameSettings.getWidth() +100;
	private static final int HEIGHT = GameSettings.getHeight()+100;

	// En lokal variabel f�r den h�r menyn. Kunde inte komma �t den i de anonyma
	// actionlistnersen annars.
	private final ReactionScreen GameOver = this;

	// From constructor given path to the picture and the String that should be
	// displayed so that we can make diffrent screens!
	private String path;
	private String text;
	private MusicPlayer musicplayer;
	
/**
 * Draws the graphical part of the JPanel, can read in an image and or draw a string of text that can be visualized on the screen.
 * @param
 */
	public void render(final Graphics g) {

		final Font fnto = new Font("Century Gothic", Font.BOLD, 30);
		g.setFont(fnto);
		g.setColor(Color.BLACK);

		// Read in background image
		Image img = null;
		try {
			img = ImageIO.read(new File(this.path)).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, null);
		g.drawString(this.text, (GameSettings.getWidth() / 2) - 140, GameSettings.getHeight() / 2);
	}
/**
 * Constructor takes two parameters that determines what images and what string of text that will be drawn on the JPanel
 * 
 * @param path
 * @param text
 */
	public ReactionScreen(final String path, final String text, MusicPlayer musicplayer) {

		this.path = path;
		this.text = text;
		ReactionScreen.this.musicplayer = musicplayer;
		this.setLayout(new GridBagLayout());
		// Add buttons width actionListeners
		addButtons();
		// Set menu size
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

	}
/**
 * Method handles a Buttons customization.
 * 
 * @param button
 */
	public void ButtonCustomazation(final JButton button) {

		button.setBackground(Color.CYAN);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentY(Component.CENTER_ALIGNMENT);
		button.setVisible(true);
		button.setPreferredSize(new Dimension(GameSettings.getWidth() / 4, GameSettings.getHeight() / 4));
		button.setFont(new Font("Century Gothic", Font.BOLD, 26));
		button.setOpaque(true);

	}

/**
 *  Add anonymous actionsListners to buttons and adds them to the JPanel.
 */
	@SuppressWarnings("unused")
	private void addButtons() {
		this.RestartButton.addActionListener(e -> {
			ProgramStateHandeler.setStateGame();

		});

		this.MenuButton.addActionListener(e -> {
			ReactionScreen.this.musicplayer.StopMusic();
			ProgramStateHandeler.setStateMenu();
			SwingUtilities.getWindowAncestor(ReactionScreen.this.GameOver).dispose();
			new ProgramStateHandeler(null);
		});

		this.QuitButton.addActionListener(e -> {
			System.exit(0);
		});

		// customazation
		ButtonCustomazation(this.RestartButton);
		ButtonCustomazation(this.MenuButton);
		ButtonCustomazation(this.QuitButton);

		// add buttons to the panel
		add(this.RestartButton);
		add(this.MenuButton);
		add(this.QuitButton);
	}
}