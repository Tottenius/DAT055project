package assetclasses;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import viewer.GameSettings;

//Abstract Asset class that is used by all assets in the game
public abstract class Asset {

	private final static int size = GameSettings.getAssetsize();
	private int position;
    private Image img;
    private ArrayList<Image> images = new ArrayList<Image>();
     
    public Asset(int position, String path) {
        this.setPosition(position);
        loadImage(path);      
    }
    
    //returns the image
    public Image getImage() {
    	return img;
    }
  
    //get an image at an specified location in our asset array
    public void getImageAtPos(int i) {
    	img = images.get(i);
    }
   
    //load in an image
    public void loadImage(String path) {
    	Image imgTemp;
    	
    	try {
    		img = ImageIO.read(new File(path));
    		}
    	catch (IOException e) {
    		e.printStackTrace();
		}
    	
    	//Scaling the image so all images loaded in are the same size 
    	imgTemp = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
    	img = imgTemp;
    	images.add(img);
    }
 
    //get the array location for a specified image 
	public int getPosition() {
		return position;
	}
	
	//set the array location for a specified image 
	public void setPosition(int position) {
		this.position = position;
	}   
}
