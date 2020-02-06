package assetclasses;


public class Treasure extends Asset{
	//test
	private static boolean TreasureIsOpen = false;
	private final static  String path = "src/assets/closedtreasure.png";

	
    public Treasure(int x, int y) {
      
    	super(x, y, path);
        
    }
    
    public static void OpenTreasure() { 
    	TreasureIsOpen = true;    	
    }
    
    
}
