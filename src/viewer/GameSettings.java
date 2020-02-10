package viewer;

public abstract class GameSettings {
	private static final int ASSETSIZE = 40;
	private static final int WIDTH = ASSETSIZE*32;
	private static final int HEIGHT = ASSETSIZE*24;
	
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}

	public static int getAssetsize() {
		return ASSETSIZE;
	}
	
	
}
