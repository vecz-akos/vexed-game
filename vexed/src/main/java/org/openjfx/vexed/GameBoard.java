package org.openjfx.vexed;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GameBoard {

	int[][][] levelsData;
	Square[][] board;
	int squareSize;
	final Direction gravityDirection;
	final int rowNum;
	final int colNum;
	final int levelsNum = 2;

	GameBoard(int colNum, int rowNum, int squareSize) {
		board = new Square[rowNum][colNum];
		this.rowNum = rowNum;
		this.colNum = colNum;
		this.squareSize = squareSize;
		gravityDirection = Direction.DOWN;

		levelsData = new int[levelsNum][rowNum][colNum];
		readLevelFile();
	}

	private void readLevelFile() {
		try {
			String pathToLevelsFile =
					new File("src\\main\\java\\org\\openjfx\\vexed\\leveldata_test.txt")
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

	public Square get(int row, int col) {
		return board[col][row];
	}

	public void loadLevel(int levelNum) {
		// TODO
		System.out.println("Loading level " + levelNum);
	}
}
