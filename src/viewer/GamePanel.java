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
    //Our assets
    Wall wall = new Wall( x, y);
    Tile tile = new Tile( x, y);
    Treasure treasure = new Treasure( x, y);
    Player player = new Player(x,y);
    
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
		System.out.println("Hej initWorld");

		for (int i = 0; i < level.length(); i++){
			//System.out.println(level.charAt(i));
		    assetSymbol = level.charAt(i);    
		    
		  //Load in player asset
		    if( assetSymbol == 'p') {
				g.drawImage(player.getImage(), x, y,this);
				 x= x+ SPACE;
			   }
		    
		    //Load in wall assets
		   if( assetSymbol == '#') {
			   g.drawImage(wall.getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   //Load in tile assets
		   else if( assetSymbol == ' ') {
			   g.drawImage(tile.getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   //Load in treasures
		   else if( assetSymbol == 't') {
			   g.drawImage(treasure.getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   // new row of Assets
		   else if (assetSymbol =='\n') {
			   y = y + SPACE;
			   x = 0;
		   }
			   
		}
		y = 0;
		x= 0;

    }
	/*
	private void moveObject(int x, int y) {
		int pos = x %((width/SPACE))+ y%(height/SPACE);
		
		
	}
	*/
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("vi kommer hit igen och igen");
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
                    
                    PlayerMoveUp();
                    
                    //vi flyttar detta till egen metod 
                 //   StringBuilder leveltemp = new StringBuilder(level);
                  //  leveltemp.setCharAt(0, 't');
                  //  level =  leveltemp.toString();
                    //System.out.println(level);
                  //  repaint();
                    
                    
                    
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
            //repaint(); // TO redraw everything because we have moved something, we will repaint even if nothing happens but not that big of a deal 
        }	
	}
	
	// Usefull method give us the location of the player at any current time
	private int PlayerLocation() {
		
		for (int i = 0; i < level.length(); i++){
		
			if(level.charAt(i) == 'p') 
				return i;
			
		}
		return 0 ; //this mean player can never be at zero but we can change this later
	}
	
	//work on this after cordinate system has been done by tor bara en ful test 
	private void PlayerMoveUp(){
		
		//System.out.println(level.length());
		int firstplayerpos = PlayerLocation();
          
		StringBuilder leveltemp = new StringBuilder(level);
          char temp = level.charAt(firstplayerpos -36); //here is where we would check what is forward of him, posfsible a chest or pushing something
          leveltemp.setCharAt(firstplayerpos - 36, 'p'); //36 is not always right NEEDS TO BE FIXED GENERALLISED TO A FORMULA
          level =  leveltemp.toString();
          leveltemp.setCharAt(firstplayerpos, temp);
          level =  leveltemp.toString();
         // System.out.println(level);
          repaint();
		
		
		
		
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
