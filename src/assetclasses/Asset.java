package assetclasses;

import java.awt.Graphics;
import java.awt.Image;

import Controller.Direction;
import viewer.GamePanel;

public interface Asset {
    public Image getImage();  //get an image at an specified location in our asset array intractable
    
    //Asset Attributes
    public boolean killable();
    public boolean canKill();
    public boolean intractable();
    public boolean canWalkOn();
    public boolean hasDirections(Direction d);  
    public void setPrevPos();
    public void paintAsset(Graphics g, GamePanel gp);
}