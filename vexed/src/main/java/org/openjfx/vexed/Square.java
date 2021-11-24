package org.openjfx.vexed;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square {
	private Point2D position;
	private double size;
	private Point2D offset;
	private Direction moveDirection;

	public Colors color;
	public Boolean waitToDelete;

	Square() {
		color = Colors.getColor(0);
		position = new Point2D(0, 0);
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
		waitToDelete = false;
	}

	Square(int x, int y, int size, int color) {
		this.color = Colors.getColor(color);
		position = new Point2D(x, y);
		this.size = size;
		offset = new Point2D(0, 0);
		moveDirection = Direction.NOMOVE;
		waitToDelete = false;
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(getColor());
		gc.fillRect(
			position.getX() + offset.getX(),
			position.getY() + offset.getY(),
			size, size
		);
	}

	public boolean isMoveable() {
		return color.getColorCode() != 0 && color.getColorCode() != 9;
	}

	public Color getColor() {
		return color.getColor();
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public Point2D getPosition() {
		return position;
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	public int getCol() {
		return (int) (position.getX() / size);
	}

	public int getRow() {
		return (int) (position.getY() / size);
	}

	public Direction getMoveDirection() {
		return moveDirection;
	}

	public void setDirection(Direction dir) {
		if (isMoveable())
			moveDirection = dir;
	}

	public void setDirection(double x) {
		if (isMoveable()) {
			if (isItTheLeftSide(x)) {
				moveDirection = Direction.LEFT;
			} else {
				moveDirection = Direction.RIGHT;
			}
		}
	}

	public boolean isItTheLeftSide(double x) {
		if (x < getX() + (size / 2))
			return true;
		return false;
	}

	public void reset() {
		if (isMoveable()) {
			color = Colors.WHITE;
			moveDirection = Direction.NOMOVE;
			offset = new Point2D(0, 0);
			waitToDelete = false;
		}
	}
}
