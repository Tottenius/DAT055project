package viewer;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Tile extends Asset{
	
    private ImageIcon image;
    private BufferedImage img;
    private Image resizeImg;

    public Tile(int x, int y) {
        super(x, y);
        initWall();
       // System.out.println( "x = " + x +" y = " + y );
    }
    


	private void initWall() {
    	
		//load image
		try {
    	    img = ImageIO.read(new File("src/assets/tile.png"));
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		//resize image
    	resizeImg = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
    	// Make the image into an imageIcon
    	image = new ImageIcon(resizeImg);
    	//System.out.println("current working directory is: " + System.getProperty("user.dir"));

    	// Add the imageIcon to the label
        this.setIcon(image);
        this.setVisible(true);
      
        
    }
}
