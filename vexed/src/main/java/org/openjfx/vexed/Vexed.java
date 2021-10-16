package org.openjfx.vexed;

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
	
	GameBoard gameBoard;
	int currentLevel;
	int playerPoints;
	
	Vexed(Stage stage, int colNum, int rowNum, int squareSize) {
		gameBoard = new GameBoard(colNum, rowNum, squareSize);
		currentLevel = 0;
		playerPoints = 0;
		
		root = new Group();
		canvas = new Canvas(colNum * squareSize, rowNum * squareSize);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		scene = new Scene(
				root,
				colNum * squareSize,
				rowNum * squareSize,
				Color.LIGHTGRAY
		);
		this.stage = stage;
		stage.setTitle("Vexed");
		stage.setScene(scene);
		
		stage.show();
	}
	
	public void nextLevel() {
		++currentLevel;
		gameBoard.loadLevel(currentLevel);
	}
}
