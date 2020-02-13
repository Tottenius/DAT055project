package Controller;

import java.util.ArrayList;
import assetclasses.Asset;
import viewer.GamePanel.Direction;
import viewer.GamePanel;
import viewer.GameSettings;

public abstract class AssetController {
	
	//Gameboard
	private static ArrayList<Asset> assets = GamePanel.getAssetList();

	protected final static int WIDTH = GameSettings.getWidth();
	protected final static int HEIGHT = GameSettings.getHeight();
	protected final static int SIZE = GameSettings.getAssetsize();
	//Position
	protected int position;
	//Direction
	protected Direction direction;
	
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