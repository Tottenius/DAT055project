package viewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import main.Main;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String path = "src/assets/MenuBackground2.jpg";

	JButton StartButton = new JButton("Start");
	JButton LeaderboardButton = new JButton("Leaderboards");
	JButton QuitButton = new JButton("Quit");

	private final String leaderboardpath = "src/leaderboard/leaderboard.txt";

	// En lokal variabel för den här menyn. Kunde inte komma åt den i de anonyma
	// actionlistnersen annars.
	private final Menu menu = this;
	// Window size
	private static final int WIDTH = GameSettings.getWidth();
	private static final int HEIGHT = GameSettings.getHeight();

	public void render(final Graphics g) {

		final Font fnto = new Font("Roboto", Font.BOLD + Font.ITALIC, 48);
		g.setFont(fnto);
		g.setColor(Color.ORANGE);

		// Read in background image
		Image img = null;
		try {
			img = ImageIO.read(new File(path)).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, null);

		g.drawString("LABYRINTH OF SOLITUDE", GameSettings.getWidth() / 5, GameSettings.getHeight() / 3);
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		render(g);
	}

	public Menu() {

		System.out.println("Vi går in i menyn");
		System.out.println(GameWindowTemp.state);

		// this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setLayout(new GridBagLayout());

		// Add buttons width actionListeners
		addButtons();
		// Set menu size
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void ButtonCustomazation(final JButton button) {

		// customazation
		button.setBackground(Color.BLACK);
		button.setOpaque(true);
		button.setForeground(Color.PINK);
		button.setBounds(new Rectangle(20, 20));
		// Work in progress
		// button.setIcon(new ImageIcon("src/assets/button.png"));

		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentY(Component.CENTER_ALIGNMENT);
		button.setPreferredSize(new Dimension(GameSettings.getWidth() / 6, GameSettings.getHeight() / 6));
		button.setFont(new Font("Century Gothic", Font.BOLD, 15));

	}

	// Add anonymous actionsListners to buttons. Easier because we don't need lot's
	// of if cases
	@SuppressWarnings("unused")
	private void addButtons() {
		this.StartButton.addActionListener(e -> {
			System.out.println("Start Button pressed!");
			GameWindowTemp.setStateGame();
			SwingUtilities.getWindowAncestor(Menu.this.menu).dispose();
			new GameWindowTemp("level1"); // we are actually opening another windows this way and keeping options window
											// open can be changed by having start game in own method inside windowtemp
		});

		this.LeaderboardButton.addActionListener(e -> {
			System.out.println("LeaderboardButton pressed!");

			String leaderboard = fileToString(Menu.this.leaderboardpath);     
			
			// custom title, custom icon
			final Icon icon = new ImageIcon("src/assets/LeaderboardIcon.jpg");

			// JLabel label = new JLabel(leaderboard);
			// label.setHorizontalTextPosition(SwingConstants.CENTER);
			// label.setFont(new Font("Arial", Font.BOLD, 12));
			// UIManager.put("OptionPane.minimumSize",new Dimension(200,200));

			JOptionPane.showMessageDialog(Menu.this.menu, leaderboard, "Leaderboard", JOptionPane.INFORMATION_MESSAGE,
					icon);

			// System.exit(0);
		});

		this.QuitButton.addActionListener(e -> {
			System.out.println("Quit Button pressed!");
			Main.isRunning = false;
			System.exit(0);
		});

		// customazation
		ButtonCustomazation(this.StartButton);
		ButtonCustomazation(this.LeaderboardButton);
		ButtonCustomazation(this.QuitButton);

		// add buttons to the panel
		add(this.StartButton);
		add(this.LeaderboardButton);
		add(this.QuitButton);
	}

	// utility for leaderboard

	private static String fileToString(final String filePath) {
		final StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
}