package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	
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
        System.out.println("current working directory is: " + System.getProperty("user.dir"));
        try {
            level = new String(Files.readAllBytes(Paths.get(path)));
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }

	private void init() {
		this.setPreferredSize(new Dimension ( width, height));
		this.setLayout(null);
		
		for (int i = 0; i < level.length(); i++){
			//System.out.println(level.charAt(i));
		    assetSymbol = level.charAt(i);    
		    //Load in wall assets
		   if( assetSymbol == '#') {
			   this.add(new Wall(x, y)).setLocation(x, y);
			   x = x + SPACE;
		   }
		   //Load in tile assets
		   else if( assetSymbol == ' ') {
			  // assets.add( new Tile(x, y));
			   this.add(new Tile(x, y)).setLocation(x, y);
			   x = x + SPACE;
		   }
		   //Load in treasures
		   else if( assetSymbol == 't') {
			  // assets.add( new Tile(x, y));
			   this.add(new Treasure(x, y)).setLocation(x, y);
			   x = x + SPACE;
		   }
		   // new row of Assets
		   else if (assetSymbol =='\n') {
			   y = y + SPACE;
			   x = 0;
		   }

			   
		}
		
		this.setVisible(true);
    }
	
	public GamePanel(){
		readInlevel(level1);
		init();
	}

}
