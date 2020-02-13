package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import main.Main;


public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private static final String path = "src/assets/MenuBackground.jpg";
	
	 JButton StartButton = new JButton("Start");
	 JButton OptionsButton = new JButton("Options");
	 JButton QuitButton = new JButton("Quit");
	 //En lokal variabel för den här menyn. Kunde inte komma åt den i de anonyma actionlistnersen annars.
	 private Menu menu = this;
	 // Window size
	 private static final int WIDTH = GameSettings.getWidth();
	 private static final int HEIGHT = GameSettings.getHeight();
	
	
	 public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnto = new Font("Century Gothic", Font.BOLD, 50);
		g.setFont(fnto);
		g.setColor(Color.BLACK);
		
		//Read in background image
		Image img = null;
		try {
			img = ImageIO.read(new File(path)).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img,0,0,null);
		
		g.drawString("WELCOME TO OUR GAME", GameSettings.getWidth() / 4, GameSettings.getHeight() / 3); 
	}
	
	 
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);      
    }
	
    public Menu() {
    	
    	//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	this.setLayout(new GridBagLayout());  	
    	
        //Add buttons width actionListeners
    	addButtons();
        //Set menu size
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    
    public void ButtonCustomazation(JButton button) {
    	
    	//customazation
    	button.setBackground(Color.CYAN);
    	button.setAlignmentX(this.CENTER_ALIGNMENT);
    	button.setAlignmentY(this.CENTER_ALIGNMENT);   	 
    	button.setPreferredSize(new Dimension(GameSettings.getWidth()/6, GameSettings.getHeight()/6));
    	button.setFont(new Font("Century Gothic", Font.BOLD, 18));

    	
    }
 	// Add anonymous actionsListners to buttons. Easier because we don't need lot's of if cases
    private void addButtons() {
   	 StartButton.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
	            System.out.println("Start Button pressed!");
	            GameWindowTemp.SetStateGame();
	            SwingUtilities.getWindowAncestor(menu).dispose();
	            new GameWindowTemp(); //we are actually opening another windows this way and keeping options window open can be changed by having start game in own method inside windowtemp
			  } 
			} 
	 );
	 
	 OptionsButton.addActionListener(new ActionListener() { 
 		  public void actionPerformed(ActionEvent e) { 
	            System.out.println("Options Button pressed!");
	            Main.isRunning = false;
	            System.exit(0);
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
	 ButtonCustomazation(OptionsButton);
	 ButtonCustomazation(QuitButton);

	 //add buttons to the panel
	 add(StartButton);
	 add(OptionsButton);
	 add(QuitButton);
    }
	
}
