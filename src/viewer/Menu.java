package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
	 JButton LeaderboardButton = new JButton("Show Leaderboard");
	 JButton QuitButton = new JButton("Quit");
	 
	 private final String leaderboardpath = "src/leaderboard/leaderboard.txt";
	 
	 //En lokal variabel för den här menyn. Kunde inte komma åt den i de anonyma actionlistnersen annars.
	 private Menu menu = this;
	 // Window size
	 private static final int WIDTH = GameSettings.getWidth();
	 private static final int HEIGHT = GameSettings.getHeight();
	
	 public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnto = new Font("Roboto",Font.BOLD + Font.ITALIC, 48);
		g.setFont(fnto);
		g.setColor(Color.ORANGE);
		
		//Read in background image
		Image img = null;
		try {
			img = ImageIO.read(new File(path)).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img,0,0,null);
		
		g.drawString("LABYRINTH OF SOLITUDE", GameSettings.getWidth() / 5, GameSettings.getHeight() / 3); 
	}	
	 
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);      
    }
	
    public Menu() {
	
    	System.out.println("Vi går in i menyn");
    	System.out.println(GameWindowTemp.state);
    	
    	//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	this.setLayout(new GridBagLayout());  	
    	
        //Add buttons width actionListeners
    	addButtons();
        //Set menu size
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    
    public void ButtonCustomazation(JButton button) {
    	final Color DARK_ORANGE = new Color(255,102,0);
    	//customazation
    	button.setBackground(DARK_ORANGE);
    	button.setOpaque(true);
    	button.setAlignmentX(this.CENTER_ALIGNMENT);
    	button.setAlignmentY(this.CENTER_ALIGNMENT);   	 
    	button.setPreferredSize(new Dimension(GameSettings.getWidth()/5, GameSettings.getHeight()/5));
    	button.setFont(new Font("Century Gothic", Font.BOLD, 15));

    	
    }
 	// Add anonymous actionsListners to buttons. Easier because we don't need lot's of if cases
    private void addButtons() {
   	 StartButton.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
	            System.out.println("Start Button pressed!");           
	            GameWindowTemp.setStateGame();
	            SwingUtilities.getWindowAncestor(menu).dispose();
	            new GameWindowTemp("level1"); //we are actually opening another windows this way and keeping options window open can be changed by having start game in own method inside windowtemp
			  } 
			} 
	 );
	 
   	LeaderboardButton.addActionListener(new ActionListener() { 
 		  public void actionPerformed(ActionEvent e) { 
	            System.out.println("LeaderboardButton pressed!");
	            
	            String leaderboard = fileToString(leaderboardpath);
	           // System.out.println(leaderboard);
	          //custom title, custom icon
	            Icon icon = new ImageIcon("src/assets/LeaderboardIcon.jpg");
	            
	            JOptionPane.showMessageDialog(menu,leaderboard,"Leaderboard",
	            JOptionPane.INFORMATION_MESSAGE,icon);
	            
	            //System.exit(0);
			  } 
			} 
 	 );
	 
	 QuitButton.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
           System.out.println("Quit Button pressed!");
           Main.isRunning = false;
           System.exit(0);
		  } 
		} 
	 );
   
	//customazation  	 
	 ButtonCustomazation(StartButton);
	 ButtonCustomazation(LeaderboardButton);
	 ButtonCustomazation(QuitButton);

	 //add buttons to the panel
	 add(StartButton);
	 add(LeaderboardButton);
	 add(QuitButton);
    }
    
    //utility for leaderboard
    
    private static String fileToString(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}