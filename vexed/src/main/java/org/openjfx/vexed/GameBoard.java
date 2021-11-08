package org.openjfx.vexed;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.geometry.Point2D;
import javafx.scene.Group;

public class GameBoard {

	private int[][][] levelsData;
	private Square[][] board;
	final int squareSize;
	final Direction gravityDirection;
	final int rowNum;
	final int colNum;
	final int levelsNum = 2;
	Group root;

	GameBoard(int colNum, int rowNum, int squareSize, Group root) {
		board = new Square[rowNum][colNum];
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.squareSize = squareSize;
		this.root = root;
		gravityDirection = Direction.DOWN;

		levelsData = new int[levelsNum][rowNum][colNum];
		readLevelFile();
	}

	private void readLevelFile() {
		try {
			String pathToLevelsFile = new File("src\\main\\java\\org\\openjfx\\vexed\\leveldata_test.txt")
					.getAbsolutePath();
			FileInputStream levelsDataReader = new FileInputStream(pathToLevelsFile);

			int i;
			byte lineCounter = 0;

			while (lineCounter < 4) {
				i = levelsDataReader.read();
				if (i == '\n') {
					++lineCounter;
				}
			}

			int level = 0, row = 0, col = 0;

			for (level = 0; level < levelsNum; ++level) {
				lineCounter = 0;
				while (lineCounter < 2) {
					i = levelsDataReader.read();
					if (i == '\n') {
						++lineCounter;
					}
				}
				for (row = 0; row < rowNum; ++row) {
					for (col = 0; col < colNum; ++col) {
						i = levelsDataReader.read();
						levelsData[level][row][col] = i - 48;

						i = levelsDataReader.read(); // read space
					}
					i = levelsDataReader.read(); // read char '\n'
				}
			}

			levelsDataReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("There is no file with name 'leveldata_test.txt'!");
		} catch (IOException e) {
			System.out.println("Exception readLevelFile():\n" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public Square getSquare(int col, int row) {
		return board[row][col];
	}

	public Square getSquare(Point2D place) {
		return board[(int) place.getY()][(int) place.getX()];
	}

	public void loadLevel(int levelNum) {
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				board[row][col] = new Square(col * squareSize, row * squareSize, squareSize,
						levelsData[levelNum][row][col]);
				root.getChildren().add(board[row][col].getRectengle());
			}
		}
	}

	public void moveSquares() {
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				Square square = getSquare(col, row);
				if (square.getMoveDirection() != Direction.NOMOVE)
					moveSquare(square);
			}
		}
	}

	private boolean moveSquare(Square square) {
		Direction moveDirection = square.getMoveDirection();
		if (moveDirection == Direction.NOMOVE)
			return true;

		Point2D aimPlace = new Point2D(square.getX() + moveDirection.getX(), square.getY() + moveDirection.getY());

		if (!isValidPlace(aimPlace) || getSquare(aimPlace).color != Colors.WHITE || !square.isMoveable())
			return false;

		getSquare(aimPlace).color = square.color;
		square.reset();

		return true;
	}

	private int countMoveableSquares() {
		int counter = 0;
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				if (board[row][col].isMoveable())
					++counter;
			}
		}
		return counter;
	}

	public boolean isValidPlace(Point2D place) {
		if ((int) place.getX() > colNum || (int) place.getY() > rowNum || (int) place.getX() < 0
				|| (int) place.getY() < 0)
			return false;
		return true;
	}
}
