package assetclasses;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import Controller.Direction;
import viewer.GameSettings;

//Abstract Asset class that is used by all assets in the game
public abstract class AbstractAsset implements Asset {
	
	private final static int size = GameSettings.getAssetsize();
	private int position;
    private Image img;
    protected Direction direction;
    //private ArrayList<Image> images = new ArrayList<Image>();
    private Map<Direction, Image> images = new HashMap<Direction ,Image>();
    
    public AbstractAsset(int position) {
        this.setPosition(position);
        //loadImage(path, direction);      
    }
    
    //returns the image
    public Image getImage() {
    	return img;
    }
  
    //get an image at an specified location in our asset array, hasmap
 
    public void getImageAtMap(Direction direction ) {
    	img = images.get(direction);
    }
   
    //load in an image
    public void loadImage(String path, Direction direction) {
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
    	images.put(direction, img);
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
