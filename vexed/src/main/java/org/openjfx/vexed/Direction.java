package org.openjfx.vexed;

import javafx.geometry.Point2D;

public enum Direction {

	UP(1, "up"), LEFT(4, "left"), NOMOVE(0, "nomove"), RIGHT(2, "right"), DOWN(3, "down");

	private int directionCode;
	private String name;
	private Point2D vector;

	Direction(int directionCode, String name) {
		this.directionCode = directionCode;
		this.name = name;
		switch (this.directionCode) {
		case 0:
			vector = new Point2D(0, 0);
			break;
		case 1:
			vector = new Point2D(0, -1);
			break;
		case 2:
			vector = new Point2D(1, 0);
			break;
		case 3:
			vector = new Point2D(0, 1);
			break;
		case 4:
			vector = new Point2D(-1, 0);
			break;
		default:
			break;
		}
	}

	public int getDirCode() {
		return directionCode;
	}

	public Point2D getVector() {
		return vector;
	}

	public double getX() {
		return vector.getX();
	}

	public double getY() {
		return vector.getY();
	}
}
