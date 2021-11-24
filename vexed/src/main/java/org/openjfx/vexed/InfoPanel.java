package org.openjfx.vexed;

import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoPanel {
	private Group root;
	private Point2D position;
	public double width;
	public double height;
	private double insideMargin;

	private Text pointText;
	private int playerPoints;
	private Text levelText;
	private int currentLevel;

	InfoPanel(Group root, Point2D pos, double width, double height) {
		this.root = root;
		position = pos;
		this.width = width;
		this.height = height;
		insideMargin = height/8;
		playerPoints = 0;

		pointText = new Text(position.getX(), position.getY(), generatePointText());
		setFontStyle(pointText);

		levelText = new Text(position.getX(), position.getY() + 3*insideMargin, generateLevelText());
		setFontStyle(levelText);
	}

	public void attach() {
		root.getChildren().addAll(pointText, levelText);
	}

	public void addPlayerPoints(int points) {
		playerPoints += points;
		pointText.setText(generatePointText());
	}

	public void setCurrentLevel(int level) {
		currentLevel = level;
		levelText.setText(generateLevelText());
	}

	private String generatePointText() {
		return "Your points: " + playerPoints;
	}

	private String generateLevelText() {
		return "Level " + currentLevel;
	}

	private void setFontStyle(Text txt) {
		txt.setFont(Font.font(insideMargin*2));
		txt.setFill(Colors.BLACK.getColor());
		txt.setTextOrigin(VPos.TOP);
	}
}
