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
	//private static  Image img;
	//protected Direction direction;
//	private static final  Map<Direction, Image> images = new HashMap<>();
	//private static final List<String> previouslyLoaded = new ArrayList<>();
	
	/**
	 * returns img field, that being the assets image represenation. 
	 * 
	 */
	//test
//	public static  Image getImage() {
//		return img;
//	}

	/**
	 * Set the image at an specified location in our asset array, the image it sets is determined by the direction param.
	 * 
	 * @param direction
	 */
	//public static void getImageAtMap(final Direction direction) {
	//	img = images.get(direction);
	//}

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
		//if(previouslyLoaded.contains(path))
		//	return;
		
		//else {
		//	previouslyLoaded.add(path);
			
		try {
			 img = ImageIO.read(new File(path));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// Scaling the image so all images loaded in are the same size
		img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		return img;
		
	//System.out.println("Load image " + images);
		}
		}
