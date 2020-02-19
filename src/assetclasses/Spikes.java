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
}
