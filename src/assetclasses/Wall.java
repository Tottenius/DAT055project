package assetclasses;

import Controller.Direction;

public class Wall extends AbstractAsset{
	
	private static final String path = "src/assets/wall.png";

    public Wall(int position) {
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