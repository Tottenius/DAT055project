package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;




public class Menu extends JPanel  {

	public JButton StartButton = new JButton("Start");
	public JButton OptionsButton = new JButton("Options");
	public JButton QuitButton = new JButton("Quit");
	
	
	
	public void render(Graphics g) {
		
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(500,500));
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnto = new Font("arial", Font.BOLD, 50);
		g.setFont(fnto);
		g.setColor(Color.RED);
		g.drawString("WELCOME TO OUR GAME", 200 , 100); //change to Gamepanel.width and height later	

		StartButton.setSize(new Dimension(50,50));
		QuitButton.setSize(new Dimension(50,50));
		OptionsButton.setSize(new Dimension(50,50));
		
		
		
		this.add(StartButton);
		this.add(OptionsButton);
		this.add(QuitButton);
		

		
	}

	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("vi kommer hit igen och igen");
        render(g);
        
    }
	
}
