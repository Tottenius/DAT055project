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
	private ArrayList<Asset> assets = new ArrayList<Asset>();
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
    private Asset asset;
    //level paths
    String level = "";
    String level1 = "src/levels/level2.txt";
    //Our assets
    
    public void readInlevel( String path) {
        //System.out.println("current working directory is: " + System.getProperty("user.dir"));
        try {
            level = new String(Files.readAllBytes(Paths.get(path)));
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        for(int i = 0; i< level.length(); i++ ) {
        	if (level.charAt(i)== '#') {
        		assets.add(new Wall(i));
        	}
        	else if(level.charAt(i) == ' ') {
        		assets.add(new Tile(i));
        	}
 		   //Load in treasures
 		   else if( level.charAt(i) == 't') {
 			  assets.add(new Treasure(i));
 		   }
 		   
 		   //Load in opened treasures
 		   else if( level.charAt(i) == 'o') {
 			  assets.add(new Treasure(i));
 		   }
 		   else if( level.charAt(i) == 'p' ) {
 			  assets.add(new Player(i));
			
			   }   	
        }
      }

	private void initWorld(Graphics g) {
		//System.out.println("Hej initWorld");

		for (int i = 0; i < assets.size(); i++){
			//System.out.println(level.charAt(i));
		    //assetSymbol = level.charAt(i);  
			asset = assets.get(i);
		  // Time for a new row?
		    if(x == WIDTH){
		    	x = 0;
		    	y = y + SPACE;
		    }
		    if( asset instanceof Player && direction == Direction.LEFT) {
		    	asset.getImageAtPos(2);
				g.drawImage(asset.getImage(), x, y,this);
				System.out.println("vi försöker göra en gubbe");
				 x= x+ SPACE;
			   }
		    if( asset instanceof Player && direction == Direction.RIGHT) {
		    	asset.getImageAtPos(3);
				g.drawImage(asset.getImage(), x, y,this);
				 x= x+ SPACE;
			   }
		    if( asset instanceof Player && direction == Direction.UP) {
		    	asset.getImageAtPos(1);
				g.drawImage(asset.getImage(), x, y,this);
				 x= x+ SPACE;
			   }
		    if( asset instanceof Player && direction == Direction.DOWN) {
		    	asset.getImageAtPos(0);
				g.drawImage(asset.getImage(), x, y,this);
				 x= x+ SPACE;
			   }
		    
		    //Load in wall assets
		   if( asset instanceof Wall) {
			   //System.out.println("Vi försöker göra en wall");
			   g.drawImage(asset.getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   //Load in tile assets
		   else if( asset instanceof Tile) {
			   g.drawImage(asset.getImage(), x, y,this);
			   x= x+ SPACE;
		   }
		   //Load in treasures
		   else if( asset instanceof Treasure) {
			   
			   g.drawImage(asset.getImage(), x, y,this);
			   x= x+ SPACE;
		   }
			   
		}
		y = 0;
		x= 0;

    }

	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        initWorld(g);
        
    }
	
	private class keyLis extends KeyAdapter{
	
		public void keyPressed(KeyEvent e) {	

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
	private int assetLocation() {

		for (int i = 0; i < assets.size(); i++){
			
			if(assets.get(i) instanceof Player) 
				return i;
			
		}
		return 0 ; //this mean player can never be at zero but we can change this later
	}
	
	private void moveDirection( Direction direction, char a) {
		// Right now just player pos
		int firstplayerpos = assetLocation();
		Asset movingAsset = assets.get(firstplayerpos);
		Asset swapAsset = null;
		int up = firstplayerpos - (WIDTH/SPACE);
		int down = firstplayerpos + (WIDTH/SPACE);
		int left = firstplayerpos -1;
		int right = firstplayerpos + 1;
		
		int dir = 0;
		
		if(direction == Direction.UP ) {
				dir = up;
		}
		if(direction == Direction.DOWN ) {		
				dir = down;
			
		}
		if(direction == Direction.LEFT ) {
				dir = left;
		}
		if(direction == Direction.RIGHT ) {
				dir = right;
			
		}
		
		// Alla interaktioner med assets
		swapAsset = assets.get(dir);
		if (swapAsset instanceof Tile) {
			assets.set(dir, movingAsset ).setPosition(firstplayerpos);
			assets.set(firstplayerpos, swapAsset ).setPosition(dir);
		}
		if (swapAsset instanceof Treasure) {
			((Treasure) swapAsset).openTreasure();
		}

		
		
		repaint();
		
		
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
