package sample.controller.keypresses;

import sample.controller.Game;
import sample.controller.GameState;
import sample.model.Maze;
import sample.model.Runner;
import sample.model.weapons.Hand;

public class EnterWKeyPressed implements GameState {
    private Maze maze;
    private Game game;
    private Runner runnerObject = Runner.getInstance();

    public EnterWKeyPressed(Game game, Maze maze) {
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
        game.keyAPressed();
        System.out.println("key A was pressed");
    }

    @Override
    public void keyEnterWPressed() {
        game.setGameState(game.getEnterWPressed());
        System.out.println("Enter W was pressed");

        if (maze.getWeapon() instanceof Hand) {
            maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[maze.getRow() - 1][maze.getCol()]));
            if (maze.getItem() != null) {
                if (maze.getWeapon().hit(maze.getItem())) {
                    maze.getGameMap()[maze.getRow() - 1][maze.getCol()] = 'E';

                    maze.updateHealth();
                    maze.updateGame();
                }
            }
        } else {
            maze.getBullet().setLayoutX(maze.getRunner().getLayoutX());
            maze.getBullet().setLayoutY(maze.getRunner().getLayoutY());
            maze.getPane().getChildren().add(maze.getBullet());
            runnerObject.setBullets(runnerObject.getBullets() - 1);
            System.out.println("bullets = " + runnerObject.getBullets());

            for (int i = maze.getRow() - 1; i >= 0; i--) {
                if (maze.getGameMap()[i][maze.getCol()] != 'E' && maze.getGameMap()[i][maze.getCol()] != 'W') {
                    maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[i][maze.getCol()]));
                    if (maze.getWeapon().hit(maze.getItem())) {
                        maze.getGameMap()[i][maze.getCol()] = 'E';

                        maze.updateHealth();
                        maze.updateGame();
                    }
                    break;
                }
            }
        }
        game.setGameState(game.getKeyWPressed());
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
