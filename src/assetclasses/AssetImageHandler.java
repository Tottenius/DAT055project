package assetclasses;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import Controller.Direction;
import viewer.GameSettings;

public class AssetImageHandler {

	private final static int size = GameSettings.getAssetsize();
	
	/**
	 * Loads in an image, determined by the path and direction parameters, 
	 * the direction is used to store different images for the same assets.
	 * The method also scales the image the gets loaded in such that all assets are the same size.
	 * 
	 * @param path
	 * @param direction
	 */
	public static Image loadImage(final String path) {
		Image img = null;
		try {
			 img = ImageIO.read(new File(path));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// Scaling the image so all images loaded in are the same size
		img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		return img;
		}
	}
