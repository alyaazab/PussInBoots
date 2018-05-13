package sample.controller.keypresses;

import sample.controller.Game;
import sample.controller.GameState;
import sample.model.Maze;
import sample.model.Runner;

public class KeySPressed implements GameState {
    private Game game;
    private Maze maze;
    private Runner runner = Runner.getInstance();

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

        if (tempMap[tempRow + 1][tempCol] != 'E' && tempMap[tempRow + 1][tempCol] != 'W'
                && tempMap[tempRow + 1][tempCol] != 'C') {
            tempRow++;
            maze.setRow(tempRow);
            maze.setItem(maze.getItemFactory().createItem(tempMap[tempRow][tempCol]));
            tempMap[tempRow][tempCol] = 'E';
            maze.setGameMap(tempMap);

            maze.getRunner().setLayoutY(maze.getRunner().getLayoutY()+22);

            maze.updateHealth();
            if(runner.getHealth() == 0){
                System.out.println("RETURN TO CHECK POINT");
                maze.loadMemento();
                maze.getLblHealth().setText(runner.getHealth()+"");
            }
            maze.updateGame();
        } else if (tempMap[tempRow + 1][tempCol] == 'E' || tempMap[tempRow + 1][tempCol] == 'C') {
            System.out.println("You can move here");
            tempRow++;
            maze.setRow(tempRow);
            maze.getRunner().setLayoutY(maze.getRunner().getLayoutY() + 22);
            if(tempMap[tempRow][tempCol] == 'C'){
                maze.saveMemento();
            }
        }
        maze.updateMoves();
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
