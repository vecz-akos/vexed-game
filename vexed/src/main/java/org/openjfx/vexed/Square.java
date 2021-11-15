package org.openjfx.vexed;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
	private Rectangle rectangle;
	private Point2D position;
	private Point2D offset;
	private Direction moveDirection;

	public Colors color;
	public Boolean waitToDelete;

	Square() {
		color = Colors.getColor(0);
		rectangle = new Rectangle(0, 0, 0, 0);
		rectangle.setFill(getColor());
		position = new Point2D(0, 0);
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
		waitToDelete = false;
	}

	Square(int x, int y, int size, int color) {
		this.color = Colors.getColor(color);
		rectangle = new Rectangle(x, y, size, size);
		rectangle.setFill(getColor());
		position = new Point2D(x, y);
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
		waitToDelete = false;

		rectangle.setOnMouseClicked((new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getX() > getX() + size / 2) {
					setDirection(Direction.RIGHT);
				} else {
					setDirection(Direction.LEFT);
				}
			}
		}));
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

	public void setColor(Colors color) {
		this.color = color;
		rectangle.setFill(getColor());
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	public int getCol() {
		return (int) (position.getX() / rectangle.getWidth());
	}

	public int getRow() {
		return (int) (position.getY() / rectangle.getHeight());
	}

	public Direction getMoveDirection() {
		return moveDirection;
	}

	public void setDirection(Direction dir) {
		if (isMoveable())
			moveDirection = dir;
	}

	public void reset() {
		if (isMoveable()) {
			color = Colors.WHITE;
			moveDirection = Direction.NOMOVE;
			rectangle.setFill(getColor());
			offset = new Point2D(0, 0);
			waitToDelete = false;
		}
	}
}
