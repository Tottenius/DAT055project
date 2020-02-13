package assetclasses;

public class Treasure extends Asset{
	//test
	private static boolean TreasureIsOpen = false;
	//closed treasure
	private final static  String path = "src/assets/closedtreasure.png";
	//open treasure
	private final static  String path2 = "src/assets/openedtreasure.png";
	//Open bool
	private boolean isOpen= false;
	
    public Treasure(int position) {
      
    	super(position, path);
    	super.loadImage(path2);
    	super.getImageAtPos(0);      
    }
    
    public void openTreasure() { 	
    	super.getImageAtPos(1);  	
    	isOpen = true;    	
    }
    
    public void closeTreasure() { 	
    	super.getImageAtPos(0);
    	isOpen = false;      	
    }
    
    public boolean treasureIsOpen() {
    	return isOpen;
    }   
}