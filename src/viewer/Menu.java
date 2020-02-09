package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;



//OBS
// blir lite duplicring med GamePanel och att ha draw function i denna aswell finns antagigen bättre sätt att göra detta men som grund testa detta

public class Menu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	JButton StartButton = new JButton("Start");
	 JButton OptionsButton = new JButton("Options");
	 JButton QuitButton = new JButton("Quit"); 
	
	
	 public void render(Graphics g) {
		
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(500,500));
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnto = new Font("Century Gothic", Font.BOLD, 50);
		g.setFont(fnto);
		g.setColor(Color.RED);
		g.drawString("WELCOME TO OUR GAME", 200 , 100); //change to Gamepanel.width and height later		
	}
	
	 
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);      
    }
	
    public Menu() {
    	
    	//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    	   	
        //set action listeners for buttons
    	 StartButton.addActionListener((java.awt.event.ActionListener) this);
    	 OptionsButton.addActionListener((java.awt.event.ActionListener) this);
    	 QuitButton.addActionListener((java.awt.event.ActionListener) this);
        
    	//customazation  	 
    	 ButtonCustomazation(StartButton);
    	 ButtonCustomazation(OptionsButton);
    	 ButtonCustomazation(QuitButton);
	 
    	 //add buttons to the panel
        add(StartButton);
        add(OptionsButton);
        add(QuitButton);
        
        this.setPreferredSize(new Dimension(900,900));
 
    }
    
    public void ButtonCustomazation(JButton button) {
    	
    	//customazation
    	button.setBackground(Color.GREEN);
    	button.setAlignmentX(this.CENTER_ALIGNMENT);
    	button.setAlignmentY(this.CENTER_ALIGNMENT);   	 
    	button.setMaximumSize(new Dimension(200, 120));

    	
    }
 
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Start")) {
            System.out.println("Start Button pressed!");
            GameWindowTemp.SetStateGame();
           new GameWindowTemp(); //we are actually opening another windows this way and keeping options window open can be changed by having start game in own method inside windowtemp
        }
        else if (action.equals("Quit")) {
            System.out.println("Quit Button pressed!");
            System.exit(0);
        }
    }
	
	
	
}
