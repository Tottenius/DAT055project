package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



//OBS
// blir lite duplicring med GamePanel och att ha draw function i denna aswell finns antagigen bättre sätt att göra detta men som grund testa detta

public class Menu extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	
	public void render(Graphics g) {
		
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(500,500));
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnto = new Font("arial", Font.BOLD, 50);
		g.setFont(fnto);
		g.setColor(Color.RED);
		g.drawString("WELCOME TO OUR GAME", 200 , 100); //change to Gamepanel.width and height later	
		
		
		/*
		StartButton.setSize(new Dimension(50,50));
		QuitButton.setSize(new Dimension(50,50));
		OptionsButton.setSize(new Dimension(50,50));
		
		
	/*	
		this.add(StartButton);
		this.add(OptionsButton);
		this.add(QuitButton); */
		
	}
	

	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("vi kommer hit igen och igen");
        render(g);
        
    }
	
    public Menu() {
    	 
    	 JButton StartButton = new JButton("Start");
    	 JButton OptionsButton = new JButton("Options");
    	 JButton QuitButton = new JButton("Quit"); 
    	
        //set action listeners for buttons
    	 StartButton.addActionListener((java.awt.event.ActionListener) this);
    	 OptionsButton.addActionListener((java.awt.event.ActionListener) this);
    	 QuitButton.addActionListener((java.awt.event.ActionListener) this);
        //add buttons to the frame
        add(StartButton);
        add(OptionsButton);
        add(QuitButton);
 
    }
 
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action.equals("Start")) {
            System.out.println("Start Button pressed!");
            GameWindowTemp.SetStateGame();
           new GameWindowTemp();
        }
        else if (action.equals("Quit")) {
            System.out.println("Quit Button pressed!");
        }
    }
	
	
	
}
