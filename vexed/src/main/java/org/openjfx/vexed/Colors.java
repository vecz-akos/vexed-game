package org.openjfx.vexed;

import javafx.scene.paint.Color;

public enum Colors {

	WHITE(0, "#f9eaea"), RED(1, "#c62535"), YELLOW(2, "#F1AF34"),
	GREEN(3, "#86c625"), BLUE(4, "#1A5472"), PURPLE(5, "#C53FEE"),
	LIGHT_BLUE(6, "#98DEB5"), ORANGE(7, "#E67219"), PINK(8, "#FF9FAF"),
	BLACK(9, "#1c1818");

	private final int colorCode;
	final String rgb;

	Colors(int colorCode, String rgb) {
		this.colorCode = colorCode;
		this.rgb = rgb;
	}

	public static Colors getColor(int colorCode) {
		switch (colorCode) {
			case 0:
				return WHITE;
			case 9:
				return BLACK;
			case 1:
				return RED;
			case 2:
				return YELLOW;
			case 3:
				return GREEN;
			case 4:
				return BLUE;
			case 5:
				return PURPLE;
			case 6:
				return LIGHT_BLUE;
			case 7:
				return ORANGE;
			case 8:
				return PINK;
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
