
package sample.controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import sample.Main;
import sample.controller.keypresses.KeyAPressed;
import sample.controller.keypresses.KeyDPressed;
import sample.controller.keypresses.KeySPressed;
import sample.controller.keypresses.KeyWPressed;
import sample.files.FileClass;
import sample.lang.Diag;
import sample.model.Maze;
import sample.model.weapons.Gun;
import sample.model.weapons.Hand;

import java.util.ArrayList;

public class Controller {

    @FXML
    Pane pane;

    @FXML
    Label lblMoves, lblHealth, lblTimer;

    @FXML
    Button btnSave;

    @FXML
    ListView<String> lv;

    private Maze maze = new Maze();
    private Game game = new Game(maze);
    private ArrayList<String> savedGames;

    private boolean paused = false;

    private AnimationTimer timer = new AnimationTimer() {
        private long timestamp;
        private long time = 0;
        private long fraction = 0;

        @Override
        public void start() {
            //current time adjusted by remaining time from last run
            timestamp = System.currentTimeMillis() - fraction;
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            //save leftover time not handled with the last update
            fraction = System.currentTimeMillis() - timestamp;
        }

        @Override
        public void handle(long now) {
            long newTime = System.currentTimeMillis();
            if (timestamp + 1000 <= newTime) {
                long deltaT = (newTime - timestamp) / 1000;
                time += deltaT;
                timestamp += 1000 * deltaT;
                System.out.println("time: " + time);
                lblTimer.setText(Long.toString(time));
            }
        }
    };

    @FXML
    public void initialize() {
        savedGames = new ArrayList<>();
        savedGames.addAll(Main.savedGames);
        ObservableList<String> ol = FXCollections.observableArrayList(savedGames);
        lv.setItems(ol);
        maze.setPane(pane);
        maze.setLblHealth(lblHealth);
        maze.setLblMoves(lblMoves);
        maze.setUpGame();
        timer.start();
    }

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
        } else if (keyEvent.getCode() == KeyCode.P) {
            paused ^= true;

            if(paused)
                timer.stop();
            else
                timer.start();
        }
    }

    public void onBtnSaveClick(ActionEvent actionEvent) {
        FileClass fileClass = new FileClass();
        String name = Diag.showDiag();
        if (name != null) {
            fileClass.saveGame(maze.getGameMap(), maze.getInfoPanel(),
                    maze.getRow(), maze.getCol(), name);
            savedGames.add(name);
            Main.savedGames.clear();
            Main.savedGames.addAll(savedGames);
            ObservableList<String> ol = FXCollections.observableArrayList(savedGames);
            lv.setItems(ol);
        }
    }

    public void onListClicked(MouseEvent event) {
        String selectedItem = lv.getSelectionModel().getSelectedItem();
        FileClass fileClass = new FileClass();
        fileClass.loadGame(selectedItem, maze);
        maze.getLblMoves().setText(Integer.toString(maze.getInfoPanel().getMoves()));
        maze.getLblHealth().setText(Integer.toString(maze.getInfoPanel().getHealth()));
        maze.updateGame();
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
}
