package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel{

	// list with assets
	private ArrayList<Asset> assets;
	private static final int width = 640;
	private static final int height = 480;
	// Size of an asset
	private static final int SPACE = 20;
	// Starting position
    private int x = 0;
    private int y = 0;
    private char assetSymbol;
    //level paths
    String level = "";
    String level1 = "src/levels/level1.txt";
    
    public void readInlevel( String path) {
        //System.out.println("current working directory is: " + System.getProperty("user.dir"));
        try {
            level = new String(Files.readAllBytes(Paths.get(path)));
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }

	private void initWorld(Graphics g) {

		for (int i = 0; i < level.length(); i++){
			//System.out.println(level.charAt(i));
		    assetSymbol = level.charAt(i);    
		    //Load in wall assets
		   if( assetSymbol == '#') {
			   g.drawImage(new Wall( x, y).getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   //Load in tile assets
		   else if( assetSymbol == ' ') {
			   g.drawImage(new Tile( x, y).getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   //Load in treasures
		   else if( assetSymbol == 't') {
			   g.drawImage(new Treasure( x, y).getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   // new row of Assets
		   else if (assetSymbol =='\n') {
			   y = y + SPACE;
			   x = 0;
		   }
			   
		}

    }
	/*
	private void moveObject(int x, int y) {
		int pos = x %((width/SPACE))+ y%(height/SPACE);
		
		
	}
	*/
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        initWorld(g);
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
                   // ball1.move(10,10);
                    
                	
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
   
	
	public GamePanel(){
		this.setPreferredSize(new Dimension ( width, height));
		this.setLayout(null);
		readInlevel(level1);
		this.setVisible(true);
		//adding the keylistener
	    this.addKeyListener(new keyLis());
	    this.setFocusable(true);
	
		
	}

}
