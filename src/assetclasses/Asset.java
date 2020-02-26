package assetclasses;

import java.awt.Image;

public interface Asset {
    public Image getImage();  //get an image at an specified location in our asset array intractable
    
    public boolean killable();
    public boolean canKill();
    public boolean intractable();
    public boolean canWalkOn();
    public boolean hasDirections();
   // public void interacted();
    
}