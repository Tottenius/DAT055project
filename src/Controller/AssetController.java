package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import assetclasses.Asset;
import viewer.GamePanel;
import viewer.GamePanel.Direction;
import viewer.GameSettings;

public abstract class AssetController {
	
	protected final static int WIDTH = GameSettings.getWidth();
	protected final static int HEIGHT = GameSettings.getHeight();
	protected final static int SPACE = GameSettings.getAssetsize();
	//Dirction
	protected Direction direction;
	//Spelplanen
	private ArrayList<Asset> assets;
	
	public AssetController(Direction direction, ArrayList<Asset> assets){
		assets = this.assets;
		direction = this.direction;
	}

}