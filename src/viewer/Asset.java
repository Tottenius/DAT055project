package viewer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Asset extends JLabel {
	
	private final static int size = 20;
	private int x;
	private int y;
	private String path;
	private ImageIcon icon;

	
	public int getSize1() {
		return size;
	}
	
    public Asset(int x, int y, String path) {
        this.x = x;
        this.y = y;
        this.setBounds(x, y, getSize1(), getSize1());
        loadImage(path);
    }
    
    private void loadImage(String path) {
    	BufferedImage bImg = null;
    	Image resizeImg;
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
