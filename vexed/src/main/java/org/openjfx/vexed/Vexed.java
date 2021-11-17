package org.openjfx.vexed;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Vexed {

	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	Stage stage;
	Canvas canvas;
	AnimationTimer timer;

	private GameBoard gameBoard;
	private int currentLevelIndex;
	private int playerPoints;
	private boolean isGameEnded;

	Vexed(Stage stage, int colNum, int rowNum, int squareSize) {
		root = new Group();
		gameBoard = new GameBoard(colNum, rowNum, squareSize, root);
		currentLevelIndex = -1;
		playerPoints = 0;
		isGameEnded = false;

		canvas = new Canvas(colNum * squareSize, rowNum * squareSize);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		scene = new Scene(root, colNum * squareSize, rowNum * squareSize, Color.LIGHTGRAY);
		this.stage = stage;
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
			if (gameBoard.countMoveableSquares() == 0)
				nextLevel();
			gameBoard.update();
		}
		
		
	}

	private void nextLevel() {
		++currentLevelIndex;
		if (currentLevelIndex >= gameBoard.levelsNum) {
			endGame();
			return;
		}
		
		gameBoard.loadLevel(currentLevelIndex);
	}
	
	private void endGame() {
		isGameEnded = true;
		currentLevelIndex = gameBoard.levelsNum - 1;
		System.out.println("end of the game :)");
	}
}
