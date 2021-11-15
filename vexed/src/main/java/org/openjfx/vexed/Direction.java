package org.openjfx.vexed;

public enum Direction {

	         UP(1),
	LEFT(4), NOMOVE(0), RIGHT(2),
	         DOWN(3);

	private int directionCode;
	private int x, y;

	Direction(int directionCode) {
		this.directionCode = directionCode;
		switch (this.directionCode) {
		case 0:
			x = 0;
			y = 0;
			break;
		case 1:
			x = 0;
			y = -1;
			break;
		case 2:
			x = 1;
			y = 0;
			break;
		case 3:
			x = 0;
			y = 1;
			break;
		case 4:
			x = -1;
			y = 0;
			break;
		default:
			break;
		}
	}

	public int getDirCode() {
		return directionCode;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
