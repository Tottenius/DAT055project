package assetclasses;

import Controller.Direction;

public class Wall extends AbstractAsset{
	
	private static final String path = "src/assets/wall.png";

    public Wall(int position) {
        super(position);
        super.loadImage(path, Direction.DOWN);
        super.getImage();
    }  
}