package assetclasses;

public class Tile extends AbstractAsset{
	//test
	private static final String path = "src/assets/tile.png";
	//private static final String name = "Tile";

    public Tile(int position) {
        super(position);
        super.loadImage(path, direction.DOWN);
        super.getImage();
    }
    
}
