package assetclasses;

import java.awt.Graphics;
import java.awt.Image;

import Controller.Direction;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

/**
 * An class for a door asset
 * 
 * @author Group 10
 *
 */
public class Door extends AbstractAsset {

	private static final String path = "src/assets/doordown.png";
	private static Image image = AssetImageHandler.loadImage(path);

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
			GameWindowTemp.setNextLevelState();
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
	public boolean hasDirections(final Direction d) {
		return false;
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {
		g.drawImage(image, getCoords().x, getCoords().y, gp);
	}
}