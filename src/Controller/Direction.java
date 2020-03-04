package Controller;

import viewer.GameSettings;

// Directions
public enum Direction {

	UP(0, -(GameSettings.getWidth() / GameSettings.getAssetsize())),
	DOWN(0, GameSettings.getWidth() / GameSettings.getAssetsize()), LEFT(-1, 0), RIGHT(1, 0);

	private final int yDelta;
	private final int xDelta;

	Direction(final int xDelta, final int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}

	public int getXDelta() {
		return this.xDelta;
	}

	public int getYDelta() {
		return this.yDelta;
	}
}