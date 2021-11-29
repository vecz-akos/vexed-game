package org.openjfx.vexed;

import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoPanel {
	private Group panel;
	private Point2D position;
	public double width;
	public double height;
	private double insideMargin;

	private Text pointText;
	private int playerPoints;
	private Text levelText;
	private int currentLevel;

	private Button reloadButton;

	InfoPanel(Point2D pos, double width, double height, Vexed vexed) {
		panel = new Group();
		position = pos;
		this.width = width;
		this.height = height;
		insideMargin = height/8;
		playerPoints = 0;

		pointText = new Text(position.getX(), position.getY(), generatePointText());
		setFontStyle(pointText);

		levelText = new Text(position.getX(), position.getY() + 3*insideMargin, generateLevelText());
		setFontStyle(levelText);

		String buttonStyle =
			"-fx-background-color: " + Colors.GREEN.rgb + ";" +
			"-fx-text-fill: " + Colors.WHITE.rgb + ";";
		reloadButton = new Button("Reload");
		reloadButton.setMinHeight(height);
		reloadButton.setStyle(buttonStyle);
		reloadButton.setTranslateX(pos.getX() + width - insideMargin - 3*height);
		reloadButton.setTranslateY(pos.getY() + height/2 - height/2);

		reloadButton.setOnAction(value -> vexed.loadLevel());

		panel.getChildren().addAll(pointText, levelText, reloadButton);
	}

	public void attach(Group parent) {
		parent.getChildren().add(panel);
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
