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

	public void update() {
		moveSquares();
	}

	public Square getSquare(int col, int row) {
		return board[row][col];
	}

	public Square getSquare(int col, int row, Direction dir) {
		return board[row + dir.getY()][col + dir.getX()];
	}

	public Square getSquare(Point2D place) {
		return board[(int) place.getY()][(int) place.getX()];
	}

	public Square getSquare(Point2D place, Direction dir) {
		return board[(int) place.getY() + dir.getY()][(int) place.getX() + dir.getX()];
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
				moveSingleSquare(square);
			}
		}

		if (isGravityNeed())
			gravity();
		freeSquares();
	}

	// return true if something changed, false otherwise
	private boolean moveSingleSquare(Square square) {
		Direction moveDirection = square.getMoveDirection();
		if (moveDirection == Direction.NOMOVE)
			return false;

		Point2D aimPlace = new Point2D(square.getCol() + moveDirection.getX(), square.getRow() + moveDirection.getY());
		if (!isValidPlace(aimPlace) || !square.isMoveable()) {
			square.setDirection(Direction.NOMOVE);
			return false;
		}

		Square aimSquare = getSquare(aimPlace);
		if (aimSquare.color != Colors.WHITE) {
			square.setDirection(Direction.NOMOVE);
			return false;
		}

		aimSquare.setColor(square.color);
		square.reset();

		return true;
	}

	private boolean isGravityNeed() {
		final int beginX = gravityDirection.getX() > 0 ? colNum - 1 : 1;
		final int beginY = gravityDirection.getY() > 0 ? rowNum - 1 : 1;
		final int endX = beginX > 1 ? 0 : colNum - 1;
		final int endY = beginY > 1 ? 0 : rowNum - 1;
		final int deltaX = beginX > 1 ? -1 : 1;
		final int deltaY = beginY > 1 ? -1 : 1;
		
		for (int col = beginX; col != endX; col += deltaX) {
			for (int row = beginY; row != endY; row += deltaY) {
				Square square = getSquare(col, row);
				if (isSquareCanFall(square)) {
					return true;
				}
			}
		}
		return false;
	}

	private void gravity() {
		final int beginX = gravityDirection.getX() > 0 ? colNum - 1 : 1;
		final int beginY = gravityDirection.getY() > 0 ? rowNum - 1 : 1;
		final int endX = beginX > 1 ? 0 : colNum - 1;
		final int endY = beginY > 1 ? 0 : rowNum - 1;
		final int deltaX = beginX > 1 ? -1 : 1;
		final int deltaY = beginY > 1 ? -1 : 1;
		boolean change = false;
		
		for (int col = beginX; col != endX; col += deltaX) {
			for (int row = beginY; row != endY; row += deltaY) {
				Square square = getSquare(col, row);
				if (isSquareCanFall(square)) {
					square.setDirection(gravityDirection);
					change = true;
				}
			}
		}

		if (change)
			moveSquares();
	}

	private boolean isSquareCanFall(Square square) {
		if (!square.isMoveable())
			return false;

		Point2D belowPlace = new Point2D(square.getCol() + gravityDirection.getX(),
				square.getRow() + gravityDirection.getY());
		if (!isValidPlace(belowPlace))
			return false;

		Square belowSquare = getSquare(belowPlace);
		if (belowSquare.color == Colors.WHITE || belowSquare.getMoveDirection() == gravityDirection)
			return true;
		return false;
	}

	private void freeSquares() {
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				Square square = getSquare(col, row);
				if (square.isMoveable() && !square.waitToDelete)
					checkNeigboursColor(square);
			}
		}

		deleteSquares();
	}

	private void checkNeigboursColor(Square square) {
		deleteMeAndMyNeighbourIfOurColorIsSame(square, Direction.UP);
		deleteMeAndMyNeighbourIfOurColorIsSame(square, Direction.RIGHT);
		deleteMeAndMyNeighbourIfOurColorIsSame(square, Direction.DOWN);
		deleteMeAndMyNeighbourIfOurColorIsSame(square, Direction.LEFT);
	}

	private void deleteMeAndMyNeighbourIfOurColorIsSame(Square me, Direction dir) {
		Point2D neighbourPlace = new Point2D(me.getCol() + dir.getX(), me.getRow() + dir.getY());
		if (!isValidPlace(neighbourPlace))
			return;
		Square neighbour = getSquare(neighbourPlace);
		if (me.color.equals(neighbour.color)) {
			me.waitToDelete = true;
		}
	}

	private void deleteSquares() {
		boolean change = false;
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum; col++) {
				Square square = getSquare(col, row);
				if (square.waitToDelete) {
					deleteSquare(square);
					change = true;
				}
			}
		}
		if (isGravityNeed())
			gravity();
	}

	private void deleteSquare(Square square) {
		square.reset();
	}

	public boolean isValidPlace(Point2D place) {
		if ((int) place.getX() >= colNum || (int) place.getY() >= rowNum || (int) place.getX() < 0
				|| (int) place.getY() < 0)
			return false;
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
}
