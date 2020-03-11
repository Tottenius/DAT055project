package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;

/**
 * An class for a spike asset.
 * 
 * @author Group 10
 *
 */
public class Spikes extends AbstractAsset {

	private static final String path = "src/assets/spikes.png";
	private static final String path2 = "src/assets/tile.png";
	private boolean up = true;
	private boolean canKill = true;
	private boolean canWalkOn = false;

	public Spikes(final int position) {
		super(position);
		super.loadImage(Spikes.path, Direction.UP);
		super.loadImage(Spikes.path2, Direction.DOWN);
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
	public boolean hasDirections(final Direction d) {
		return false;
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
			this.getImageAtMap(Direction.UP);
			g.drawImage(getImage(), getCoords().x, getCoords().y, gp);
		} else {
			this.getImageAtMap(Direction.DOWN);
			g.drawImage(getImage(), getCoords().x, getCoords().y, gp);
		}
	}
}