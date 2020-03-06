package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;
/**
 * An class for a Tile asset.
 * 
 * @author Group 10
 *
 */
public class Tile extends AbstractAsset {
	
	private static final String path = "src/assets/tile.png";
	
	public Tile(final int position) {
		super(position);
		super.loadImage(path, Direction.DOWN);
		super.getImage();
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
		return false;
	}

	@Override
	public boolean canWalkOn() {
		return true;
	}

	@Override
	public boolean hasDirections(final Direction d) {
		return false;
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {
		g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
	}

}