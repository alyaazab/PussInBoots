
package sample.controller;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.files.FileClass;
import sample.model.Maze;
import sample.model.Runner;
import sample.model.weapons.Gun;
import sample.model.weapons.Hand;
import sample.model.weapons.Weapon;
import sample.model.items.Item;
import sample.model.items.ItemFactory;
import sample.model.observer.InfoPanel;

import java.io.FileInputStream;

public class Controller {

    @FXML
    Pane pane;

    @FXML
    Label lblMoves, lblHealth;

    private InfoPanel infoPanel = InfoPanel.getInstance();
    private Runner runnerObject = Runner.getInstance();
//    private Weapon weapon = null;


//    private ImageView runner;
//    private ImageView bullet;

    private char key = ' ';

    Maze maze = new Maze();


//    private void setUpGame(){
//        weapon = new Hand();
//        try {
//            Image image;
//            bullet = new ImageView(new Image(new FileInputStream("res/Photos/bullet.jpg")));
//            bullet.setFitWidth(10);
//            bullet.setFitHeight(10);
//            maze.setUpArray(pane);
//
//            image = new Image(new FileInputStream("res/Photos/runner.png"));
//
//            runner = new ImageView(image);
//            runner.setLayoutX(0);
//            runner.setLayoutY(29 * 20);
//
//            pane.getChildren().add(runner);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    public void initialize() {
        maze.setPane(pane);
        maze.setUpGame();
    }

    Game game = new Game(maze);

    public void onKeyPressed(KeyEvent keyEvent) {
        updateHealth();
        updateMoves();

        if (keyEvent.getCode() == KeyCode.D) {
            key = 'D';
            game.keyDPressed(maze);

        } else if (keyEvent.getCode() == KeyCode.W) {
            key = 'W';
            game.keyWPressed();
//            if (gameMap[row - 1][col] != 'E' && gameMap[row - 1][col] != 'W') {
//                row--;
//                item = itemFactory.createItem(gameMap[row][col]);
//                gameMap[row][col] = 'E';
//                runner.setLayoutY(runner.getLayoutY() - 20);
//                updateHealth();
//                updateMoves();
//
//                updateGame();
//            } else if (gameMap[row - 1][col] == 'E') {
//                System.out.println("You can move here");
//                row--;
//                runner.setLayoutY(runner.getLayoutY() - 20);
//
//                updateMoves();
//            }
//        } else if (keyEvent.getCode() == KeyCode.S) {
//            key = 'S';
//            if (gameMap[row + 1][col] != 'E' && gameMap[row + 1][col] != 'W') {
//                row++;
//                item = itemFactory.createItem(gameMap[row][col]);
//                gameMap[row][col] = 'E';
//                runner.setLayoutY(runner.getLayoutY() + 20);
//                updateHealth();
//                updateMoves();
//
//                updateGame();
//            } else if (gameMap[row + 1][col] == 'E') {
//                System.out.println("You can move here");
//                row++;
//                runner.setLayoutY(runner.getLayoutY() + 20);
//
//                updateMoves();
//            }
//        } else if (keyEvent.getCode() == KeyCode.NUMPAD1 || keyEvent.getCode() == KeyCode.DIGIT1) {
//            weapon = new Hand();
//            System.out.println("Hand selected");
//        } else if (keyEvent.getCode() == KeyCode.NUMPAD2 || keyEvent.getCode() == KeyCode.DIGIT2) {
//            weapon = new Gun();
//            System.out.println("Gun selected");
//        }
//        else if (keyEvent.getCode() == KeyCode.ENTER) {
//            if (key == 'D') {
//                if (weapon instanceof Hand) {
//                    item = itemFactory.createItem(gameMap[row][col + 1]);
//                    if (item != null){
//                        if(weapon.hit(item)) {
//                            gameMap[row][col + 1] = 'E';
//                            updateGame();
//                        }
//                    }
//                } else {
//                    bullet.setLayoutX(runner.getLayoutX());
//                    bullet.setLayoutY(runner.getLayoutY());
//                    pane.getChildren().add(bullet);
//                    runnerObject.setBullets(runnerObject.getBullets()-1);
//                    System.out.println("bullets = " + runnerObject.getBullets());
//
//                    for (int i = col + 1; i < 30; i++) {
//                        if (gameMap[row][i] != 'E' && gameMap[row][i] != 'W') {
//                            moveBullet(key, false);
//                            if(weapon.hit(itemFactory.createItem(gameMap[row][i]))){
//                                gameMap[row][i] = 'E';
//                                updateGame();
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
//            else if (key == 'A') {
//                    System.out.println("key=A");
//                    if (weapon instanceof Hand) {
//                        item = itemFactory.createItem(gameMap[row][col - 1]);
//                        if (item != null) {
//                            if(weapon.hit(item)) {
//                                gameMap[row][col - 1] = 'E';
//                                updateGame();
//                            }
//                        }
//                    } else {
//                        bullet.setLayoutX(runner.getLayoutX());
//                        bullet.setLayoutY(runner.getLayoutY());
//                        pane.getChildren().add(bullet);
//
//                        runnerObject.setBullets(runnerObject.getBullets()-1);
//
//                        for (int i = col - 1; i >= 0; i--) {
//                            if (gameMap[row][i] != 'E' && gameMap[row][i] != 'W') {
//                                moveBullet(key, false);
//                                if(weapon.hit(itemFactory.createItem(gameMap[row][i]))){
//                                    gameMap[row][i] = 'E';
//                                    updateGame();
//                                }
//                                break;
//                            }
//                        }
//                    }
//                } else if (key == 'W') {
//                    if (weapon instanceof Hand) {
//                        item = itemFactory.createItem(gameMap[row - 1][col]);
//                        if (item != null){
//                            if(weapon.hit(item)) {
//                                gameMap[row - 1][col] = 'E';
//                                updateGame();
//                            }
//                        }
//                    } else {
//                        bullet.setLayoutX(runner.getLayoutX());
//                        bullet.setLayoutY(runner.getLayoutY());
//                        pane.getChildren().add(bullet);
//
//                        runnerObject.setBullets(runnerObject.getBullets()-1);
//
//                        for (int i = row - 1; i >= 0; i--) {
//                            if (gameMap[i][col] != 'E' && gameMap[i][col] != 'W') {
//                                moveBullet(key, false);
//                                if(weapon.hit(itemFactory.createItem(gameMap[i][col]))){
//                                    gameMap[i][col] = 'E';
//                                    updateGame();
//                                }
//                                break;
//                            }
//                        }
//                    }
//                } else if (key == 'S') {
//                    if (weapon instanceof Hand) {
//                        item = itemFactory.createItem(gameMap[row + 1][col]);
//                        if (item != null) {
//                           if(weapon.hit(item)) {
//                                gameMap[row + 1][col] = 'E';
//                                updateGame();
//                            }
//                        }
//                    } else {
//                        bullet.setLayoutX(runner.getLayoutX());
//                        bullet.setLayoutY(runner.getLayoutY());
//                        pane.getChildren().add(bullet);
//
//                        runnerObject.setBullets(runnerObject.getBullets()-1);
//
//                        for (int i = row; i < 30; i++) {
//                            if (gameMap[i][col] != 'E' && gameMap[i][col] != 'W') {
//                                moveBullet(key, false);
//                                if(weapon.hit(itemFactory.createItem(gameMap[i][col]))){
//                                    gameMap[i][col] = 'E';
//                                    updateGame();
//                                }
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
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

    private void updateGame(){
        pane.getChildren().remove(maze.getRunner());
        maze.setUpArray();
        pane.getChildren().add(maze.getRunner());
    }

}
