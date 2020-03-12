package Controller;

import java.util.List;
import assetclasses.AbstractAsset;
import assetclasses.Empty;
import model.ReadInWorld;
/**
 * An abstract class that is used by other the other controller classes. 
 * It contains all movement methods and setters and getters for positions of the controllers
 * 
 * @author Group 10
 * @version 2020-03-06
 *
 */
public abstract class AssetController implements ControllerInterface {
	// Gameboard
	protected List<AbstractAsset> assets;// = GamePanel.getWorld().getAssetList();
	protected List<AbstractAsset> movingAssets;
	// Position
	protected int position;
	// Direction
	protected Direction direction;
	// world
	protected ReadInWorld world;
/**
 * Moves the asset and it's controller to another position by swapping positions of 
 * two assets in the list with movingAssets
 * @param ourAsset
 * The asset we want to move
 * @param assetTargetLocation
 * The asset that we want to move to
 */
	protected void moveAsset( final AbstractAsset ourAsset, final AbstractAsset assetTargetLocation) {
		// Sätt playerns positon till den nya positionen och ge objectet den nya
		int oldPos = ourAsset.getPosition();
		int newPos = assetTargetLocation.getPosition();
		// positionen
		this.movingAssets.set(newPos, ourAsset);
		// Sätt objetet vi rör oss till på playerns gamla position och ge det playerns
		// gamla position
		this.movingAssets.set(oldPos, assetTargetLocation);
		// Sätt controllerns position till den nya positionen
		setPosition(newPos);
		ourAsset.setPosition(newPos);
		assetTargetLocation.setPosition(oldPos);
	}
	/**
	 * Kills the the asset and replaces it with an empty asset
	 * @param oldPos
	 * The current position of the asset that's killed
	 */
	protected void dieWhileMovingIntoDanger(final int oldPos) {
		this.movingAssets.set(oldPos, new Empty(oldPos));
	}
	/**
	 * Kills the target on the new position and replaces it with an Empty asset in the moving assets list
	 * and then moves ourAsset to that location
	 * @param newPos
	 * The new position
	 * @param ourAsset
	 * The asset that kills the target at the new position
	 */
	protected void killAsset(final int newPos, final AbstractAsset ourAsset) {
		int oldPos = ourAsset.getPosition();
		// Den nya positionen
		this.movingAssets.set(newPos, ourAsset);
		ourAsset.setPosition(newPos);
		// Den gamla positionen
		this.movingAssets.set(oldPos, new Empty(oldPos));
		setPosition(newPos);
	}
	/**
	 * Changes the asset at a position in the non moving asset's list 
	 * @param pos
	 * The position where we change the asset
	 * @param newAsset
	 * The new asset we want on that position
	 */
	protected void changeAsset(final int pos, final AbstractAsset newAsset) {
		this.assets.set(pos, newAsset);
		newAsset.setPosition(pos);
	}
	/**
	 * A getter for the position of the controller
	 * @return 
	 * Returns the position of the controller
	 */
	protected int getPosition() {
		return this.position;
	}
	/**
	 * A setter for the position of the controller
	 * @param pos
	 * Sets the new position of the controller
	 */
	protected void setPosition(final int pos) {
		this.position = pos;
	}
	/**
	 * A basic abstract constructor that contains all common attributes for all controllers
	 * 
	 * @param position
	 * The position of the controller
	 * @param world
	 * The world in which the controller is present
	 */
	// Constructor
	public AssetController(final int position, final ReadInWorld world) {
		this.position = position;
		this.world = world;
		this.assets = world.getAssetList();
		this.movingAssets = world.getMovingAssets();
	}
}