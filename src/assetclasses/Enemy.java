package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;
/**
 * An class for an enemy asset
 * 
 * @author Group 10
 *
 */
public class Enemy extends AbstractAsset {

	private static final String down = "src/assets/headcrabMonsterAsset.png";
	private static final String up = "src/assets/headcrabMonsterAsset.png";
	private static final String left = "src/assets/headcrabMonsterAsset.png";
	private static final String right = "src/assets/headcrabMonsterAsset.png";

	public Enemy(final int position) {
		super(position);
		super.loadImage(up, Direction.UP);
		super.loadImage(down, Direction.DOWN);
		super.loadImage(left, Direction.LEFT);
		super.loadImage(right, Direction.RIGHT);
	}

	@Override
	public boolean killable() {
		return false;
	}

	@Override
	public boolean canKill() {
		return true;
	}

	@Override
	public boolean intractable() {
		return false;
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
		g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
	}
}