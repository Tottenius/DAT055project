package viewer;

//import java.awt.Image;

public class Treasure extends Asset{
	//test
	private static boolean TreasureIsOpen = false;
	private final static  String path = "src/assets/closedtreasure.png";


	
	//private Image img;
	
    public Treasure(int x, int y) {
      
    	super(x, y, path);
        
    }
    
    public void moveTreasure(int x, int y) {
    	
    }
    
    public static void OpenTreasure() { 
    	TreasureIsOpen = true;    	
    }
    
    
}
