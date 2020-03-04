package assetclasses;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import Controller.Direction;
import viewer.GameSettings;

//Abstract Asset class that is used by all assets in the game
public abstract class AbstractAsset implements Asset {

	private final static int size = GameSettings.getAssetsize();
	private int position;
	private Point coords;
	private Image img;
	protected Direction direction;

	// private ArrayList<Image> images = new ArrayList<Image>();
	private final Map<Direction, Image> images = new HashMap<>();

	public AbstractAsset(final int position) {
		this.setPosition(position);
		this.setCoords();
	}

	// returns the image
	@Override
	public Image getImage() {
		return this.img;
	}

	// get an image at an specified location in our asset array, hashmap
	public void getImageAtMap(final Direction direction) {
		this.img = this.images.get(direction);
	}

	// load in an image
	public void loadImage(final String path, final Direction direction) {
		Image imgTemp;

		try {
			this.img = ImageIO.read(new File(path));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// Scaling the image so all images loaded in are the same size
		imgTemp = this.img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		this.img = imgTemp;
		this.images.put(direction, this.img);
	}

	// get the array location for a specified image
	public int getPosition() {
		return this.position;
	}

	// set the array location for a specified image
	public void setPosition(final int position) {
		this.position = position;
		this.coords = new Point(position * GameSettings.getAssetsize() % GameSettings.getWidth(),
				position * GameSettings.getAssetsize() / GameSettings.getWidth() * GameSettings.getAssetsize());
	}

	public Point getCoords() {
		return this.coords;
	}

	public void setCoords(final Point coords) {
		this.coords = coords;
	}

	public void setCoords() {
		this.coords = new Point(this.position * GameSettings.getAssetsize() % GameSettings.getWidth(),
				this.position * GameSettings.getAssetsize() / GameSettings.getWidth() * GameSettings.getAssetsize());
	}

}
