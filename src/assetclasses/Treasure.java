package assetclasses;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import Controller.Direction;
import viewer.GamePanel;
/**
 * An class for a TreasureChest asset.
 * 
 * @author Group 10
 *
 */
public class Treasure extends AbstractAsset {

	// closed treasure
	private final static String ImageClosedPath = "src/assets/closedtreasure.png";
	// open treasure
	private final static String ImageOpenedPath = "src/assets/openedtreasure.png";
	
	
	private static Image imageClosed = AssetImageHandler.loadImage(ImageClosedPath);
	private static Image imageOpened= AssetImageHandler.loadImage(ImageOpenedPath);
	
	private static Map<Direction,Image> map = new HashMap<>();
	
	
	// Open bool
	private boolean isOpen = false;

	private static int openedTreasures = 0;

	public Treasure(final int position) {
		super(position);
		initMap();
	}
	
	private void initMap() {
		map.put(Direction.UP,imageOpened );
		map.put(Direction.DOWN,imageClosed );
	}

	public void openTreasure() {
		openedTreasures++;
		this.isOpen = true;
	}

	public void closeTreasure() {
		this.isOpen = false;
	}

	public boolean treasureIsOpen() {
		return this.isOpen;
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
		if (!this.isOpen) {
			this.openTreasure();
			//setOpenedTreasures(getOpenedTreasures() + 1);
		}

		return true;
	}

	@Override
	public boolean canWalkOn() {
		return false;
	}

	@Override
	public boolean hasDirections(final Direction d) {
		return false;
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {
		
		if(Treasure.this.isOpen )
			g.drawImage(map.get(Direction.UP), getCoords().x, getCoords().y, gp);
		else
		g.drawImage(map.get(Direction.DOWN), getCoords().x, getCoords().y, gp);
	}
/**
 * 
 * @return The amount of opened treasures.
 */
	public static int getOpenedTreasures() {
		return openedTreasures;
	}
/*
 * Set the treasure asset to open.
 */
	public static void setOpenedTreasures(final int openedTreasures) {
		Treasure.openedTreasures = openedTreasures;
	}
}