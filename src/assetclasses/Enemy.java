package assetclasses;

import Controller.Direction;

public class Enemy extends AbstractAsset {
	
	private static final String down = "src/assets/headcrabMonsterAsset.png";
	private static final String up = "src/assets/headcrabMonsterAsset.png";
	private static final String left = "src/assets/headcrabMonsterAsset.png";;
	private static final String right = "src/assets/headcrabMonsterAsset.png";
	
	public Enemy(int position) {
		super(position);
		super.loadImage(up, Direction.UP);
		super.loadImage(down,Direction.DOWN);
		super.loadImage(left, Direction.LEFT);
		super.loadImage(right, Direction.RIGHT);
	}

	@Override
	public boolean killable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canKill() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean intractable() {
		// TODO Auto-generated method stub
		return false;
	}

}
