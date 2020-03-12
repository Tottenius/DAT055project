package model;

import java.awt.Graphics;
import java.awt.Image;

import Controller.Direction;
import viewer.GamePanel;
/**
 * An class for a wall asset.
 * 
 * @author Group 10
 *
 */
public class Wall extends AbstractAsset {

	private static final String path = "src/assets/wall.png";
	private static Image image = AssetImageHandler.loadImage(path);

	public Wall(final int position) {
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
		return false;
	}

	@Override
	public boolean hasDirections(final Direction d) {
		return false;
	}

	@Override
	public void paintAsset(final Graphics g, final GamePanel gp) {
		g.drawImage(image, getCoords().x, getCoords().y, gp);
	}
}