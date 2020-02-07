package assetclasses;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public abstract class Asset {

	//dawdf
	private final static int size = 20;
    private int x = 0;
    private int y = 0;
    private Image img;


    public Asset(int x, int y, String path) {
        this.x = x;
        this.y = y;
        loadImage(path);
    }
    
    public Image getImage() {
    	return img;
    }
    
    private void loadImage(String path) {
    	//try to get image
    	Image imgTemp;
    	img = null;
    	try {
    		img = ImageIO.read(new File(path));
    		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
    	// den går till sig själv, kanske inte går
    	imgTemp = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
    	img = imgTemp;
    }
    
    
    	
    	
    
}

/*
public class Asset extends JLabel {
	
	private final static int size = 20;
	private int x;
	private int y;
	private String path;
	private ImageIcon icon;
	
    public Asset(int x, int y, String path) {
        this.x = x;
        this.y = y;
        this.setBounds(x, y, size, size);
        loadImage(path);
    }
    
    
    
    private void loadImage(String path) {
    	BufferedImage bImg = null;
    	Image resizeImg;
    	// Try to load image
		try {
    	     bImg = ImageIO.read(new File(path));
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		//resize image
		resizeImg = bImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
    	// Make the image into an imageIcon
		icon = new ImageIcon(resizeImg);
	    this.setIcon(icon);
	    this.setVisible(true);
    	
    	
    }
    

	
}
*/
