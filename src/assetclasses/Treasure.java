package assetclasses;

import Controller.Direction;

public class Treasure extends AbstractAsset{
	//test
	private static boolean TreasureIsOpen = false;
	//closed treasure
	private final static  String path = "src/assets/closedtreasure.png";
	//open treasure
	private final static  String path2 = "src/assets/openedtreasure.png";
	//Open bool
	private boolean isOpen= false;
	
    public Treasure(int position) {   
    	super(position);
    	super.loadImage(path, Direction.DOWN);
    	super.loadImage(path2, Direction.UP);
    	super.getImageAtMap(Direction.DOWN);      
    }
    
    public void openTreasure() { 	
    	super.getImageAtMap(Direction.UP);  	
    	isOpen = true;    	
    }
    
    public void closeTreasure() { 	
    	super.getImageAtMap(Direction.DOWN);
    	isOpen = false;      	
    }
    
    public boolean treasureIsOpen() {
    	return isOpen;
    }   
}