package Controller;

import java.util.ArrayList;
import assetclasses.Asset;
import viewer.GamePanel.Direction;
import viewer.GameSettings;

public abstract class AssetController {
	
	protected final static int WIDTH = GameSettings.getWidth();
	protected final static int HEIGHT = GameSettings.getHeight();
	protected final static int SIZE = GameSettings.getAssetsize();
	//Position
	protected int position;
	//Dirction
	protected Direction direction;
	//Spelplanen
	private ArrayList<Asset> assets;
	
	public AssetController(Direction direction, ArrayList<Asset> assets, int position){
		assets = this.assets;
		direction = this.direction;
		setPosition (position);
	}

	protected int getPosition() {
		return position;
	}
	protected void setPosition(int pos) {
		position = pos;
	}
}
