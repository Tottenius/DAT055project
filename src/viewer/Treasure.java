package viewer;

import java.awt.Image;

public class Treasure extends Asset{
	
	private static final String path = "src/assets/treasure.png";
	
	private Image img;
	
    public Treasure(int x, int y) {
        super(x, y, path);
        
    }
    
    public void moveTreasure(int x, int y) {
    	
    }
}
