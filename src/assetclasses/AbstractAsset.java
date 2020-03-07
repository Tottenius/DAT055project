package assetclasses;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import Controller.Direction;
import viewer.GameSettings;

/**
 * An abstract class utilized by all asset classes. 
 * Contains methods for assets image handling as well as handling assets position.
 * 
 * 
 * @author Group 10
 * @version 2020-02-25
 */

public abstract class AbstractAsset implements Asset {

	private final static int size = GameSettings.getAssetsize();
	private int position;
	private Point coords;
	private Image img;
	protected Direction direction;

	private final Map<Direction, Image> images = new HashMap<>();

	/**
	 * Constructor takes position that will set the class position and coords field.
	 * 
	 * @param position
	 * 
	 */
	public AbstractAsset(final int position) {
		this.setPosition(position);
		this.setCoords();
	}

	/**
	 * returns img field, that being the assets image represenation. 
	 * 
	 */
	@Override
	public Image getImage() {
		return this.img;
	}

	/**
	 * Set the image at an specified location in our asset array, the image it sets is determined by the direction param.
	 * 
	 * @param direction
	 */
	public void getImageAtMap(final Direction direction) {
		this.img = this.images.get(direction);
	}

	/**
	 * Loads in an image, determined by the path and direction parameters, 
	 * the direction is used to store different images for the same assets.
	 * The method also scales the image the gets loaded in such that all assets are the same size.
	 * 
	 * @param path
	 * @param direction
	 */
	public void loadImage(final String path, final Direction direction) {
		Image imgTemp;

		try {
			this.img = ImageIO.read(new File(path));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// Scaling the image so all images loaded in are the same size
		imgTemp = this.img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		this.img = imgTemp;
		this.images.put(direction, this.img);
	}

	
	/**
	 * Get the array location for a specified image
	 * 
	 * @return the position for the asset
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * Sets the position for the asset
	 * 
	 * @param position
	 */
	
	// set the array location for a specified image
	public void setPosition(final int position) {
		this.position = position;
		this.coords = new Point(position * GameSettings.getAssetsize() % GameSettings.getWidth(),
				position * GameSettings.getAssetsize() / GameSettings.getWidth() * GameSettings.getAssetsize());
	}
/**
 * 
 * @return the coordinates for the asset
 */
	public Point getCoords() {
		return this.coords;
	}

	/**
	 * Sets the coordinates given by the parameter for the asset.
	 * 
	 * @param coords
	 */
	public void setCoords(final Point coords) {
		this.coords = coords;
	}

	/**
	 * Set the coords from the assets position.
	 * 
	 */
	public void setCoords() {
		this.coords = new Point(this.position * GameSettings.getAssetsize() % GameSettings.getWidth(),
				this.position * GameSettings.getAssetsize() / GameSettings.getWidth() * GameSettings.getAssetsize());
	}

}
