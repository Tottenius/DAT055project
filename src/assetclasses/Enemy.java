package assetclasses;

public class Enemy extends Asset {
	
	private static final String down = "src/assets/headcrabMonsterAsset.png";
	private static final String up = "src/assets/headcrabMonsterAsset.png";
	private static final String left = "src/assets/headcrabMonsterAsset.png";;
	private static final String right = "src/assets/headcrabMonsterAsset.png";

	public Enemy(int position) {
		super(position, down);
		super.loadImage(up);
		super.loadImage(left);
		super.loadImage(right);
	}

}
