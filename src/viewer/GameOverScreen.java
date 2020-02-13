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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.Main;

public class GameOverScreen extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;	
		
		private static final String path = "src/assets/GameOverScreen.jpg";
		
		 JButton RestartButton = new JButton("Restart");
		 JButton MenuButton = new JButton("Go to Menu");
		 JButton QuitButton = new JButton("Quit");

		 // Window size
		 private static final int WIDTH = GameSettings.getWidth();
		 private static final int HEIGHT = GameSettings.getHeight();
		 
		 //En lokal variabel för den här menyn. Kunde inte komma åt den i de anonyma actionlistnersen annars.
		 private GameOverScreen GameOver = this;
		
		
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
		
	    public GameOverScreen() {
	    	
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
	    	RestartButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
		            System.out.println("Rest Button pressed!");
		            //GameWindowTemp.SetStateGame();
		         //  SwingUtilities.getWindowAncestor(GameOver).dispose();
		        //    new GameWindowTemp(); //we are actually opening another windows this way and keeping options window open can be changed by having start game in own method inside windowtemp
				  }  
				} 
		 );
		 
	    	MenuButton.addActionListener(new ActionListener() { 
	 		  public void actionPerformed(ActionEvent e) { 
		            System.out.println("Menu Button pressed!");
		            GameWindowTemp.SetStateMenu();
		            SwingUtilities.getWindowAncestor(GameOver).dispose();
		            new GameWindowTemp();
				  } 
				} 
	 	 );
		 
		 QuitButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
	           System.out.println("Quit Button pressed!");
	           System.exit(0);
			  } 
			} 
		 );
	   
		//customazation  	 
		 ButtonCustomazation(RestartButton);
		 ButtonCustomazation(MenuButton);
		 ButtonCustomazation(QuitButton);

		 //add buttons to the panel
		 add(RestartButton);
		 add(MenuButton);
		 add(QuitButton);
	    }
		
	}

