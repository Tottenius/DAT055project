package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * Handles the loading of asset images.
 *
 * @author Anton & Tor
 * @version 2020-03-13
 */
public class AssetImageHandler {

    private final static int size = GameSettings.getAssetsize();

    /**
     * Loads in an image, determined by the path parameter,
     * The method also scales the image the gets loaded in such that all assets are the same size.
     *
     * @return An image
     */
    public static Image loadImage(final String path) {
        Image img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        // Scaling the image so all images loaded in are the same size

        img = Objects.requireNonNull(img).getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return img;
    }
}
