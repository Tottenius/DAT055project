package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;

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
	public boolean hasDirections(Direction d) {
		return false;
	}

	@Override
	public void paintAsset(Graphics g, GamePanel gp) {
		g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
	}

	@Override
	public void setPrevPos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasMultibleStates() {
		// TODO Auto-generated method stub
		return false;
	}
    
}