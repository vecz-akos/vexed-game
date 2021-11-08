package org.openjfx.vexed;

import javafx.scene.paint.Color;

public enum Colors {

	BLACK(9, "#1c1818"), WHITE(0, "#f9eaea"), RED(1, "#c62535"), YELLOW(2, "#F1AF34"), GREEN(3, "#86c625");

	private final int colorCode;
	private final String rgb;

	Colors(int colorCode, String rgb) {
		this.colorCode = colorCode;
		this.rgb = rgb;
		// switch (colorCode) {
		// case 9:
		// this.rgb = Color.BLACK;
		// break;
		// case 0:
		// this.rgb = Color.WHITE;
		// break;
		// case 1:
		// this.rgb = Color.RED;
		// break;
		// case 2:
		// this.rgb = Color.YELLOW;
		// break;
		// case 3:
		// this.rgb = Color.GREEN;
		// break;
		// default:
		// this.rgb = Color.WHITE;
		// }
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
