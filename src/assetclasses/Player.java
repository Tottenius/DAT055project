package assetclasses;

import Controller.Direction;

public class Player extends AbstractAsset {

	private static final String down = "src/assets/PlayerDown.png";
	private static final String up = "src/assets/PlayerUp.png";
	private static final String left = "src/assets/PlayerLeft.png";
	private static final String right = "src/assets/PlayerRight.png";
	
	private boolean alive;


	public Player(int position) {
		super(position);
		super.loadImage(up, Direction.UP);
		super.loadImage(down,Direction.DOWN);
		super.loadImage(left, Direction.LEFT);
		super.loadImage(right, Direction.RIGHT);
		alive = true;
	}


	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	@Override
	public boolean killable() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean canKill() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean intractable() {
		// TODO Auto-generated method stub
		return false;
	}
}
