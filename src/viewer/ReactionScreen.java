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


public class ReactionScreen extends JPanel {

		private static final long serialVersionUID = 1L;		
		
		 JButton RestartButton = new JButton("Restart");
		 JButton MenuButton = new JButton("Go to Menu");
		 JButton QuitButton = new JButton("Quit");

		 // Window size
		 private static final int WIDTH = GameSettings.getWidth() ;
		 private static final int HEIGHT = GameSettings.getHeight();
		 
		 //En lokal variabel f�r den h�r menyn. Kunde inte komma �t den i de anonyma actionlistnersen annars.
		 private ReactionScreen GameOver = this;
		 
		 //From constructor given path to the picture and the String that should be displayed so that we can make diffrent screens!
		 String path;
		 String text;
		
		 public void render(Graphics g) {
			
			Font fnto = new Font("Century Gothic", Font.BOLD, 30);
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
			g.drawString(text, GameSettings.getWidth() / 2, GameSettings.getHeight() / 3); 
		}
		
		 /*
		@Override
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        render(g);      
	    }
		*/
		 
	    public ReactionScreen(String path, String text) {
	    	
	    	this.path = path;
	    	this.text = text;
	    	this.setLayout(new GridBagLayout());  		    	
	        //Add buttons width actionListeners
	    	addButtons();
	        //Set menu size
	        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	        
	    }
	    
	    public void ButtonCustomazation(JButton button) {
	    	//System.out.println("inside button custom");
	    	//Customization
	    	button.setBackground(Color.CYAN);
	    	//button.setAlignmentX(this.CENTER_ALIGNMENT);
	    	//button.setAlignmentY(this.CENTER_ALIGNMENT);  
	    	button.setAlignmentX(200);
	    	button.setAlignmentX(200);
	    	
	    	button.setPreferredSize(new Dimension(GameSettings.getWidth()/4, GameSettings.getHeight()/4));
	    	button.setFont(new Font("Century Gothic", Font.BOLD, 26));
	    	
	    }
	 	// Add anonymous actionsListners to buttons. Easier because we don't need lot's of if cases
	    private void addButtons() {
	    	RestartButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
		            System.out.println("Rest Button pressed!");
		            
				  }  
				} 
		 );
		 
	    	MenuButton.addActionListener(new ActionListener() { 
	 		  public void actionPerformed(ActionEvent e) { 
		            System.out.println("Menu Button pressed!");
		            GameWindowTemp.setStateMenu();
		            SwingUtilities.getWindowAncestor(GameOver).dispose();
		            new GameWindowTemp(null);
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

