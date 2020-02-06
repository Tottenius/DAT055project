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
	//Directions
	private enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}

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
		//System.out.println("Hej initWorld");

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
		   //Load in tile assetst
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

	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("vi kommer hit igen och igen");
        initWorld(g);
        
    }
	
	
	private class keyLis extends KeyAdapter{
	
		
		public void keyPressed(KeyEvent e) {

			//System.out.println("inside keylist class");
			
       //If game is completed then keyInputs are ignored
        /*	if (Completed) {   
                return;
            }*/

            int input = e.getKeyCode();

            switch (input) {
                
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                	
                    System.out.println("Moved Up");
                    
                    moveDirection(Direction.UP, 'p');
                
                    break;
                    
                    
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
   
                    System.out.println("Moved Down");
                    moveDirection(Direction.DOWN, 'p');

                	
                	/* if (checkCollisions()) {    //if we collied with wall or other object than do nothing
                    return;
                } */
                
                	// player.move(); 			//Method to move character if no collision
                    
                    break;
                    
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:	
                   
                	System.out.println("Moved right");
                	moveDirection(Direction.RIGHT, 'p');

                	
                 /*    if (checkCollisions()) {    //if we collied with wall or other object than do nothing
                    return;
                }
                */
                	// player.move();			//Method to move character if no collision
                    
                    break;
                    
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                	
                  System.out.println("Moved Left");
                  moveDirection(Direction.LEFT, 'p');
                	
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
            //System.out.println("inside repaint");
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
	
	private void moveDirection( Direction direction, char a) {
		// Right now just player pos
		int firstplayerpos = PlayerLocation();
		if(direction == Direction.UP ) {
			level = moveObeject( level, a, firstplayerpos - (width/SPACE)-4, firstplayerpos );
			
		}
		if(direction == Direction.DOWN ) {
			level = moveObeject( level, a, firstplayerpos + (width/SPACE)+4, firstplayerpos );
			
		}
		if(direction == Direction.LEFT ) {
			level = moveObeject( level, a, firstplayerpos - 1, firstplayerpos );
			
		}
		if(direction == Direction.RIGHT ) {
			level = moveObeject( level, a, firstplayerpos + 1, firstplayerpos );
			
		}
		repaint();
		
		
	}
	
	//For moving movable objects and players
	private String moveObeject(String s, char asset, int newPos, int oldPos) {
		char temp = s.charAt(newPos);
		String tempString = s;
		if(temp != '#' && temp != 't') {
			StringBuilder leveltemp = new StringBuilder(s);
			//Asset at new pos
			leveltemp.setCharAt(newPos, asset);
			//Old asset at objects former pos
			leveltemp.setCharAt(oldPos,temp);
			// rebuild to string
			tempString =  leveltemp.toString();
		}
		
		//interacting with a chest, giving items etc TBD
		if(temp == 't') {
			
		System.out.println("interacting with a chest");
		
		//chaning animation to open chest
		
		Treasure.OpenTreasure();
		}
		
		return tempString;
	}
	

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
