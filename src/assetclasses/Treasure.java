package assetclasses;

import java.awt.Graphics;

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
	private final static String path = "src/assets/closedtreasure.png";
	// open treasure
	private final static String path2 = "src/assets/openedtreasure.png";
	// Open bool
	private boolean isOpen = false;

	private static int openedTreasures = 0;

	public Treasure(final int position) {
		super(position);
		super.loadImage(Treasure.path, Direction.DOWN);
		super.loadImage(Treasure.path2, Direction.UP);
		super.getImageAtMap(Direction.DOWN);
	}

	public void openTreasure() {
		super.getImageAtMap(Direction.UP);
		openedTreasures++;
		this.isOpen = true;
	}

	public void closeTreasure() {
		super.getImageAtMap(Direction.DOWN);
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
		g.drawImage(getImage(), getCoords().x, getCoords().y, gp);
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