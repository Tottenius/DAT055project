package assetclasses;

import java.awt.Graphics;
import Controller.Direction;
import viewer.GamePanel;

/**
 * An class for a player asset.
 * 
 * @author Group 10
 *
 */
public class Player extends AbstractAsset {

	private static final String down = "src/assets/PlayerDown.png";
	private static final String up = "src/assets/PlayerUp.png";
	private static final String left = "src/assets/PlayerLeft.png";
	private static final String right = "src/assets/PlayerRight.png";

	private boolean alive;
	public Player(final int position) {
		super(position);
		super.loadImage(Player.up, Direction.UP);
		super.loadImage(Player.down, Direction.DOWN);
		super.loadImage(Player.left, Direction.LEFT);
		super.loadImage(Player.right, Direction.RIGHT);
		this.alive = true;
	}
/**
 * Method to get the state of the player.
 * 
 * @return State of player, if player is alive or not.
 */
	public boolean isAlive() {
		return this.alive;
	}
/**
 * Set the state of the player to alive. 
 * 
 * @param alive
 */
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

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {

		g.drawImage(getImage(), getCoords().x, getCoords().y, gp);
	}
}