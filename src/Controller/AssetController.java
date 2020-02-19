package Controller;

import java.util.List;

import assetclasses.AbstractAsset;
import assetclasses.Tile;
import viewer.GamePanel;
import viewer.GameSettings;

public abstract class AssetController {
	//Gameboard
	protected List<AbstractAsset> assets =  GamePanel.getAssetList();

	protected final static int WIDTH = GameSettings.getWidth();
	protected final static int HEIGHT = GameSettings.getHeight();
	protected final static int SIZE = GameSettings.getAssetsize();
	//Position
	protected int position;
	//Direction
	protected Direction direction;
	
	protected void moveAsset(int newPos, int oldPos, AbstractAsset ourAsset, AbstractAsset assetTargetLocation) {
		// Sätt playerns positon till den nya positionen och ge objectet den nya positionen
		assets.set(newPos, ourAsset ).setPosition(oldPos);
		// Sätt objetet vi rör oss till på playerns gamla position och ge det playerns gamla position
		assets.set(oldPos, assetTargetLocation ).setPosition(newPos);
		// Sätt controllerns position till den nya positionen
		setPosition(newPos);
	}
	
	protected void dieWhileMovingIntoDanger(int oldPos, int newPos) {
		assets.set(oldPos, new Tile(oldPos) ).setPosition(newPos);
	}
	
	protected void killAsset(int newPos, int oldPos, AbstractAsset ourAsset) {
		// Den nya positionen
		assets.set(newPos, ourAsset ).setPosition(oldPos);
		// Den gamla positionen
		assets.set(oldPos, new Tile(oldPos) ).setPosition(newPos);
		setPosition(newPos);
	}
	
	public AssetController( int position){
		this.position = position;
	}

	protected int getPosition() {
		return position;
	}
	protected void setPosition(int pos) {
		this.position = pos;
	}
}