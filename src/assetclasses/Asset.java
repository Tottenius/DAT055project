package assetclasses;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
public abstract class Asset {

	private final static int size = 20;
    private int x = 0;
    private int y = 0;
    private Image img;
    private ArrayList<Image> images = new ArrayList<Image>();


    public Asset(int x, int y, String path) {
        this.x = x;
        this.y = y;
        loadImage(path);
    }
    
    public Image getImage() {
    	return img;
    }
    
    public Image getImageAtPos(int i) {
    	return images.get(i);
    }
    
    public void loadImage(String path) {
    	//try to get image
    	Image imgTemp;
    	
    	try {
    		img = ImageIO.read(new File(path));
    		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
    	// den g�r till sig sj�lv, kanske inte g�r
    	imgTemp = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
    	img = imgTemp;
    	System.out.println(img);
    	images.add(img);
    }
    
    
    	
    	
    
}
