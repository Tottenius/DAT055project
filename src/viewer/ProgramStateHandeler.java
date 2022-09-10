package viewer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * A class that is used as an intermediate between the menu and the Main game class. Keeps a track of what State the program is in and also is the main JFrame that we then add our JPanel containing the game into.
 *
 * @author Group 10
 * @version 2020-03-13
 */
public class ProgramStateHandeler extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    public enum STATE {
        MENU, GAME, DEAD, WIN, NextLevel,
    }

    @Setter
    public static STATE state = STATE.MENU;

    private final JMenuBar menubar = new JMenuBar();

    private final JMenu jmenu = new JMenu("Help");

    static JMenuItem m1, m2, m3, m4;

    private final ProgramStateHandeler window = this;

    @Getter
    private final String currentLevel;

    public ProgramStateHandeler(final String level) {

        this.currentLevel = level;

        Menu menu;
        if (state == STATE.MENU) {
            menu = new Menu(getCurrentLevel());
            this.add(menu);
        } else if (state == STATE.GAME) {
            this.add(new GamePanel(getCurrentLevel(), "Hello"));
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
        m4 = new JMenuItem("Quit to main menu");

        m1.addActionListener(e -> {

            final Icon guidesicon = new ImageIcon("src/assets/GuidesIcon.jpg");
            JOptionPane.showMessageDialog(ProgramStateHandeler.this.window, "For help on getting good at this game, please see: \n https://www.wikihow.com/Become-a-Master-Gamer",
                    "Guide", JOptionPane.INFORMATION_MESSAGE, guidesicon);

        });

        m2.addActionListener(e -> {
            final Icon supporticon = new ImageIcon("src/assets/SupportIcon.png");
            JOptionPane.showMessageDialog(ProgramStateHandeler.this.window,
                    """

                             ”Character consists of what you do on the third and fourth tries.”\s
                             -James A. Michener\s
                            \s
                             “Winners never quit, and quitters never win.”\s
                             -Vince Lombardi\s
                            \s
                             “It always seems impossible until it’s done.”\s
                             -Nelson Mandela\s
                            \s
                             “You just can’t beat the person who won’t give up.”\s
                             -Babe Ruth\s
                            \s
                            """,
                    "Support", JOptionPane.INFORMATION_MESSAGE,
                    supporticon);
        });

        // Return to the main menu
        m4.addActionListener(e -> {
            ProgramStateHandeler.setState(STATE.MENU);
            ProgramStateHandeler.this.window.dispose();
            new ProgramStateHandeler("level1");
        });


        // add menu items to menu
        this.jmenu.add(m1);
        this.jmenu.add(m2);
        this.jmenu.add(m4);


        // add menu to menubar
        this.menubar.add(this.jmenu);

        // add menubar
        this.setJMenuBar(this.menubar);
    }

    /**
     * Check whether the state is winState.
     *
     * @return true if state is WinState.
     */
    public static boolean isWinState() {
        return state == STATE.WIN;
    }

    /**
     * Check whether the state is winState.
     *
     * @return true if state is WinState.
     */
    public static boolean isDeadState() {
        return state == STATE.DEAD;
    }

    /**
     * Method to tell if the game is in the State Game.
     *
     * @return True if gamestate is Game, False otherwise.
     */
    public static boolean isGameState() {
        return state == STATE.GAME;
    }

    /**
     * Check whether the state is NextLevelState.
     *
     * @return true if state is NextLevelState.
     */
    public static boolean isNextLevelState() {
        return state == STATE.NextLevel;
    }
}