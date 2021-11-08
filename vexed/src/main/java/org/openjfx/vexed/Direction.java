package org.openjfx.vexed;

import javafx.geometry.Point2D;

public enum Direction {

	UP(1, "up"), LEFT(4, "left"), NOMOVE(0, "nomove"), RIGHT(2, "right"), DOWN(3, "down");

	private int directionCode;
	private String name;
	private int x, y;

	Direction(int directionCode, String name) {
		this.directionCode = directionCode;
		this.name = name;
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
