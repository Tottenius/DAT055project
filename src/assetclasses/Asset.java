package assetclasses;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import viewer.GameSettings;

public interface Asset {
    public Image getImage();  //get an image at an specified location in our asset array
}