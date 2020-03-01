package assetclasses;

import java.awt.Graphics;

import Controller.Direction;
import viewer.GamePanel;
import viewer.GameWindowTemp;
import viewer.ReadInWorld;

//Door to go to next room!? are we even gonna do more then 1 room?? Could ranomize what next room is or maybe make it static
public class Door extends AbstractAsset {

	private static final String path1 = "src/assets/doordown.png";
	private static final String path2 = "src/assets/doorup.png";
	
	public Door(int position) {
        super(position);
        super.loadImage(path1, Direction.DOWN);
        super.loadImage(path2, Direction.UP);
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
		
		if(Treasure.getOpenedTreasures() == ReadInWorld.numberOfTresures) {
		
		System.out.println("player reached door!");
		//restart and new level doing
		GameWindowTemp.setNextLevelState();
		//player goes to next level!
		return true;
		}
		else
			System.out.println("You have to collect all the treasures first to open the door!");
		return true;
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
	}
}