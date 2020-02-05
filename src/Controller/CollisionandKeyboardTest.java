package Controller;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JComponent;




public class CollisionandKeyboardTest extends JPanel  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int xCord = 250;
	int yCord = 250;
	
	private ball ball1 = new ball(10,10);
	
	public CollisionandKeyboardTest() {
		
		System.out.println("inside constructor ");
		
		// generall frame code 
		JFrame frame = new JFrame("Collision");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setPreferredSize(new Dimension(500,500));
     Container contentPane = frame.getContentPane();
     contentPane.setPreferredSize(new Dimension(500,500));    
     contentPane.setBackground(Color.BLUE);         
    //frame.add(contentPane);  
     frame.pack();
     frame.setLocationRelativeTo(null);
 
     //adding the keylisteners
     frame.addKeyListener(new keyLis());
     frame.setFocusable(true);
    
     //draw
     contentPane.add(new drawings());
     
     frame.setVisible(true); 
	}
	/*
	 * 
	 */

	public class drawings extends JPanel {   //we dont really need this class
    
		public void paintComponent(Graphics g) {
    	System.out.println("inside paintComponent");
    	super.paintComponent(g);
        DrawMap(g);
    }
    
    
    //Draw gamemap should have diffrent methods to call to diffrent assets
    public void DrawMap(Graphics g) {  
    	
    	Asset asset = new Asset(50, 50);
    	
    	System.out.println("inside DrawMap");
    	g.setColor(Color.GREEN);
    	g.fillRect(0, 0, 250, 250);
    	g.setColor(Color.RED);
    	g.fillRect(300, 0, 250, 250);
    	g.setColor(Color.YELLOW);
    	g.fillRect(300, 300, 250, 250);
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 300, 250, 250);
    	g.setColor(Color.WHITE);
    	g.fillOval(ball1.giveX(), ball1.giveY(), 50, 50);  //
    	
    }
	}
    
	
	private class keyLis extends KeyAdapter{
	
		
		public void keyPressed(KeyEvent e) {

			System.out.println("inside keylist class");
			
       //If game is completed then keyInputs are ignored
        /*	if (Completed) {   
                return;
            }*/

            int input = e.getKeyCode();

            switch (input) {
                
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                	
                    System.out.println("Moved Up");
                    
                    //test
                    ball1.move(10,10);
                    
                	
                /*	 if (checkCollisions()) {    //if we collied with wall or other object than do nothing
                    return;
                } */
                
                	// player.move(); 				//Method to move character if no collision
                    
                    break;
                    
                    
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
   
                    System.out.println("Moved Down");

                	
                	/* if (checkCollisions()) {    //if we collied with wall or other object than do nothing
                    return;
                } */
                
                	// player.move(); 			//Method to move character if no collision
                    
                    break;
                    
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:	
                   
                	System.out.println("Moved right");

                	
                 /*    if (checkCollisions()) {    //if we collied with wall or other object than do nothing
                    return;
                }
                */
                	// player.move();			//Method to move character if no collision
                    
                    break;
                    
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                	
                  System.out.println("Moved Left");
                	
                /*	 if (checkCollisions()) {    //if we collied with wall or other object than do nothing
                        return;
                    } */
                    
                	// player.move(); 			//Method to move character if no collision
                    
                    break;
                    
                case KeyEvent.VK_X: 		 //Change x to be button to reset level, most intuitively 'R'
                    
                  
                	// resetLevel(); 		//Written resetLevel Method
                    
                    break;
                    
                case KeyEvent.VK_ESCAPE: 		//Esc button
                	 
                	//openMenu(); 			  //Possible method to open menu
                	
                	break;
                    
                    
                default:
                    break;
            }
            System.out.println("inside repaint");
            //System.out.println(ball1.giveX());
            repaint(); // TO redraw everything because we have moved something, we will repaint even if nothing happens but not that big of a deal 
        }	
	}
/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
        
        /*
    private boolean checkCollisions() {

        Rectangle rectangle1 = player.getBounds(); // player and objects need an getBounds method 

        for (Player player : players) {
            
            Rectangle rectangle2 = object.getBounds();

            if (rectangle1.intersects(rectangle2) ) {
                
            	return true; //A collison will happen!
            }
        }
    }

	
    //This method can be shared by all actors, players and objects, add to main class/interface etc..
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    */
   
	public static void main(String [] args) {
		
		 SwingUtilities.invokeLater(new Runnable() { //ask what this does

			public void run() {
				System.out.println("inside main");
				new CollisionandKeyboardTest();
				
			}
		
		 });
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	

