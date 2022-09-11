package model;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import controller.Direction;
import viewer.GamePanel;

/**
 * An class for a spike asset.
 * 
 * @author Group 10
 *
 */
public class Spikes extends AbstractAsset {

	private static final String pathUp = "src/assets/spikes.png";
	private static final String pathDown = "src/assets/tile.png";
	
	private static final Image imageDown = AssetImageHandler.loadImage(pathDown);
	private static final Image imageUp = AssetImageHandler.loadImage(pathUp);
	
	private static final Map<Direction,Image> map = new HashMap<>();
	
	
	private boolean up = true;
	private boolean canKill = true;
	private boolean canWalkOn = false;
	

	public Spikes(final int position) {
		super(position);
		initMap();
	}
	
	private void initMap() {
		map.put(Direction.UP,imageUp );
		map.put(Direction.DOWN,imageDown );
	}

	@Override
	public boolean killable() {
		return false;
	}

	@Override
	public boolean canKill() {
		return this.canKill;
	}

	@Override
	public boolean intractable() {
		return false;
	}

	@Override
	public boolean canWalkOn() {
		return this.canWalkOn;
	}

	@Override
	public void hasDirections(final Direction d) {
	}
/**
 * Update the state of the spike, whether it's up or down.
 * 
 */
	public void changeUpOrDown() {
		this.up = !this.up;
		this.canKill = !this.canKill;
		this.canWalkOn = !this.canWalkOn;
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {

		if (this.up) {
			g.drawImage(map.get(Direction.UP), getCoords().x, getCoords().y, gp);
		} else {
			g.drawImage(map.get(Direction.DOWN), getCoords().x, getCoords().y, gp);
		}
	}
}