package model;
/**
 * An enum defining the settings for the Game. Also has methods for getting these settings.
 * @author Group 10
 *
 */
public enum GameSettings {
	;
	private static final int ASSETSIZE = 30;
	private static final int WIDTH = ASSETSIZE*32;
	private static final int HEIGHT = ASSETSIZE*24;
	
	/**
	 * Gives the games Width.
	 * @return Game width
	 */
	public static int getWidth() {
		return WIDTH;
	}
	/**
	 * Gives the games Height.
	 * @return Game height
	 */
	public static int getHeight() {
		return HEIGHT;
	}
/**
 * Gives the games Assetsize.
 * @return Assetsize
 */
	public static int getAssetsize() {
		return ASSETSIZE;
	}
}
