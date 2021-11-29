package org.openjfx.vexed;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class Vexed {

	private Group root;
	private Scene scene;
	Stage stage;
	Canvas canvas;
	AnimationTimer timer;

	private int margin;
	private InfoPanel infoPanel;
	private GameBoard gameBoard;
	private int currentLevelIndex;
	private boolean isGameEnded;
	private boolean waitToUser = false;

	Vexed(Stage stage, int colNum, int rowNum, int squareSize) {
		root = new Group();
		margin = squareSize/4;
		currentLevelIndex = -1;
		isGameEnded = false;

		canvas = new Canvas(colNum * squareSize, rowNum * squareSize);
		canvas.setTranslateX(margin);
		canvas.setTranslateY(margin);
		root.getChildren().add(canvas);
		gameBoard = new GameBoard(colNum, rowNum, squareSize, canvas);
		infoPanel = new InfoPanel(new Point2D(margin, 2*margin + rowNum*squareSize), canvas.getWidth(), squareSize, this);
		infoPanel.attach(root);
		scene = new Scene(
			root,
			canvas.getWidth() + 2*margin,
			canvas.getHeight() + 3*margin + infoPanel.height,
			Colors.WHITE.getColor()
		);
		this.stage = stage;
		stage.setResizable(false);
		stage.setTitle("Vexed");
		stage.setScene(scene);
		nextLevel();

		stage.show();

		timer = new AnimationTimer() {
			public void handle(long now) {
				update();
			}
		};

		timer.start();
	}
	
	private void update() {
		if (isGameEnded) {
			return;
		} else {
			if (gameBoard.countMoveableSquares() == 0 && !waitToUser) {
				waitToUser = true;
				infoPanel.nextLvlBtnOnOff();
			}
			gameBoard.update();
		}
	}

	public void nextLevel() {
		waitToUser = false;
		++currentLevelIndex;
		if (currentLevelIndex >= gameBoard.levelsNum) {
			endGame();
			return;
		}

		infoPanel.addPlayerPoints(2);
		infoPanel.setCurrentLevel(currentLevelIndex+1);
		loadLevel();
	}
	
	private void loadLevel(int levelIndex) {
		gameBoard.loadLevel(levelIndex);
	}
	
	public void loadLevel() {
		loadLevel(currentLevelIndex);
	}
	
	private void endGame() {
		isGameEnded = true;
		currentLevelIndex = gameBoard.levelsNum - 1;
		System.out.println("end of the game :)");
	}
}
