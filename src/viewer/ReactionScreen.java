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
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.GameSettings;

/**
 * Class that is a JPanel used for specific events, such as a win or death screen for a game.
 *
 * @author Group 10
 * @version 2020-03-03
 */
public class ReactionScreen extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    final JButton MenuButton = new JButton("Go to Menu");
    final JButton QuitButton = new JButton("Quit");

    // Window size
    private static final int WIDTH = GameSettings.getWidth() + 100;
    private static final int HEIGHT = GameSettings.getHeight() + 100;

    private final ReactionScreen GameOver = this;

    // From constructor given path to the picture and the String that should be
    // displayed so that we can make diffrent screens!
    private final String path;
    private final String text;

    /**
     * Draws the graphical part of the JPanel, can read in an image and or draw a string of text that can be visualized on the screen.
     *
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
     */
    public ReactionScreen(final String path, final String text) {

        this.path = path;
        this.text = text;
        this.setLayout(new GridBagLayout());
        // Add buttons width actionListeners
        addButtons();
        // Set menu size
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }

    /**
     * Method handles a Buttons customization.
     *
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
     * Add anonymous actionsListners to buttons and adds them to the JPanel.
     */
    @SuppressWarnings("unused")
    private void addButtons() {
        this.MenuButton.addActionListener(e -> {
            ProgramStateHandeler.setState(ProgramStateHandeler.STATE.MENU);
            SwingUtilities.getWindowAncestor(ReactionScreen.this.GameOver).dispose();
            new ProgramStateHandeler(null);
        });

        this.QuitButton.addActionListener(e -> System.exit(0));

        // customazation
        ButtonCustomazation(this.MenuButton);
        ButtonCustomazation(this.QuitButton);

        // add buttons to the panel
        add(this.MenuButton);
        add(this.QuitButton);
    }
}