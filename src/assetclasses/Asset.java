package assetclasses;

import java.awt.Graphics;
import java.awt.Image;

import Controller.Direction;
import viewer.GamePanel;

public interface Asset {
    
	public Image getImage();  //get an image at an specified location in our asset array intractable
    
    // ------------------------------------------------ Asset Attributes ------------------------------------------------ \\
    
    //Can be killed, in this case it only refers to the player, as a player currently can't kill anything
    public boolean killable();
    //Can kill something that is killable
    public boolean canKill();
    //Is interactble, something the player can interact with for exemple a treasure
    public boolean intractable();
    //Something any moving entity that moves can walk over
    public boolean canWalkOn();
    //Entity that moves in multible directions
    public boolean hasDirections(Direction d);
    
    //Animations
    public void setPrevPos();
    public void paintAsset(Graphics g, GamePanel gp);
}