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
