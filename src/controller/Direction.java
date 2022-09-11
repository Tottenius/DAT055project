package controller;

import model.GameSettings;

/**
 * Directions that also contains the information how to move in that direction 
 * according to the current direction. The directions are UP, DOWN, RIGHT and LEFT
 * 
 * @author Group 10
 * @version 2020-03-06
 *
 */
// Directions
public enum Direction {

	UP(0, -(GameSettings.getWidth() / GameSettings.getAssetsize())),
	DOWN(0, GameSettings.getWidth() / GameSettings.getAssetsize()), 
	LEFT(-1, 0), 
	RIGHT(1, 0);

	private final int yDelta;
	private final int xDelta;

	Direction(final int xDelta, final int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}
	/**
	 * Gives X for the current direction
	 * @return
	 * Returns the x value to move in the current direction
	 */
	public int getXDelta() {
		return this.xDelta;
	}
	/**
	 * Gives Y for the current direction
	 * @return
	 * Returns the y value to move in the current direction
	 */
	public int getYDelta() {
		return this.yDelta;
	}
}