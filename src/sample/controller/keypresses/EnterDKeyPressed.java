package sample.controller.keypresses;

import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import sample.controller.Game;
import sample.controller.GameState;
import sample.model.Maze;
import sample.model.Runner;
import sample.model.weapons.Hand;


public class EnterDKeyPressed implements GameState {
    private Maze maze;
    private Game game;
    private Runner runnerObject = Runner.getInstance();
    public static Circle c;

    public EnterDKeyPressed(Game game, Maze maze) {
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
        System.out.println("key A was pressed");
        game.keyAPressed();
    }

    @Override
    public void keyEnterAPressed() {
        game.setGameState(game.getEnterAPressed());
        System.out.println("Enter W Pressed");
        game.keyEnterAPressed();
    }

    @Override
    public void keyEnterWPressed() {
        game.setGameState(game.getEnterWPressed());
        System.out.println("Enter W Pressed");
        game.keyEnterWPressed();
    }

    @Override
    public void keyEnterSPressed() {
        game.setGameState(game.getEnterSPressed());
        System.out.println("Enter S Pressed");
        game.keyEnterSPressed();
    }

    @Override
    public void keyEnterDPressed() {
        game.setGameState(game.getEnterDPressed());
        System.out.println("Enter D Pressed");

        if (maze.getWeapon() instanceof Hand) {
            maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[maze.getRow()][maze.getCol() + 1]));
            if (maze.getItem() != null) {
                if (maze.getWeapon().hit(maze.getItem())) {
                    maze.getGameMap()[maze.getRow()][maze.getCol() + 1] = 'E';

                    maze.updateHealth();
                    maze.updateGame();
                }
            }
        } else if (Integer.parseInt(maze.getLblBullets().getText())!=0) {
            runnerObject.setBullets(runnerObject.getBullets() - 1);
            maze.getLblBullets().setText(runnerObject.getBullets()+"");
            System.out.println("bullets = " + runnerObject.getBullets());
                PathTransition transition = new PathTransition();
                Line line = new Line();

                line.setStartX(maze.getRunner().getLayoutX() + 22);
                line.setStartY(maze.getRunner().getLayoutY() + 11);

                line.setEndX(maze.getRunner().getLayoutX() + 22 * 5);
                line.setEndY(maze.getRunner().getLayoutY() + 11);

                c = new Circle();
                c.setRadius(3);
                c.setCenterX(maze.getRunner().getLayoutX() + 22);
                c.setCenterY(maze.getRunner().getLayoutY() + 11);
                c.setFill(Color.BLACK);
                maze.getPane().getChildren().add(c);

                transition.setNode(c);
                transition.setDuration(Duration.seconds(1));
                transition.setPath(line);
                transition.setCycleCount(1);
                transition.play();

                for (int i = maze.getCol() + 1; i < maze.getCol() + 6; i++) {
                    if (maze.getGameMap()[maze.getRow()][i] != 'E' && maze.getGameMap()[maze.getRow()][i] != 'W'
                            && maze.getGameMap()[maze.getRow()][i] != 'C') {
                        maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[maze.getRow()][i]));
                        if (maze.getWeapon().hit(maze.getItem())) {
                            maze.getGameMap()[maze.getRow()][i] = 'E';

                            maze.updateHealth();
                            //maze.updateGame();
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

}
