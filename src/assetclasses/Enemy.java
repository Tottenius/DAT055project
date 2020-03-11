package assetclasses;

import java.awt.Graphics;
import java.awt.Image;

import Controller.Direction;
import viewer.GamePanel;
/**
 * An class for an enemy asset
 * 
 * @author Group 10
 *
 */
public class Enemy extends AbstractAsset {

    private static final String path = "src/assets/headcrabMonsterAsset.png";
    private static Image image = AssetImageHandler.loadImage(path);

    public Enemy(final int position) {
        super(position);
    }

    @Override
    public boolean killable() {
        return false;
    }

    @Override
    public boolean canKill() {
        return true;
    }

    @Override
    public boolean intractable() {
        return false;
    }

    @Override
    public boolean canWalkOn() {
        return false;
    }

    @Override
    public boolean hasDirections(final Direction d) {
        return false;
    }

    @Override
    public void paintAsset(final Graphics g, final GamePanel gp) {
        g.drawImage(image, getCoords().x, getCoords().y, gp);
    }
}