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
		return false;
	}

	@Override
	public boolean hasDirections() {
		// TODO Auto-generated method stub
		return false;
	}  
}