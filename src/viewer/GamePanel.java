package viewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JPanel;

import assetclasses.Asset;
import assetclasses.Player;
import assetclasses.Tile;
import assetclasses.Treasure;
import assetclasses.Wall;


public class GamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Directions
	private enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	private Direction direction = Direction.RIGHT;
	//test
	// list with assets
	private ArrayList<Asset> assets;
	//Window size
	 private static final int WIDTH = GameSettings.getWidth();
	 private static final int HEIGHT = GameSettings.getHeight();
	// Size of an asset
	private static final int SPACE = GameSettings.getAssetsize();
	// Starting position
    private int position = 0;
    private int x = 0;
    private int y = 0;
    // Symbol
    private char assetSymbol;
    //level paths
    String level = "";
    String level1 = "src/levels/level2.txt";
    //Our assets
    Wall wall = new Wall( position);
    Tile tile = new Tile( position);
    Treasure treasure = new Treasure( position);
    Treasure openedtreasure = new Treasure(position,treasure.getOpenTreasurePath());
    Player player = new Player(position);
    
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
		    if( assetSymbol == 'p' && direction == Direction.LEFT) {
				g.drawImage(player.getImageAtPos(2), x, y,this);
				 x= x+ SPACE;
			   }
		    if( assetSymbol == 'p' && direction == Direction.RIGHT) {
				g.drawImage(player.getImageAtPos(3), x, y,this);
				 x= x+ SPACE;
			   }
		    if( assetSymbol == 'p' && direction == Direction.UP) {
				g.drawImage(player.getImageAtPos(1), x, y,this);
				 x= x+ SPACE;
			   }
		    if( assetSymbol == 'p' && direction == Direction.DOWN) {
				g.drawImage(player.getImageAtPos(0), x, y,this);
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
		   
		   //Load in opened treasures
		   else if( assetSymbol == 'o') {
			   g.drawImage(openedtreasure.getImage(), x, y,this);
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
                    direction = Direction.UP;
                    moveDirection(Direction.UP, 'p');
                
                    break;
                    
                    
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
   
                    System.out.println("Moved Down");
                    direction = Direction.DOWN;
                    moveDirection(Direction.DOWN, 'p');
                    
                    break;
                    
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:	
                   
                	System.out.println("Moved right");
                	direction = Direction.RIGHT;
                	moveDirection(Direction.RIGHT, 'p');
                	
                    break;
                    
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                	
                  System.out.println("Moved Left");
                  direction = Direction.LEFT;
                  moveDirection(Direction.LEFT, 'p');
                    
                    break;
                    
                case KeyEvent.VK_R: 		 //reset level
                    
                  
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
	// Might have to be changed if there exists more then one kind of asset that we want to move
	private int assetLocation(char a) {
		
		for (int i = 0; i < level.length(); i++){
		
			if(level.charAt(i) == 'p') 
				return i;
			
		}
		return 0 ; //this mean player can never be at zero but we can change this later
	}
	
	private void moveDirection( Direction direction, char a) {
		// Right now just player pos
		int firstplayerpos = assetLocation(a);
		if(direction == Direction.UP ) {
			level = moveObeject( level, a, firstplayerpos - (WIDTH/SPACE)-4, firstplayerpos );
			
		}
		if(direction == Direction.DOWN ) {
			level = moveObeject( level, a, firstplayerpos + (WIDTH/SPACE)+4, firstplayerpos );
			
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
		if(temp != '#' && temp != 't' && temp!= 'o') { //remove o if we changed how opening treasure works
			StringBuilder leveltemp = new StringBuilder(s);
			//Asset at new pos
			leveltemp.setCharAt(newPos, asset);
			//Old asset at objects former pos
			leveltemp.setCharAt(oldPos,temp);
			// rebuild to string
			tempString =  leveltemp.toString();
		}
		
		//interacting with a chest, giving items etc TBD 'o' = opened chest
		if(temp == 't') {
			
		System.out.println("interacting with a chest");
		StringBuilder leveltemp = new StringBuilder(s);
		leveltemp.setCharAt(newPos, 'o'); //replace t with o representing treasure is now opened at specific location
		tempString =  leveltemp.toString();
		
		//Treasure.OpenTreasure(level,newPos);
		
		
		}
		System.out.println(tempString);
		return tempString;
	}
	

	public GamePanel(){
		this.setPreferredSize(new Dimension ( WIDTH, HEIGHT));
		this.setLayout(null);
		readInlevel(level1);
		this.setVisible(true);
		//adding the keylistener
	    this.addKeyListener(new keyLis());
	    this.setFocusable(true);
	    this.revalidate();
		
	}
}
