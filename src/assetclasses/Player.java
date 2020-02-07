package assetclasses;


public class Player extends Asset {

	private static final String down = "src/assets/PlayerDown.png";
	private static final String up = "src/assets/PlayerUp.png";
	private static final String left = "src/assets/PlayerLeft.png";
	private static final String right = "src/assets/PlayerRight.png";

	
	
	
	public Player(int x, int y) {
		super(x, y, down);
		super.loadImage(up);
		super.loadImage(left);
		super.loadImage(right);
		
	}

	
}
