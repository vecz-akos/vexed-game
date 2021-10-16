package org.openjfx.vexed;

import javafx.geometry.Point2D;

public class Square {
	int colorCode;
	Point2D offset;
	Direction moveDirection;
	
	Square() {
		colorCode = 0;
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
	}
	
	Square(int color) {
		colorCode = color;
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
	}
}
