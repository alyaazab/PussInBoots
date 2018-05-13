package sample.controller.keypresses;

import sample.controller.Game;
import sample.controller.GameState;
import sample.model.Maze;
import sample.model.Runner;

public class KeyAPressed implements GameState {

    private Maze maze;
    private Game game;
    private Runner runner = Runner.getInstance();

    public KeyAPressed(Game game, Maze maze) {
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
    public void keySPressed() {
        game.setGameState(game.getKeySPressed());
        game.keySPressed();
        System.out.println("key S was pressed");
    }

    @Override
    public void keyDPressed() {
        game.setGameState(game.getKeyDPressed());
        game.keyDPressed();
        System.out.println("key D was pressed");
    }

    @Override
    public void keyAPressed() {
        game.setGameState(game.getKeyAPressed());
        System.out.println("Key A was pressed");

        char[][] tempMap = maze.getGameMap();
        int tempRow = maze.getRow();
        int tempCol = maze.getCol();

        if (tempMap[tempRow][tempCol -1] != 'E' && tempMap[tempRow][tempCol -1]!= 'W'
                && tempMap[tempRow][tempCol -1]!= 'C') {
            tempCol--;
            maze.setCol(tempCol);

            maze.setItem(maze.getItemFactory().createItem(tempMap[tempRow][tempCol]));
            tempMap[tempRow][tempCol] = 'E';
            maze.setGameMap(tempMap);

            maze.getRunner().setLayoutX(maze.getRunner().getLayoutX()-22);

            maze.updateHealth();
            if(runner.getHealth() == 0){
                maze.loadMemento();
                maze.getLblHealth().setText(runner.getHealth()+"");
            }
            maze.updateGame();
        } else if (tempMap[tempRow][tempCol -1] == 'E' || tempMap[tempRow][tempCol -1]== 'C') {
            System.out.println("You can move here");
            tempCol--;
            maze.setCol(tempCol);
            maze.getRunner().setLayoutX(maze.getRunner().getLayoutX() - 22);
            if(tempMap[tempRow][tempCol]== 'C'){
                maze.saveMemento();
            }
        }

        maze.updateMoves();
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