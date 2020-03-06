package assetclasses;

import java.awt.Graphics;
import java.awt.Image;

import Controller.Direction;
import viewer.GamePanel;

/**
 * An interface for assets. Contains a method for retrieving an image.
 * Also has many diffrent attribute methods that can be giving to different assets to differentiate and classify them. 
 * 
 * @author Group 10
 *
 */
public interface Asset {

	Image getImage(); // get an image at an specified location in our asset array intractable

	// ------------------------------------------------ Asset Attributes ------------------------------------------------ \\

	// Can be killed, in this case it only refers to the player, as a player
	// currently can't kill anything
	boolean killable();

	// Can kill something that is killable
	boolean canKill();

	// Is interactble, something the player can interact with for exemple a treasure
	boolean intractable();

	// Something any moving entity that moves can walk over
	boolean canWalkOn();

	// Entity that moves in multible directions
	boolean hasDirections(Direction d);

	// Animations
	void paintAsset(Graphics g, GamePanel gp);
}