
package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.files.FileClass;
import sample.model.Runner;
import sample.model.Weapons.Gun;
import sample.model.Weapons.Hand;
import sample.model.Weapons.Weapon;
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
    private Weapon weapon = null;

    private FileClass fileClass = new FileClass();
    private char[][] gameMap = fileClass.getMaze();
    private int row = 29, col = 0;

    private ImageView runner;
    private Item item;
    private ItemFactory itemFactory = new ItemFactory();

    private char key = ' ';

    private void setUpArray(){
        try {
            Image image;

            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (gameMap[i][j] == 'W') {
                        image = new Image(new FileInputStream("res/Photos/wall.png"));
                        //System.out.printf("at [%d][%d], %c found\n", i, j, gameMap[i][j]);
                    } else if (gameMap[i][j] == 'E') {
                        image = new Image(new FileInputStream("res/Photos/empty.png"));
                        //System.out.printf("at [%d][%d], %c found\n", i, j, gameMap[i][j]);
                    } else {
                        item = itemFactory.createItem(gameMap[i][j]);
                        image = item.getImage();
                        gameMap[i][j] = itemFactory.getX();
                    }

                    ImageView img = new ImageView(image);

                    img.setLayoutX(j * 20);
                    img.setLayoutY(i * 20);

                    pane.getChildren().add(img);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpGame(){
        weapon = new Hand();
        try {
            Image image;

            setUpArray();

            image = new Image(new FileInputStream("res/Photos/runner.png"));

            runner = new ImageView(image);
            runner.setLayoutX(0);
            runner.setLayoutY(29 * 20);

            pane.getChildren().add(runner);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        setUpGame();
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            key = 'D';
            if (gameMap[row][col + 1] != 'E' && gameMap[row][col + 1] != 'W') {
                col++;
                item = itemFactory.createItem(gameMap[row][col]);
                gameMap[row][col] = 'E';
                runner.setLayoutX(runner.getLayoutX() + 20);
                updateLives();
                updateMoves();

                pane.getChildren().remove(runner);
                setUpArray();
                pane.getChildren().add(runner);
            } else if (gameMap[row][col + 1] == 'E') {
                System.out.println("You can move here");
                col++;
                runner.setLayoutX(runner.getLayoutX() + 20);
                updateMoves();
            }
        }

        else if (keyEvent.getCode() == KeyCode.A) {
            key='A';
            if (gameMap[row][col - 1] != 'E' && gameMap[row][col - 1] != 'W') {
                col--;
                item = itemFactory.createItem(gameMap[row][col]);
                gameMap[row][col]= 'E';
                runner.setLayoutX(runner.getLayoutX() - 20);
                updateLives();
                updateMoves();

                pane.getChildren().remove(runner);
                setUpArray();
                pane.getChildren().add(runner);
            } else if (gameMap[row][col - 1] == 'E') {
                System.out.println("You can move here");
                col--;
                runner.setLayoutX(runner.getLayoutX() - 20);

                updateMoves();
            }
        }

        else if (keyEvent.getCode() == KeyCode.W) {
            key='W';
            if (gameMap[row - 1][col] != 'E' && gameMap[row - 1][col] != 'W') {
                row--;
                item = itemFactory.createItem(gameMap[row][col]);
                gameMap[row][col] = 'E';
                runner.setLayoutY(runner.getLayoutY() - 20);
                updateLives();
                updateMoves();
                setUpArray();


                pane.getChildren().remove(runner);
                setUpArray();
                pane.getChildren().add(runner);
            } else if (gameMap[row - 1][col] == 'E') {
                System.out.println("You can move here");
                row--;
                runner.setLayoutY(runner.getLayoutY() - 20);

                updateMoves();
            }
        }

        else if (keyEvent.getCode() == KeyCode.S) {
            key='S';
            if (gameMap[row + 1][col] != 'E' && gameMap[row + 1][col] != 'W') {
                row++;
                item = itemFactory.createItem(gameMap[row][col]);
                gameMap[row][col] = 'E';
                runner.setLayoutY(runner.getLayoutY() + 20);
                updateLives();
                updateMoves();
                setUpArray();


                pane.getChildren().remove(runner);
                setUpArray();
                pane.getChildren().add(runner);
            } else if (gameMap[row + 1][col] == 'E') {
                System.out.println("You can move here");
                row++;
                runner.setLayoutY(runner.getLayoutY() + 20);

                updateMoves();
            }
        }

        else if(keyEvent.getCode() == KeyCode.NUMPAD1 || keyEvent.getCode() == KeyCode.DIGIT1){
            weapon = new Hand();
            System.out.println("Hand selected");
        }

        else if(keyEvent.getCode() == KeyCode.NUMPAD2 || keyEvent.getCode() == KeyCode.DIGIT2){
            weapon = new Gun();
            System.out.println("Gun selected");
        }

        else if(keyEvent.getCode() == KeyCode.ENTER){
            if(key=='D'){
                if(weapon instanceof Hand) {
                    item = itemFactory.createItem(gameMap[row][col + 1]);
                    if (item != null) weapon.hit(item);
                }
                else {
                    for (int i = col+1; i < 30; i++) {
                        if (gameMap[row][i] != 'E' && gameMap[row][i] != 'W') {
                            weapon.hit(itemFactory.createItem(gameMap[row][i]));
                            break;
                        }
                    }
                }
            }
            else if (key == 'A')
            {
                if(weapon instanceof Hand) {
                    item = itemFactory.createItem(gameMap[row][col - 1]);
                    if (item != null) weapon.hit(item);
                }
                else {
                    for(int i = col-1; i >= 0; i--){
                        if (gameMap[row][i] != 'E' && gameMap[row][i] != 'W') {
                            weapon.hit(itemFactory.createItem(gameMap[row][i]));
                            break;
                        }
                    }
                }
            }

            else if(key == 'W')
            {
                if(weapon instanceof Hand) {
                    item = itemFactory.createItem(gameMap[row-1][col]);
                    if (item != null) weapon.hit(item);
                }
                else {
                    for(int i = row-1; i >=0; i--){
                        if (gameMap[i][col] != 'E' && gameMap[i][col] != 'W') {
                            weapon.hit(itemFactory.createItem(gameMap[i][col]));
                            break;
                        }
                    }
                }
            }

            else if(key=='S'){
                if(weapon instanceof Hand) {
                    item = itemFactory.createItem(gameMap[row+1][col]);
                    if (item != null) weapon.hit(item);
                }
                else {
                    for(int i = row; i < 30; i++){
                        if (gameMap[i][col] != 'E' && gameMap[i][col] != 'W') {
                            weapon.hit(itemFactory.createItem(gameMap[i][col]));
                            break;
                        }
                    }
                }
            }
        }
    }

    private void updateMoves() {
        runnerObject.setMoves(runnerObject.getMoves() + 1);
        lblMoves.setText(Integer.toString(infoPanel.getMoves()));
        System.out.println(infoPanel.getMoves());
    }

    private void updateLives() {
        item.change(runnerObject);
        lblHealth.setText(Integer.toString(infoPanel.getLives()));
        System.out.println("FOUND A BOMB");
    }

}
