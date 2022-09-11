package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serial;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JPanel;

import controller.KeyListenerController;
import model.AbstractAsset;
import model.GameSettings;
import model.ReadInWorld;
import model.StopWatch;


/**
 * A class containing the core part of the game
 *
 * @author Group 10
 * @version 2020-03-13
 */
public class GamePanel extends JPanel {
    //Gamepanels
    public static volatile int numberOfControllers = 0;
    // This window
    final GamePanel gamePanel = this;
    // Read in level
    private ReadInWorld world;
    // Window size
    private static final int WIDTH = GameSettings.getWidth();
    private static final int HEIGHT = GameSettings.getHeight();
    // Size of an AbstractAsset
    private static final int SIZE = GameSettings.getAssetsize();
    // Serial
    @Serial
    private static final long serialVersionUID = 1L;
    // Starting position
    private final Point pos = new Point(0, 0);
    // Current level
    String CurrentLevel;

    private boolean slowTime = false;
    private final int HowManyTries;


    private void initWorld(final Graphics g) {

        final List<AbstractAsset> assets = this.world.getAssetList();
        final List<AbstractAsset> movingAssets = this.world.getMovingAssets();
        for (int i = 0; i < assets.size(); i++) {
            // Get first asset
            // Symbols
            AbstractAsset asset = assets.get(i);
            AbstractAsset movingAsset = movingAssets.get(i);
            // Time for a new row?
            if (this.pos.x == WIDTH) {
                this.pos.x = 0;
                this.pos.y = this.pos.y + SIZE;
            }
            // Paint all assets
            if (asset.getCoords() != this.pos) {
                movingAsset.hasDirections(KeyListenerController.getDirection());
                asset.paintAsset(g, this.gamePanel);
                movingAsset.paintAsset(g, this.gamePanel);
            }
            this.pos.x = this.pos.x + SIZE;
        }
        this.pos.y = 0;
        this.pos.x = 0;
    }

    // 0 = deathScreen , 1= Winscreen
    private void initDeathOrWinScreen(final Graphics g, final String Msg, String path) {

        this.slowTime = true;
        final ReactionScreen gos = new ReactionScreen(path, Msg);
        gos.render(g);
        this.add(gos);

    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (ProgramStateHandeler.isGameState()) {
            initWorld(g);


        } else if (ProgramStateHandeler.isDeadState()) {
            initDeathOrWinScreen(g, "bad", "src/assets/GameOverScreen.jpg");
        } else if (ProgramStateHandeler.isWinState()) {
            final String finishtime = Long.toString(StopWatch.stopTimer());
            initDeathOrWinScreen(g, "It took you " + GamePanel.this.HowManyTries + " tries and " + finishtime + "s" + " to beat the game!", "src/assets/WinScreen.png");
        }
    }

    /**
     * Constructor for the GamePanel class. Sets the currentLevel that shall be loaded in and the Profile name for the session.
     */
    public GamePanel(final String CurrentLevel) {

        this.HowManyTries = 1;

        // read in and build level
        this.CurrentLevel = CurrentLevel;
        this.world = new ReadInWorld(this.CurrentLevel);
        this.world.startControllers();
        // set layout
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.setVisible(true);

        // adding the keylistener
        this.addKeyListener(new KeyListenerController());
        this.setFocusable(true);


        startTimer(0, 1000 / 60);
        StopWatch.start();

    }


    private void startTimer(final int firstTime, final int period) {

        // ms

        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                ProgramStateHandeler.STATE currentState = ProgramStateHandeler.state;

                switch (currentState) {
                    case NextLevel:

                        if (GamePanel.this.CurrentLevel.equals("level9"))
                            GamePanel.this.CurrentLevel = "level10";
                        else {
                            String nextLevelNumber = GamePanel.this.CurrentLevel.substring(GamePanel.this.CurrentLevel.length() - 1);
                            nextLevelNumber = String.valueOf(Integer.parseInt(nextLevelNumber) + 1);
                            GamePanel.this.CurrentLevel = "level" + nextLevelNumber;
                        }

                        GamePanel.this.world = new ReadInWorld(GamePanel.this.CurrentLevel);
                        ProgramStateHandeler.setState(ProgramStateHandeler.STATE.GAME);
                        GamePanel.this.world.startControllers();
                        break;
                    case GAME:
                        if (GamePanel.this.slowTime) {
                            timer.cancel();
                            startTimer(0, 1000 / 60);
                            GamePanel.this.slowTime = false;
                            repaint();
                        } else {
                            repaint();
                        }
                        break;
                    case WIN:
                    case DEAD:
                        timer.cancel();
                        startTimer(5000, 100000);
                        repaint();
                        break;
                    default:
                        timer.cancel();


                }
            }
        }, firstTime, period);
    }

    public static synchronized void incrementNumberOfControllers() {
        numberOfControllers++;
    }

    public static synchronized void decrementNumberOfControllers() {
        numberOfControllers--;
    }
}