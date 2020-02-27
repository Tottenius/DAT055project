package assetclasses;

import Controller.Direction;

public class Tile extends AbstractAsset{
	//test
	private static final String path = "src/assets/tile.png";
	//private static final String name = "Tile";

    public Tile(int position) {
        super(position);
        super.loadImage(path, Direction.DOWN);
        super.getImage();
    }

	@Override
	public boolean killable() {
		return false;
	}

	@Override
	public boolean canKill() {
		return false;
	}

	@Override
	public boolean intractable() {
		return false;
	}

	@Override
	public boolean canWalkOn() {
		return true;
	}

	@Override
	public boolean hasDirections() {
		return false;
	}
    
}