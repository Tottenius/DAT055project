package model;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

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
	
	private static final Image imageDown = AssetImageHandler.loadImage(down);
	private static final Image imageUp = AssetImageHandler.loadImage(up);
	private static final Image imageLeft = AssetImageHandler.loadImage(left);
	private static final Image imageRight = AssetImageHandler.loadImage(right);
	
	private Image currentImage = null;
	
	private static final Map<Direction,Image> map = new HashMap<>();

	private boolean alive;
	public Player(final int position) {
		super(position);
		this.alive = true;
		initMap();
	}
	
	private void initMap() {
		
		Player.map.put(Direction.DOWN, imageDown );
		Player.map.put(Direction.UP, imageUp );
		Player.map.put(Direction.LEFT, imageLeft );
		Player.map.put(Direction.RIGHT, imageRight );
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
	public void hasDirections(final Direction d) {
		this.direction = d;
		currentImage = map.get(d);
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {

		g.drawImage(Player.this.currentImage, getCoords().x, getCoords().y, gp);
	}
}