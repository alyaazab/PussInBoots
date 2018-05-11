
package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.controller.keypresses.KeyAPressed;
import sample.controller.keypresses.KeyDPressed;
import sample.controller.keypresses.KeySPressed;
import sample.controller.keypresses.KeyWPressed;
import sample.model.Maze;
import sample.model.Runner;
import sample.model.observer.InfoPanel;
import sample.model.weapons.Gun;
import sample.model.weapons.Hand;

public class Controller {

    @FXML
    Pane pane;

    @FXML
    Label lblMoves, lblHealth;

    private InfoPanel infoPanel = InfoPanel.getInstance();
    private Runner runnerObject = Runner.getInstance();
    private Maze maze = new Maze();

    @FXML
    public void initialize() {
        maze.setPane(pane);
        maze.setUpGame();
    }

    private Game game = new Game(maze);

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            game.keyDPressed();
        } else if (keyEvent.getCode() == KeyCode.A) {
            game.keyAPressed();
        } else if (keyEvent.getCode() == KeyCode.W) {
            game.keyWPressed();
        } else if (keyEvent.getCode() == KeyCode.S) {
            game.keySPressed();
        } else if (keyEvent.getCode() == KeyCode.NUMPAD1 || keyEvent.getCode() == KeyCode.DIGIT1) {
            maze.setWeapon(new Hand());
            System.out.println("Hand selected");
        } else if (keyEvent.getCode() == KeyCode.NUMPAD2 || keyEvent.getCode() == KeyCode.DIGIT2) {
            maze.setWeapon(new Gun());
            System.out.println("Gun selected");
        } else if (keyEvent.getCode() == KeyCode.ENTER) {
            if (game.getGameState() instanceof KeyDPressed) {
                game.keyEnterDPressed();
            } else if (game.getGameState() instanceof KeyAPressed) {
                game.keyEnterAPressed();
            } else if (game.getGameState() instanceof KeySPressed) {
                game.keyEnterSPressed();
            } else if (game.getGameState() instanceof KeyWPressed) {
                game.keyEnterWPressed();
            }
        }
    }

//    private void moveBullet(char key, boolean stop) {
//        AnimationTimer animationTimer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                if(key=='A') {
//                    maze.getBullet().setLayoutX(bullet.getLayoutX() - 1);
//                } else if(key=='D')
//                    bullet.setLayoutX(bullet.getLayoutX() + 1);
//                else if(key=='W')
//                    bullet.setLayoutY(bullet.getLayoutY()-1);
//                else
//                    bullet.setLayoutY(bullet.getLayoutY()+1);
//            }
//        };
//        animationTimer.start();
//        Task<Void> sleeper = new Task<Void>() {
//            @Override
//            protected Void call() {
//                try {
//                    Thread.sleep(500);
//                    System.out.println("SLEEEP");
//                } catch (InterruptedException ignored) {
//                }
//                return null;
//            }
//        };
//        sleeper.setOnSucceeded(event -> pane.getChildren().remove(bullet));
//
//        new Thread(sleeper).start();
//    }

    private void updateMoves() {
        runnerObject.setMoves(runnerObject.getMoves() + 1);
        lblMoves.setText(Integer.toString(infoPanel.getMoves()));
        System.out.println(infoPanel.getMoves());
    }

    private void updateHealth() {
        maze.getItem().change(runnerObject);
        lblHealth.setText(Integer.toString(infoPanel.getHealth()));
        System.out.println("updating health");
    }

    private void updateGame() {
        pane.getChildren().remove(maze.getRunner());
        maze.setUpArray();
        pane.getChildren().add(maze.getRunner());
    }

}
