package model;

import java.awt.Point;
import lombok.Getter;

/**
 * An abstract class utilized by all asset classes. 
 * Contains methods for assets image handling as well as handling assets position.
 * 
 * 
 * @author Group 10
 * @version 2020-02-25
 */

public abstract class AbstractAsset implements Asset {
	@Getter
	private int position;
	@Getter
	private Point coords;

	/**
	 * Constructor takes position that will set the class position and coords field.
	 *
     *
	 */
	public AbstractAsset(final int position) {
		this.setPosition(position);
		this.setCoords();
	}

	/**
	 * Sets the position for the asset
	 *
     */
	
	// set the array location for a specified image
	public void setPosition(final int position) {
		this.position = position;
		this.coords = new Point(position * GameSettings.getAssetsize() % GameSettings.getWidth(),
				position * GameSettings.getAssetsize() / GameSettings.getWidth() * GameSettings.getAssetsize());
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
