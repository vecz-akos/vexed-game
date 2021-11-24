package org.openjfx.vexed;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Triangle {
	private double[] triangleXCoords = {0.0, 0.0, 0.0};
	private double[] triangleYCoords = {0.0, 0.0, 0.0};
	private boolean visible = false;
	private double size;
	private double margin;

	Triangle(double size) {
		this.size = size;
		margin = size/16;
	}

	public void drawTo(GraphicsContext gc) {
		if (!visible)
			return;
		gc.setFill(Colors.WHITE.getColor());
		gc.fillPolygon(triangleXCoords, triangleYCoords, 3);
	}

	public void reset() {
		visible = false;
	}

	public void setLeftDirection(Point2D topLeft) {
		triangleXCoords[0] = topLeft.getX() + size/2 - margin;
		triangleXCoords[1] = topLeft.getX() + size/2 - margin;
		triangleXCoords[2] = topLeft.getX() + margin;
		triangleYCoords[0] = topLeft.getY() + margin;
		triangleYCoords[1] = topLeft.getY() + size - margin;
		triangleYCoords[2] = topLeft.getY() + size/2;
		visible = true;
	}

	public void setRightDirection(Point2D topLeft) {
		triangleXCoords[0] = topLeft.getX() + size/2 + margin;
		triangleXCoords[1] = topLeft.getX() + size/2 + margin;
		triangleXCoords[2] = topLeft.getX() + size - margin;
		triangleYCoords[0] = topLeft.getY() + margin;
		triangleYCoords[1] = topLeft.getY() + size - margin;
		triangleYCoords[2] = topLeft.getY() + size/2;
		visible = true;
	}
}
