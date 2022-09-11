package model;

import java.awt.Graphics;
import java.awt.Image;

import controller.Direction;
import viewer.GamePanel;
import viewer.ProgramStateHandeler;

/**
 * A class for a door asset
 * 
 * @author Group 10
 *
 */
public class Door extends AbstractAsset {

	private static final String path = "src/assets/doordown.png";
	private static final Image image = AssetImageHandler.loadImage(path);

	public Door(final int position) {
		super(position);
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

		if (Treasure.getOpenedTreasures() >= ReadInWorld.numberOfTresures) {
			// restart and new level doing
			ProgramStateHandeler.setState(ProgramStateHandeler.STATE.NextLevel);
			// player goes to next level!
			return true;
		}
		return true;
	}

	@Override
	public boolean canWalkOn() {
		return false;
	}

	@Override
	public void hasDirections(final Direction d) {
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {
		g.drawImage(image, getCoords().x, getCoords().y, gp);
	}
}