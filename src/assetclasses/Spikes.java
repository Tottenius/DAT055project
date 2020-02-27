package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;

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
	public boolean hasDirections(Direction d) {
		return false;
	}


	@Override
	public void paintAsset(Graphics g, GamePanel gp) {
		// TODO Auto-generated method stub
	}


	@Override
	public void setPrevPos() {
		// TODO Auto-generated method stub
		
	}
}