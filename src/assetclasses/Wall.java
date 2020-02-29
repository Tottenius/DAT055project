package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;
import viewer.GameSettings;

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
		return false;
	}

	@Override
	public boolean hasDirections(Direction d) {
		return false;
	}

	@Override
	public void paintAsset(Graphics g, GamePanel gp) {
		g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
		//GamePanel.redrawSpecified(getCoords().x,getCoords().y,GameSettings.getWidth(),GameSettings.getHeight());
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