package org.openjfx.vexed;

import javafx.scene.paint.Color;

public enum Colors {

	BLACK(9, "#1c1818"), WHITE(0, "#f9eaea"), RED(1, "#c62535"), YELLOW(2, "#F1AF34"), GREEN(3, "#86c625");

	private final int colorCode;
	final String rgb;

	Colors(int colorCode, String rgb) {
		this.colorCode = colorCode;
		this.rgb = rgb;
	}

	public static Colors getColor(int colorCode) {
		switch (colorCode) {
		case 9:
			return BLACK;
		case 0:
			return WHITE;
		case 1:
			return RED;
		case 2:
			return YELLOW;
		case 3:
			return GREEN;
		default:
			return WHITE;
		}
	}

	public Color getColor() {
		return Color.web(rgb);
	}

	public int getColorCode() {
		return colorCode;
	}
}
