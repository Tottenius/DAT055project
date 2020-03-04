package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;

public class Wall extends AbstractAsset {

	private static final String path = "src/assets/wall.png";

	public Wall(final int position) {
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