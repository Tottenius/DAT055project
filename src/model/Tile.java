package model;

import java.awt.Graphics;
import java.awt.Image;

import Controller.Direction;
import viewer.GamePanel;
/**
 * A class for a Tile asset.
 * 
 * @author Group 10
 *
 */
public class Tile extends AbstractAsset {
	
	private static final String path = "src/assets/tile.png";
	private static final Image image = AssetImageHandler.loadImage(path);
	
	public Tile(final int position) {
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
		g.drawImage(image, getCoords().x, getCoords().y, gp);
	}

}