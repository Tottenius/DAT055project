package assetclasses;

import Controller.Direction;

//Spike to dodge or player will die
public class Spikes extends AbstractAsset {

	private static final String path = "src/assets/spikes.png";

	
	public Spikes(int position) {
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
		return true;
	}


	@Override
	public boolean intractable() {
		return false;
	}


	@Override
	public boolean canWalkOn() {
		return false;
	}


	@Override
	public boolean hasDirections() {
		return false;
	}	
}
