package Controller;

import java.util.List;
import assetclasses.AbstractAsset;
import assetclasses.Tile;
import viewer.ReadInWorld;

public abstract class AssetController {
	//Gameboard
	protected List<AbstractAsset> assets;// =  GamePanel.getWorld().getAssetList();
	//Position
	protected int position;
	//Direction
	protected Direction direction;
	//world
	protected ReadInWorld world;
	
	protected void moveAsset(int newPos, int oldPos, AbstractAsset ourAsset, AbstractAsset assetTargetLocation) {
		// Sätt playerns positon till den nya positionen och ge objectet den nya positionen
		assets.set(newPos, ourAsset );
		// Sätt objetet vi rör oss till på playerns gamla position och ge det playerns gamla position
		assets.set(oldPos, assetTargetLocation );
		// Sätt controllerns position till den nya positionen
		setPosition(newPos);
		ourAsset.setPosition(newPos);
		assetTargetLocation.setPosition(oldPos);
		//System.out.println(ourAsset.getCoords() + " " + assetTargetLocation.getCoords());
	}
	
	protected void dieWhileMovingIntoDanger(int oldPos, int newPos) {
		//Tile t = new Tile(oldPos);
		assets.set(oldPos, new Tile(oldPos));
	}
	
	protected void killAsset(int newPos, int oldPos, AbstractAsset ourAsset) {
		// Den nya positionen
		assets.set(newPos, ourAsset );
		ourAsset.setPosition(newPos);
		// Den gamla positionen
		assets.set(oldPos, new Tile(oldPos));
		setPosition(newPos);
	}
	
	protected void changeAsset(int pos, AbstractAsset newAsset) {
		assets.set( pos, newAsset);
		newAsset.setPosition(pos);
	}
	
	protected int getPosition() {
		return position;
	}
	
	protected void setPosition(int pos ) {
		this.position = pos;
	}
	// Constructor
	public AssetController( int position, ReadInWorld world){
		this.position = position;
		this.world = world;
		assets = world.getAssetList();
	}	
}