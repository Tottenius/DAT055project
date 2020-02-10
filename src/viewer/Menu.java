package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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



//OBS
// blir lite duplicring med GamePanel och att ha draw function i denna aswell finns antagigen b�ttre s�tt att g�ra detta men som grund testa detta

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private static final String path = "src/assets/MenuBackground.jpg";
	
	 JButton StartButton = new JButton("Start");
	 JButton OptionsButton = new JButton("Options");
	 JButton QuitButton = new JButton("Quit");
	 //En lokal variabel f�r den h�r menyn. Kunde inte komma �t den i de anonyma actionlistnersen annars.
	 private Menu menu = this;
	 // Window size
	 private static final int WIDTH = GameSettings.getWidth();
	 private static final int HEIGHT = GameSettings.getHeight();
	
	
	 public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnto = new Font("Century Gothic", Font.BOLD, 50);
		g.setFont(fnto);
		g.setColor(Color.RED);
		g.drawString("WELCOME TO OUR GAME", 200 , 100); //change to Gamepanel.width and height later
		
		//Read in background image
		Image img = null;
		try {
			img = ImageIO.read(new File(path)).getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img,0,0,null);
	}
	
	 
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);      
    }
	
    public Menu() {
    	
    	//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	   	
        //Add buttons width actionListeners
    	addButtons();
        //Set menu size
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }
    
    public void ButtonCustomazation(JButton button) {
    	
    	//customazation
    	button.setBackground(Color.GREEN);
    	button.setAlignmentX(this.CENTER_ALIGNMENT);
    	button.setAlignmentY(this.CENTER_ALIGNMENT);   	 
    	button.setMaximumSize(new Dimension(200, 120));

    	
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
	            System.out.println("Start Button pressed!");
	            System.out.println("Quit Button pressed!");
	            System.exit(0);
			  } 
			} 
 	 );
	 
	 QuitButton.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
           System.out.println("Start Button pressed!");
           System.out.println("Quit Button pressed!");
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
