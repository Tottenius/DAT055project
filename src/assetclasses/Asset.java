package assetclasses;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import viewer.GameSettings;

public abstract class Asset {

	private final static int size = GameSettings.getAssetsize();
	private int position;
    private Image img;
    private ArrayList<Image> images = new ArrayList<Image>();
     
    public Asset(int position, String path) {
        this.setPosition(position);
        loadImage(path);      
    }
    
    public Image getImage() {
    	return img;
    }
    
    public void getImageAtPos(int i) {
    	img = images.get(i);
    }
    //s
    public void loadImage(String path) {
    	//try to get image
    	Image imgTemp;
    	
    	try {
    		img = ImageIO.read(new File(path));
    		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
    	// den går till sig själv, kanske inte går
    	imgTemp = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
    	img = imgTemp;
    	System.out.println(img);
    	images.add(img);
    }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}   
}
