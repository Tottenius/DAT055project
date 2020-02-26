package assetclasses;

import Controller.Direction;

//Door to go to next room!? are we even gonna do more then 1 room?? Could ranomize what next room is or maybe make it static
public class Door extends AbstractAsset {

	private static final String path = "src/assets/tile.png";
	
	public Door(int position) {
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



}
