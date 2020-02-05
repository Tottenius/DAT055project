package viewer;

import java.awt.Image;

public class Player extends Asset {

	private static final String path = "src/assets/PlayerModel.png";
	
	private Image img;
	
	public Player(int x, int y, String path) {
		super(x, y, path);
		
	}
	
    public void movePlayer(int x, int y) {
    	
    }

}
