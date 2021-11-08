package org.openjfx.vexed;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
	Rectangle rectangle;
	Point2D position;
	public Colors color;
	Point2D offset;
	Direction moveDirection;

	Square() {
		position = new Point2D(0, 0);
		color = Colors.getColor(0);
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
	}

	Square(int x, int y, int size, int color) {
		this.color = Colors.getColor(color);
		rectangle = new Rectangle(x, y, size, size);
		rectangle.setFill(getColor());
		rectangle.setOnMouseClicked((new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getX() > x + size / 2) {
					moveDirection = Direction.RIGHT;
				} else {
					moveDirection = Direction.LEFT;
				}
			}
		}));
		position = new Point2D(x, y);
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
	}

	public boolean isMoveable() {
		return color.getColorCode() != 0 && color.getColorCode() != 9;
	}

	public Rectangle getRectengle() {
		return rectangle;
	}

	public Color getColor() {
		return color.getColor();
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	public Direction getMoveDirection() {
		return moveDirection;
	}

	public void reset() {
		color = Colors.WHITE;
		moveDirection = Direction.NOMOVE;
		rectangle.setFill(getColor());
		offset = new Point2D(0, 0);
	}
}
