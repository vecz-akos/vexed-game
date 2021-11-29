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
	private Button nextLvlButton;

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

		initButtons();
		reloadButton.setOnAction(value -> vexed.loadLevel());
		nextLvlButton.setOnAction(value -> {vexed.nextLevel(); nextLvlBtnOnOff();});

		panel.getChildren().addAll(pointText, levelText, reloadButton, nextLvlButton);
	}

	public void attach(Group parent) {
		parent.getChildren().add(panel);
	}

	private void initButtons() {
		String buttonStyle =
			"-fx-text-fill: " + Colors.WHITE.rgb + ";" +
			"-fx-cursor: hand;";
		String reloadButtonStyle = buttonStyle +
			"-fx-background-color: " + Colors.GREEN.rgb + ";";
		String nextLvlButtonStyle = buttonStyle +
			"-fx-background-color: " + Colors.YELLOW.rgb + ";";
		
		reloadButton = new Button("Reload");
		reloadButton.setMinHeight(height);
		reloadButton.setStyle(reloadButtonStyle);
		reloadButton.setTranslateX(position.getX() + width - 2*insideMargin - 4*height);
		reloadButton.setTranslateY(position.getY() + height/2 - height/2);
		
		nextLvlButton = new Button("Next level");
		nextLvlButton.setMinHeight(height);
		nextLvlButton.setStyle(nextLvlButtonStyle);
		nextLvlButton.setTranslateX(position.getX() + width - insideMargin - 3*height);
		nextLvlButton.setTranslateY(position.getY() + height/2 - height/2);
		nextLvlButton.setVisible(false);
	}

	public void addPlayerPoints(int points) {
		playerPoints += points;
		pointText.setText(generatePointText());
	}

	public void setCurrentLevel(int level) {
		currentLevel = level;
		levelText.setText(generateLevelText());
	}

	public void nextLvlBtnOnOff() {
		nextLvlButton.setVisible(!nextLvlButton.isVisible());
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
