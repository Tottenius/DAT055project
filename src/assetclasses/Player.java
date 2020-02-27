package assetclasses;

import java.awt.Graphics;
import java.awt.Point;

import Controller.Direction;
import viewer.GamePanel;
import viewer.GameSettings;

public class Player extends AbstractAsset {

	private static final String down = "src/assets/PlayerDown.png";
	private static final String up = "src/assets/PlayerUp.png";
	private static final String left = "src/assets/PlayerLeft.png";
	private static final String right = "src/assets/PlayerRight.png";
	
	private boolean alive;
	private Point prevPos = getCoords();
	private Direction dir = Direction.RIGHT;

	public Player(int position) {
		super(position);
		super.loadImage(up, Direction.UP);
		super.loadImage(down,Direction.DOWN);
		super.loadImage(left, Direction.LEFT);
		super.loadImage(right, Direction.RIGHT);
		alive = true;
	}


	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	@Override
	public boolean killable() {
		return true;
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
		this.direction = d;
		getImageAtMap(d);
		return true;
	}
	@Override
	public void setPrevPos() {
		prevPos.x = getCoords().x - direction.getXDelta() * GameSettings.getAssetsize();
		prevPos.y = getCoords().y - direction.getYDelta() * GameSettings.getAssetsize()/32;
	}
	

	@Override
	public void paintAsset(Graphics g, GamePanel gp) {
		g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
		/*
		if(prevPos.x != getCoords().x || prevPos.y != getCoords().y ) {	
			g.drawImage(this.getImage(), prevPos.x, prevPos.y, gp);
			prevPos.x = prevPos.x + (direction.getXDelta())* 2;
			prevPos.y = prevPos.y + (direction.getYDelta()/32)*2;
		}
		else {
			g.drawImage(this.getImage(), getCoords().x, getCoords().y, gp);
		}
		*/
		
		
		
	}
}