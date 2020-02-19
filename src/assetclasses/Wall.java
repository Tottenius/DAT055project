package assetclasses;

public class Wall extends AbstractAsset{
	
	private static final String path = "src/assets/wall.png";

    public Wall(int position) {
        super(position);
        super.loadImage(path, direction.DOWN);
        super.getImage();
    }  
}