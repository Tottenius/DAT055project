package model;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;
/**
 * An class to make emptiness concrete. Can be used to have empty assets or multible layers of assets.
 * 
 * @author Group 10
 *
 */
public class Empty extends AbstractAsset {
	public Empty(final int position) {
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
		return false;
	}

	@Override
	public boolean canWalkOn() {
		return true;
	}

	@Override
	public void hasDirections(final Direction d) {
    }

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {
		// unused
	}
}
