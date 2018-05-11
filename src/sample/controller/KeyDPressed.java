package sample.controller;

import javafx.scene.layout.Pane;
import sample.model.Maze;

public class KeyDPressed implements GameState {

    private Maze maze;
    private Game game;
    private char [][] tempMap;
    private int tempRow;
    private int tempCol;

    public KeyDPressed(Game game, Maze maze) {
        this.game = game;
        this.maze = maze;
    }


    @Override
    public void keyWPressed() {
        game.setGameState(game.getKeyWPressed());
        System.out.println("key W was pressed");

    }

    @Override
    public void keyAPressed() {
        //game.setGameState(game.getKeyAPressed());
        System.out.println("key W was pressed");
    }

    @Override
    public void keySPressed() {

    }

    @Override
    public void keyDPressed() {
        game.setGameState(game.getKeyDPressed());
        System.out.println("Key D was pressed");

        tempMap = maze.getGameMap();
        tempRow = maze.getRow();
        tempCol = maze.getCol();

        if (tempMap[tempRow][tempCol] != 'E' && tempMap[tempRow][tempCol]!= 'W') {
            tempCol++;
            maze.setCol(tempCol);

            maze.setItem(maze.getItemFactory().createItem(tempMap[tempRow][tempCol]));
            tempMap[tempRow][tempCol] = 'E';
            maze.setGameMap(tempMap);

            maze.getRunner().setLayoutX(maze.getRunner().getLayoutX()+20);

            maze.getPane().getChildren().remove(maze.getRunner());
            maze.setUpArray();
            maze.getPane().getChildren().add(maze.getRunner());

            } else if (tempMap[tempRow][tempCol + 1] == 'E') {
                System.out.println("You can move here");
                tempCol++;
                maze.setCol(tempCol);
                maze.getRunner().setLayoutX(maze.getRunner().getLayoutX() + 20);
            }
    }
}
