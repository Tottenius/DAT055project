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

import Controller.EnemyController;
import Controller.PlayerController;
import assetclasses.Asset;
import assetclasses.Enemy;
import assetclasses.Player;
import assetclasses.Tile;
import assetclasses.Treasure;
import assetclasses.Wall;
import main.Main;


public class GamePanel extends JPanel implements Runnable{
	//Window size
	 private static final int WIDTH = GameSettings.getWidth();
	 private static final int HEIGHT = GameSettings.getHeight();
	 // Size of an asset
	 private static final int SIZE = GameSettings.getAssetsize();
	 // Serial
	 private static final long serialVersionUID = 1L;
	 // NewKeyPressedBool
	 private static boolean isKeyPressed = false;
	 
	 public static boolean isKeyPressed() {
		return isKeyPressed;
	 }
	 public static void setKeyPressed(boolean isKeyPressed) {
		 GamePanel.isKeyPressed = isKeyPressed;
	 }

		
	 //Directions
	 public enum Direction{
		UP(0, -(WIDTH/SIZE)),
		DOWN(0, (WIDTH/SIZE)),
		LEFT(-1, 0),
		RIGHT(1, 0);
		private final int yDelta;
		private final int xDelta;
		
		Direction(int xDelta, int yDelta) {
			this.xDelta = xDelta;
			this.yDelta = yDelta;
		}
		
		public int getXDelta() {
			return this.xDelta;
		}
		
		public int getYDelta() {
			return this.yDelta;
		}
	}
	private static Direction direction = Direction.RIGHT;
	//get direction
	public static Direction getDirection() {
		return direction;
	}
	// list with assets
	private static ArrayList<Asset> assets = new ArrayList<Asset>();

	// Starting position
    private int x = 0;
    private int y = 0;
    
    // Symbols
    private Asset asset;
    //level paths
    String level = "";
    String level1 = "src/levels/level2.txt";
    
    //Players
    private PlayerController player1;
    private PlayerController player2;
    
    //Enemys
    private EnemyController enemy1;
    //Enemy threads
    private Thread enemy1Thread;
    //Player threads
    private Thread player1Thread;
    private Thread player2Thread;
    //Reach assets in controller
    public static ArrayList<Asset> getAssetList(){
    	return assets;
    }
    public static void setAssetList( ArrayList<Asset> assetList){
    	assets = assetList;
    }
    
    private void howManyPlayers(int amount) {
    	if (amount == 1) {
    		//player1 = new PlayerController( 45);
    		player1Thread = new Thread(player1);
    		player1Thread.start();
    		// startar enemyn kanske fel ställen men på test
    		enemy1Thread = new Thread(enemy1);
    		enemy1Thread.start();
    			
    	}
       	if (amount == 2) {
    		player1 = new PlayerController( 45);
       		player2 = new PlayerController( 46);
    		player1Thread = new Thread(player1);
    		player2Thread = new Thread(player2);
    		player1Thread.start();
    		player2Thread.start();
			}
    }
  
    public void readInlevel( String path) {
    	int posInList = 0;
        //System.out.println("current working directory is: " + System.getProperty("user.dir"));
        try {
            level = new String(Files.readAllBytes(Paths.get(path)));
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        for(int i = 0; i< level.length(); i++ ) {
        	
        	if (level.charAt(i)== '#') {
        		assets.add(new Wall(posInList));
        		posInList++;
        	}
        	else if(level.charAt(i) == ' ') {
        		assets.add(new Tile(posInList));
        		posInList++;
        	}
 		   //Load in treasures
 		   	else if( level.charAt(i) == 't') {
 			  assets.add(new Treasure(posInList));
 			 posInList++;
 		   	}
 		   
 		   //Load in opened treasures
 		   	else if( level.charAt(i) == 'o') {
 			  assets.add(new Treasure(posInList));
 			  posInList++;
 		   	}
 		   	else if( level.charAt(i) == 'p' ) {
 			   System.out.println(i);
 			   player1 = new PlayerController( posInList);
 			   posInList++;
 			  //assets.add(new Player(i));
			}   	
  		   //Load in enemies
 		   	else if( level.charAt(i) == 'e') {
 			  enemy1 = new EnemyController( posInList);
 			  posInList++;
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
		    	y = y + SIZE;
		    }
		    // Load in player
		    if( asset instanceof Player && direction == Direction.LEFT) {
		    	asset.getImageAtPos(2);
				g.drawImage(asset.getImage(), x, y,this);
				//System.out.println("vi försöker göra en gubbe");
				 x= x+ SIZE;
			   }
		    if( asset instanceof Player && direction == Direction.RIGHT) {
		    	asset.getImageAtPos(3);
				g.drawImage(asset.getImage(), x, y,this);
				 x= x+ SIZE;
			   }
		    if( asset instanceof Player && direction == Direction.UP) {
		    	asset.getImageAtPos(1);
				g.drawImage(asset.getImage(), x, y,this);
				 x= x+ SIZE;
			   }
		    if( asset instanceof Player && direction == Direction.DOWN) {
		    	asset.getImageAtPos(0);
				g.drawImage(asset.getImage(), x, y,this);
				 x= x+ SIZE;
			   }
		    //Load in enemies
		   if( asset instanceof Enemy) {
			   //System.out.println("Vi försöker göra en enemy");
			   g.drawImage(asset.getImage(), x, y,this);
			   x= x+ SIZE;
		   }
		    //Load in wall assets
		   if( asset instanceof Wall) {
			   //System.out.println("Vi försöker göra en wall");
			   g.drawImage(asset.getImage(), x, y,this);
			   x= x+ SIZE;
		   }
		   //Load in tile assets
		   else if( asset instanceof Tile) {
			   g.drawImage(asset.getImage(), x, y,this);
			   x= x+ SIZE;
		   }
		   //Load in treasures
		   else if( asset instanceof Treasure) {
			   
			   g.drawImage(asset.getImage(), x, y,this);
			   x= x+ SIZE;
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
            //System.out.println("vi försöker måla om");
            switch (input) {
            	            
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                	
                    System.out.println("Moved Up");
                    direction = Direction.UP;
                   // player1.moveDirection(Direction.UP);
                   // moveDirection(Direction.UP);
                
                    break;               
                    
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
   
                    System.out.println("Moved Down");
                    direction = Direction.DOWN;
                    //player1.moveDirection(Direction.DOWN);
                   // moveDirection(Direction.DOWN);
                    
                    break;
                    
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:	
                   
                	System.out.println("Moved right");
                	direction = Direction.RIGHT;
                	//player1.moveDirection(Direction.RIGHT);
                	//moveDirection(Direction.RIGHT);
                	
                    break;
                    
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                	System.out.println("Moved Left");
                	direction = Direction.LEFT;
                    
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
            isKeyPressed = true;
            
            
        }	
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
	    howManyPlayers(1);
		
	}
	@Override
	public void run() {
		while(Main.isRunning) {
			repaint();
			//Repaint at 60 fps
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}