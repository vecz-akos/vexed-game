package org.openjfx.vexed;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage stage) {
    	final int colNum = 10;
    	final int rowNum = 8;
    	final int squareSize = 50;
        final int levelsNum = 117;
    	
    	Vexed vexed = new Vexed(stage, colNum, rowNum, squareSize, levelsNum);
        vexed.start();
    }

}