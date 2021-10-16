package org.openjfx.vexed;

public class GameBoard {
	
	Square[][] board;
	int squareSize;
	final Direction gravityDirection;
	
	GameBoard(int colNum, int rowNum, int squareSize) {
		board = new Square[rowNum][colNum];
		this.squareSize = squareSize;
		gravityDirection = Direction.DOWN;
	}
	
	public Square get(int row, int col) {
		return board[col][row];
	}
	
	public void loadLevel(int levelNum) {
		//TODO
		System.out.println("Loading level " + levelNum);
	}
}
