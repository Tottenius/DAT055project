package assetclasses;


public class Treasure extends Asset{
	//test
	private static boolean TreasureIsOpen = false;
	private final static  String path = "src/assets/closedtreasure.png";
	private final static  String path2 = "src/assets/openedtreasure.png";
	

	
    public Treasure(int x, int y) {
      
    	super(x, y, path);
        
    }
    
    public Treasure(int x, int y, String path) {
        
    	super(x, y, path);
        
    }  
    
    public String getOpenTreasurePath() { 
    	
    	return path2;
    	//TreasureIsOpen = true;    	
    }
    
    
}
