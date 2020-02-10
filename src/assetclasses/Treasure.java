package assetclasses;


public class Treasure extends Asset{
	//test
	private static boolean TreasureIsOpen = false;
	//closed treasure
	private final static  String path = "src/assets/closedtreasure.png";
	//open treas
	private final static  String path2 = "src/assets/openedtreasure.png";
	

	
    public Treasure(int position) {
      
    	super(position, path);
        
    }
    
    public Treasure(int position, String path) {
        
    	super(position, path);
        
    }  
    
    public String getOpenTreasurePath() { 
    	
    	return path2;
    	//TreasureIsOpen = true;    	
    }
    
    
}
