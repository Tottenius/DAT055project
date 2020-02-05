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
	
	
	public GamePanel(){
		this.setPreferredSize(new Dimension ( width, height));
		this.setLayout(null);
		readInlevel(level1);
		this.setVisible(true);
	
		
	}

}
