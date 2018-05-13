package sample.controller.keypresses;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import sample.controller.Game;
import sample.controller.GameState;
import sample.model.Maze;
import sample.model.Runner;
import sample.model.weapons.Hand;

public class EnterSKeyPressed implements GameState {
    private Maze maze;
    private Game game;
    private Runner runnerObject = Runner.getInstance();

    public static Circle c;


    public EnterSKeyPressed(Game game, Maze maze) {
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

        if (maze.getWeapon() instanceof Hand) {
            maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[maze.getRow() + 1][maze.getCol()]));
            if (maze.getItem() != null) {
                if (maze.getWeapon().hit(maze.getItem())) {
                    maze.getGameMap()[maze.getRow() + 1][maze.getCol()] = 'E';

                    maze.updateHealth();
                    maze.updateGame();
                }
            }
        }else if (Integer.parseInt(maze.getLblBullets().getText())!=0) {
            runnerObject.setBullets(runnerObject.getBullets() - 1);
            maze.getLblBullets().setText(runnerObject.getBullets()+"");
            System.out.println("bullets = " + runnerObject.getBullets());
            PathTransition transition = new PathTransition();
            Line line = new Line();

            line.setStartX(maze.getRunner().getLayoutX() + 11);
            line.setStartY(maze.getRunner().getLayoutY() + 22);

            line.setEndX(maze.getRunner().getLayoutX() + 11);
            line.setEndY(maze.getRunner().getLayoutY() + 22*5);

            c = new Circle();
            c.setRadius(3);
            c.setCenterX(maze.getRunner().getLayoutX() + 11);
            c.setCenterY(maze.getRunner().getLayoutY() + 22);
            c.setFill(Color.BLACK);
            maze.getPane().getChildren().add(c);

            transition.setNode(c);
            transition.setDuration(Duration.seconds(1));
            transition.setPath(line);
            transition.setCycleCount(1);
            transition.play();

            for (int i = maze.getRow() + 1; i < maze.getRow()+6; i++) {
                if (maze.getGameMap()[i][maze.getCol()] != 'E' && maze.getGameMap()[i][maze.getCol()] != 'W'
                        && maze.getGameMap()[i][maze.getCol()] != 'C') {
                    maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[i][maze.getCol()]));
                    if (maze.getWeapon().hit(maze.getItem())) {
                        maze.getGameMap()[i][maze.getCol()] = 'E';

                        maze.updateHealth();
                    }
                    break;
                }
                if(i==29)
                    break;

            }

        }

        game.setGameState(game.getKeyDPressed());
    }


    public Circle getC() {
        return c;
    }


    @Override
    public void keyEnterDPressed() {
        game.setGameState(game.getEnterDPressed());
        System.out.println("Enter D was pressed");
        game.keyEnterDPressed();
    }

    @Override
    public void keyAPressed() {
        game.setGameState(game.getKeyAPressed());
        game.keyAPressed();
        System.out.println("key A was pressed");
    }
}
