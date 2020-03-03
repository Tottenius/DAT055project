package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.MusicPlayer;
import viewer.ReadInWorld;

public class Treasure extends AbstractAsset{

	//closed treasure
	private final static  String path = "src/assets/closedtreasure.png";
	//open treasure
	private final static  String path2 = "src/assets/openedtreasure.png";
	//Open bool
	private boolean isOpen= false;
	
	private static int openedTreasures = 0;
	
    public Treasure(int position) {   
    	super(position);
    	super.loadImage(path, Direction.DOWN);
    	super.loadImage(path2, Direction.UP);
    	super.getImageAtMap(Direction.DOWN);      
    }
    
    public void openTreasure() { 	
    	super.getImageAtMap(Direction.UP);
    	//play an actually sutitble sounds not an beep, just for now
    	java.awt.Toolkit.getDefaultToolkit().beep();
    	isOpen = true;    	
    }
    
    public void closeTreasure() { 	
    	super.getImageAtMap(Direction.DOWN);
    	isOpen = false;      	
    }
    
    public boolean treasureIsOpen() {
    	return isOpen;
    }

	@Override
	public boolean killable() {
		return false;
	}

	@Override
	public boolean canKill() {
		return false;
	}

	@Override
	public boolean intractable() {
		if(!this.isOpen) {
			this.openTreasure();
			setOpenedTreasures(getOpenedTreasures() + 1);
			if( getOpenedTreasures() == ReadInWorld.numberOfTresures) {
				//GameWindowTemp.setWinState();
				System.out.println("you opened all treasure, now you can go to next level trough the door!");
			}
		}
		
		return true;
	}

	@Override
	public boolean canWalkOn() {
		return false;
	}

	@Override
	public boolean hasDirections(Direction d) {
		return false;
	}

	@Override
	public void paintAsset(Graphics g, GamePanel gp) {
		g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
	}

	public static int getOpenedTreasures() {
		return openedTreasures;
	}

	public static void setOpenedTreasures(int openedTreasures) {
		Treasure.openedTreasures = openedTreasures;
	}
}