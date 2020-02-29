package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;

//Spike to dodge or player will die
public class Spikes extends AbstractAsset {

	private static final String path = "src/assets/spikes.png";
	private static final String path2 ="src/assets/tile.png";
	private boolean up = true;
	private boolean canKill = true;
	private boolean canWalkOn = false;

	
	public Spikes(int position) {
        super(position);
        super.loadImage(path, Direction.UP);
        super.loadImage(path2, Direction.DOWN);
        super.getImage();
	}


	@Override
	public boolean killable() {
		return false;
	}


	@Override
	public boolean canKill() {
		return canKill;
	}


	@Override
	public boolean intractable() {
		return false;
	}


	@Override
	public boolean canWalkOn() {
		return canWalkOn;
	}


	@Override
	public boolean hasDirections(Direction d) {
		return false;
	}
	
	public void changeUpOrDown() {
		up = !up;
		canKill = !canKill;
		canWalkOn = !canWalkOn;
	}


	@Override
	public void paintAsset(Graphics g, GamePanel gp) {
		
		if(up) {
			this.getImageAtMap(direction.UP);
			g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
		}
		else {	
			this.getImageAtMap(direction.DOWN);
			g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);			
		}
	}


	@Override
	public void setPrevPos() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean hasMultibleStates() {
		// TODO Auto-generated method stub
		return true;
	}
}