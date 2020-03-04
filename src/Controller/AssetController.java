package Controller;

import java.util.List;
import assetclasses.AbstractAsset;
import assetclasses.Empty;
import assetclasses.Tile;
import viewer.ReadInWorld;

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

	protected void moveAsset(final int newPos, final int oldPos, final AbstractAsset ourAsset,
			final AbstractAsset assetTargetLocation) {
		// Sätt playerns positon till den nya positionen och ge objectet den nya
		// positionen
		this.movingAssets.set(newPos, ourAsset);
		// Sätt objetet vi rör oss till på playerns gamla position och ge det playerns
		// gamla position
		this.movingAssets.set(oldPos, assetTargetLocation);
		// Sätt controllerns position till den nya positionen
		setPosition(newPos);
		ourAsset.setPosition(newPos);
		assetTargetLocation.setPosition(oldPos);
		// System.out.println(ourAsset.getCoords() + " " +
		// assetTargetLocation.getCoords());
	}

	protected void dieWhileMovingIntoDanger(final int oldPos) {
		// Tile t = new Tile(oldPos);
		this.movingAssets.set(oldPos, new Empty(oldPos));
	}

	protected void killAsset(final int newPos, final int oldPos, final AbstractAsset ourAsset) {
		// Den nya positionen
		this.assets.set(newPos, ourAsset);
		ourAsset.setPosition(newPos);
		// Den gamla positionen
		this.assets.set(oldPos, new Tile(oldPos));
		setPosition(newPos);
	}

	protected void changeAsset(final int pos, final AbstractAsset newAsset) {
		this.assets.set(pos, newAsset);
		newAsset.setPosition(pos);
	}

	protected int getPosition() {
		return this.position;
	}

	protected void setPosition(final int pos) {
		this.position = pos;
	}

	// Constructor
	public AssetController(final int position, final ReadInWorld world) {
		this.position = position;
		this.world = world;
		this.assets = world.getAssetList();
		this.movingAssets = world.getMovingAssets();
	}
}