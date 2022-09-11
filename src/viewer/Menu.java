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
import java.io.Serial;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.Main;
import model.GameSettings;

/**
 * A class for creating a menu.
 *
 * @author Group 10
 * @version 2020-03-13
 */
public class Menu extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String path = "src/assets/MenuBackground2.jpg";
    private final String level;

    final JButton StartButton = new JButton("Start");
    final JButton QuitButton = new JButton("Quit");


    private final Menu menu = this;
    // Window size
    private static final int WIDTH = GameSettings.getWidth();
    private static final int HEIGHT = GameSettings.getHeight();

    /**
     * Draws the graphical part of the menu
     */
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

    public Menu(String level) {
        this.level = level;
        this.setLayout(new GridBagLayout());

        // Add buttons width actionListeners
        addButtons();
        // Set menu size
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void ButtonCustomazation(final JButton button) {

        // customazation
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setForeground(Color.PINK);
        button.setBounds(new Rectangle(20, 20));
        // Work in progress

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(GameSettings.getWidth() / 6, GameSettings.getHeight() / 6));
        button.setFont(new Font("Century Gothic", Font.BOLD, 15));

    }

    @SuppressWarnings("unused")
    private void addButtons() {
        //For start button
        this.StartButton.addActionListener(e -> {
            ProgramStateHandeler.setState(ProgramStateHandeler.STATE.GAME);
            SwingUtilities.getWindowAncestor(Menu.this.menu).dispose();
            new ProgramStateHandeler(level);
        });

        //For quit button
        this.QuitButton.addActionListener(e -> {
            System.exit(0);
        });

        // customazation
        ButtonCustomazation(this.StartButton);
        ButtonCustomazation(this.QuitButton);

        // add buttons to the panel
        add(this.StartButton);
        add(this.QuitButton);
    }


}