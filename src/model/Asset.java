package model;

import java.awt.Graphics;
import controller.Direction;
import viewer.GamePanel;

/**
 * An interface for assets. Contains a method for retrieving an image.
 * Also has many attribute methods that can be giving to different assets to differentiate and classify them.
 * 
 * @author Group 10
 *
 */
public interface Asset {

	// ------------------------------------------------ Asset Attributes ------------------------------------------------ \\
	
	/**
	 * Whether an asset is killable.
	 */
	boolean killable();

	/**
	 * Determines if an asset can kill other assets.
	 */
	boolean canKill();

	/**
	 * Method tells if an asset is intractable and if so what it does when being interacted with.
	 */
	boolean intractable();

	/**
	 * Determines if the asset is walkable over by other assets.
	 */
	boolean canWalkOn();
	
	/**
	 * Determines if an asset has multiple graphical states that can be drawn depending on different decided conditions.
	 */
	void hasDirections(Direction d);
	
	/**
	 * method for painting an asset at a specified panel.
	 */
	// Animations
	void paintAsset(Graphics g, GamePanel gp);
}