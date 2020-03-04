package assetclasses;

import java.awt.Graphics;
import java.awt.Point;

import Controller.Direction;
import viewer.GamePanel;
import viewer.GameSettings;

public class Player extends AbstractAsset {

	private static final String down = "src/assets/PlayerDown.png";
	private static final String up = "src/assets/PlayerUp.png";
	private static final String left = "src/assets/PlayerLeft.png";
	private static final String right = "src/assets/PlayerRight.png";

	private boolean alive;
	private final Point prevPos = getCoords();
	public Player(final int position) {
		super(position);
		super.loadImage(up, Direction.UP);
		super.loadImage(down, Direction.DOWN);
		super.loadImage(left, Direction.LEFT);
		super.loadImage(right, Direction.RIGHT);
		this.alive = true;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(final boolean alive) {
		this.alive = alive;
	}

	@Override
	public boolean killable() {
		return true;
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
		this.direction = d;
		getImageAtMap(d);
		return true;
	}

	public void setPrevPos() {
		this.prevPos.x = getCoords().x - this.direction.getXDelta() * GameSettings.getAssetsize();
		this.prevPos.y = getCoords().y - this.direction.getYDelta() * GameSettings.getAssetsize() / 32;
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {

		g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
	}
}