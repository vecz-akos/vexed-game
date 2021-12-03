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

	private Text scoreText;
	private int playerScore;
	private Text levelText;
	private int currentLevel;
	private final int levelsNum;
	private Text moveNumText;
	private int moveNum;

	private Button reloadButton;
	private Button nextLvlButton;
	private Button quitButton;

	InfoPanel(Point2D pos, double width, double height, Vexed vexed, int levelsNum) {
		panel = new Group();
		position = pos;
		this.width = width;
		this.height = height;
		insideMargin = height/16;
		playerScore = 0;
		this.levelsNum = levelsNum;

		scoreText = new Text(position.getX(), position.getY(), generateScoreText());
		setFontStyle(scoreText);

		levelText = new Text(position.getX(), position.getY() + 5*insideMargin, generateLevelText());
		setFontStyle(levelText);

		moveNumText = new Text(position.getX(), position.getY() + 10*insideMargin, generateMoveNumText());
		setFontStyle(moveNumText);

		initButtons();
		reloadButton.setOnAction(value -> vexed.loadLevel());
		nextLvlButton.setOnAction(value -> vexed.nextLevel());
		quitButton.setOnAction(value -> vexed.quit());

		panel.getChildren().addAll(scoreText, levelText, moveNumText, reloadButton, nextLvlButton, quitButton);
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
		String quitButtonStyle = buttonStyle +
			"-fx-background-color: " + Colors.RED.rgb + ";";
		
		reloadButton = new Button("Reload");
		reloadButton.setMinHeight(height);
		reloadButton.setMinWidth(height);
		reloadButton.setStyle(reloadButtonStyle);
		reloadButton.setTranslateX(position.getX() + width - 4*insideMargin - 4*height);
		reloadButton.setTranslateY(position.getY() + height/2 - height/2);
		
		nextLvlButton = new Button("Next level");
		nextLvlButton.setMinHeight(height);
		nextLvlButton.setMinWidth(height);
		nextLvlButton.setStyle(nextLvlButtonStyle);
		nextLvlButton.setTranslateX(position.getX() + width - 2*insideMargin - 3*height);
		nextLvlButton.setTranslateY(position.getY() + height/2 - height/2);
		
		quitButton = new Button("Quit");
		quitButton.setMinHeight(height);
		quitButton.setMinWidth(height);
		quitButton.setStyle(quitButtonStyle);
		quitButton.setTranslateX(position.getX() + width - 2*insideMargin - 3*height);
		quitButton.setTranslateY(position.getY() + height/2 - height/2);
		quitButton.setVisible(false);
	}

	public void addPlayerScore() {
		playerScore += moveNum;
		resetMoveNum();
		moveNumText.setText(generateMoveNumText());
		scoreText.setText(generateScoreText());
	}

	public void setCurrentLevel(int level) {
		currentLevel = level;
		levelText.setText(generateLevelText());
	}

	public void addMoveNum() {
		moveNum++;
		moveNumText.setText(generateMoveNumText());
	}

	public void resetMoveNum() {
		moveNum = 0;
		moveNumText.setText(generateMoveNumText());
	}

	public void reloadBtnOnOff() {
		reloadButton.setVisible(!reloadButton.isVisible());
	}

	public void nextLvlBtnOnOff() {
		quitButton.setVisible(false);
		nextLvlButton.setVisible(!nextLvlButton.isVisible());
	}

	public void quitBtnOnOff() {
		nextLvlButton.setVisible(false);
		quitButton.setVisible(!quitButton.isVisible());
	}

	private String generateScoreText() {
		return "Score: " + playerScore;
	}

	private String generateLevelText() {
		return "Level: " + currentLevel + " / " + levelsNum;
	}

	private String generateMoveNumText() {
		return "Moves: " + moveNum;
	}

	private void setFontStyle(Text txt) {
		txt.setFont(Font.font(insideMargin*4));
		txt.setFill(Colors.BLACK.getColor());
		txt.setTextOrigin(VPos.TOP);
	}
}
