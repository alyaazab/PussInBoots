
package sample.controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import sample.Main;
import sample.controller.keypresses.*;
import sample.files.FileClass;
import sample.lang.Diag;
import sample.model.Maze;
import sample.model.weapons.Gun;
import sample.model.weapons.Hand;
import sample.scenes.Navigation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {

    @FXML
    Pane pane, paneMenu, gamePane;

    @FXML
    Label lblLives, lblMoves, lblHealth, lblBullets, lblTimer, lblHealthMenu, lblMovesMenu, lblTimerMenu, lblBulletsMenu;

    @FXML
    Button btnSave;

    @FXML
    ListView<String> lv;

    private Maze maze = new Maze();
    private Game game = new Game(maze);
    private ArrayList<String> savedGames;
    private boolean paused = false;
    private String timeString = " ";
    private Boolean load = false;

    private Image runnerUpIdle, runnerDownIdle, runnerRightIdle, runnerLeftIdle,
            runnerUpGif, runnerDownGif, runnerLeftGif, runnerRightGif;

    ImageView rUpIdle, rDownIdle, rRightIdle, rLeftIdle,
            rUpGif, rDownGif, rLeftGif, rRightGif;

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
                if(load){
                    time = Long.parseLong(lblTimer.getText().trim());
                }
                time += deltaT;
                timestamp += 1000 * deltaT;
                System.out.println("time: " + time);
                lblTimer.setText(Long.toString(time));
                timeString = Long.toString(time);
            }
        }
    };


    @FXML
    public void initialize() {
        pane.setVisible(true);
        paneMenu.setVisible(false);
        gamePane.setVisible(true);
        savedGames = new ArrayList<>();
        savedGames.addAll(Main.savedGames);
        ObservableList<String> ol = FXCollections.observableArrayList(savedGames);
        lv.setItems(ol);

        try {
            runnerUpIdle = new Image(new FileInputStream("res/Photos/runnerIdle.png"));
            runnerDownIdle = new Image(new FileInputStream("res/Photos/runnerDownIdle.png"));
            runnerRightIdle = new Image(new FileInputStream("res/Photos/runnerRightIdle.png"));
            runnerLeftIdle = new Image(new FileInputStream("res/Photos/runnerLeftIdle.png"));
            runnerUpGif = new Image(new FileInputStream("res/Photos/runnerUpGif.gif"));
            runnerDownGif = new Image(new FileInputStream("res/Photos/runnerDownGif.gif"));
            runnerLeftGif = new Image(new FileInputStream("res/Photos/runnerLeftGif.gif"));
            runnerRightGif = new Image(new FileInputStream("res/Photos/runnerRightGif.gif"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        maze.setPane(pane);
        maze.setLblHealth(lblHealth);
        maze.setLblMoves(lblMoves);
        maze.setLblTimer(lblTimer);
        maze.setLblLives(lblLives);
        maze.setLblBullets(lblBullets);
        maze.setUpGame();
        timer.start();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if(maze.getPane().getChildren().contains(EnterDKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterDKeyPressed.c);
            maze.updateGame();
        }else if(maze.getPane().getChildren().contains(EnterAKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterAKeyPressed.c);
            maze.updateGame();
        }else if(maze.getPane().getChildren().contains(EnterSKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterSKeyPressed.c);
            maze.updateGame();
        }else if(maze.getPane().getChildren().contains(EnterWKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterWKeyPressed.c);
            maze.updateGame();
        }

        if(paused) return;

        if (keyEvent.getCode() == KeyCode.D) {
            maze.getRunner().setImage(runnerRightGif);
            game.keyDPressed();
        } else if (keyEvent.getCode() == KeyCode.A) {
            maze.getRunner().setImage(runnerLeftGif);
            game.keyAPressed();
        } else if (keyEvent.getCode() == KeyCode.W) {
            maze.getRunner().setImage(runnerUpGif);
            game.keyWPressed();
        } else if (keyEvent.getCode() == KeyCode.S) {
            maze.getRunner().setImage(runnerDownGif);
            game.keySPressed();
        } else if (keyEvent.getCode() == KeyCode.NUMPAD1 || keyEvent.getCode() == KeyCode.DIGIT1) {
            maze.setWeapon(new Hand());
            System.out.println("Hand selected");
        } else if (keyEvent.getCode() == KeyCode.NUMPAD2 || keyEvent.getCode() == KeyCode.DIGIT2) {
            maze.setWeapon(new Gun());
            System.out.println("Gun selected");
        } else if (keyEvent.getCode() == KeyCode.G) {
            System.out.println("FUCKING ENTERRRRRRRRRR");
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

    public void onKeyReleased(KeyEvent keyEvent) {
        if (lblLives.getText().equals("0")) {
            Diag.showWINLOSEDiag(Alert.AlertType.INFORMATION, "WASTED",
                    "You Died :(", "Good luck next time :(");
            System.exit(0);
        }else if(maze.getCol() == 29){
            Diag.showWINLOSEDiag(Alert.AlertType.INFORMATION, "WINNER",
                    "You win", "Good luck next time bardo");
            System.exit(0);
        }
        if(game.getGameState() instanceof KeyDPressed)
            maze.getRunner().setImage(runnerRightIdle);
        else if(game.getGameState() instanceof KeyAPressed)
            maze.getRunner().setImage(runnerLeftIdle);
        else if(game.getGameState() instanceof KeyWPressed)
            maze.getRunner().setImage(runnerUpIdle);
        else if(game.getGameState() instanceof KeySPressed)
            maze.getRunner().setImage(runnerDownIdle);
    }

        public void onBtnSaveClick(ActionEvent actionEvent) {
        FileClass fileClass = new FileClass();
        String name = Diag.showDiag();
        if (name != null) {
            fileClass.saveGame(maze.getGameMap(), maze.getInfoPanel(),
                    maze.getRow(), maze.getCol(),timeString,name);
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
        load = true;
    }

    public void onBtnPauseClick(ActionEvent actionEvent) throws Exception {
        paused ^= true;

        if(paused) {
            timer.stop();
        }
        else
            timer.start();

        gamePane.setVisible(false);
        paneMenu.setVisible(true);

        for (int i = 0; i < Maze.imageViews.size(); i++){
            pane.getChildren().remove(Maze.imageViews.get(i));
        }
        lblHealthMenu.setText(lblHealth.getText());
        lblMovesMenu.setText(lblMoves.getText());
        lblTimerMenu.setText(lblTimer.getText());
        lblBulletsMenu.setText(lblBullets.getText());
    }

    public void onBtnResumeClick(ActionEvent actionEvent) {
        paneMenu.setVisible(false);
        gamePane.setVisible(true);
        maze.updateGame();

        paused ^= true;

        if(paused) {
            timer.stop();
        }
        else
            timer.start();
    }

    public void onMouseMoved(MouseEvent mouseEvent) {
        if(maze.getPane().getChildren().contains(EnterDKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterDKeyPressed.c);
            maze.updateGame();
        }else if(maze.getPane().getChildren().contains(EnterAKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterAKeyPressed.c);
            maze.updateGame();
        }else if(maze.getPane().getChildren().contains(EnterSKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterSKeyPressed.c);
            maze.updateGame();
        }else if(maze.getPane().getChildren().contains(EnterWKeyPressed.c)) {
            maze.getPane().getChildren().remove(EnterWKeyPressed.c);
            maze.updateGame();
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
}
