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
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public boolean canWalkOn() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean hasDirections() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
