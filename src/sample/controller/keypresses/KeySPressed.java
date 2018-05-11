package sample.controller.keypresses;

import sample.controller.Game;
import sample.controller.GameState;
import sample.model.Maze;

public class KeySPressed implements GameState {
    private Game game;
    private Maze maze;
    public KeySPressed(Game game, Maze maze) {
        this.game = game;
        this.maze = maze;
    }
    @Override
    public void keyWPressed() {
        game.setGameState(game.getKeyWPressed());
        game.keyWPressed();
        System.out.println("key W was pressed");

    }

    @Override
    public void keyAPressed() {
        game.setGameState(game.getKeyAPressed());
        game.keyAPressed();
        System.out.println("key A was pressed");

    }

    @Override
    public void keySPressed() {
        game.setGameState(game.getKeySPressed());
        System.out.println("Key S Was Pressed");
        char[][] tempMap = maze.getGameMap();
        int tempRow = maze.getRow();
        int tempCol = maze.getCol();

        if (tempMap[tempRow + 1][tempCol] != 'E' && tempMap[tempRow + 1][tempCol] != 'W') {
            tempRow++;
            maze.setRow(tempRow);
            maze.setItem(maze.getItemFactory().createItem(tempMap[tempRow][tempCol]));
            tempMap[tempRow][tempCol] = 'E';
            maze.setGameMap(tempMap);

            maze.getRunner().setLayoutY(maze.getRunner().getLayoutY()+20);

            maze.getPane().getChildren().remove(maze.getRunner());
            maze.setUpArray();
            maze.getPane().getChildren().add(maze.getRunner());

        } else if (tempMap[tempRow + 1][tempCol] == 'E') {
            System.out.println("You can move here");
            tempRow++;
            maze.setRow(tempRow);
            maze.getRunner().setLayoutY(maze.getRunner().getLayoutY() + 20);
        }
    }

    @Override
    public void keyDPressed() {
        game.setGameState(game.getKeyDPressed());
        game.keyDPressed();
        System.out.println("key D was pressed");
    }


    @Override
    public void keyEnterWPressed() {
        game.setGameState(game.getEnterWPressed());
        System.out.println("Enter W was pressed");
        game.keyEnterWPressed();
    }

    @Override
    public void keyEnterAPressed() {
        game.setGameState(game.getEnterAPressed());
        System.out.println("Enter A was pressed");
        game.keyEnterAPressed();
    }

    @Override
    public void keyEnterSPressed() {
        game.setGameState(game.getEnterSPressed());
        System.out.println("Enter S was pressed");
        game.keyEnterSPressed();
    }

    @Override
    public void keyEnterDPressed() {
        game.setGameState(game.getEnterDPressed());
        System.out.println("Enter D was pressed");
        game.keyEnterDPressed();
    }
}
