package viewer;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Treasure extends Asset{
	
	private static final String path = "src/assets/treasure.png";

    public Treasure(int x, int y) {
        super(x, y, path);
    }
}
