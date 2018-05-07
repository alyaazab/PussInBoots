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
import sample.model.items.Item;
import sample.model.items.ItemFactory;
import sample.model.observer.InfoPanel;

import java.io.FileInputStream;

public class Controller {

    @FXML
    Pane pane;

    @FXML
    Label lblMoves;

    InfoPanel infoPanel = InfoPanel.getInstance();
    Runner runnerObject = Runner.getInstance();

    private FileClass fileClass = new FileClass();
    private char [][] gameMap = fileClass.getMaze();
    private int row=29, col=0;

    private ImageView runner;
    Item item;
    ItemFactory itemFactory = new ItemFactory();

    @FXML
    public void initialize()
    {
        try{
            Image image = null;

            for(int i=0; i<30; i++)
            {
                for(int j=0; j<30; j++)
                {
                    if(gameMap[i][j] == 'W')
                    {
                        image = new Image(new FileInputStream("res/photos/wall.png"));
                        System.out.printf("at [%d][%d], %c found\n", i, j, gameMap[i][j]);
                    }
                    else if(gameMap[i][j] == 'E')
                    {
                        image = new Image(new FileInputStream("res/photos/empty.png"));
                        System.out.printf("at [%d][%d], %c found\n", i, j, gameMap[i][j]);

                    }
                    else if(gameMap[i][j] == 'B' || gameMap[i][j] == 'D')
                    {
                        item = itemFactory.createItem(gameMap[i][j]);
                        image = item.getImage();
                    }

                    ImageView img = new ImageView(image);

                    img.setLayoutX(j*20);
                    img.setLayoutY(i*20);

                    pane.getChildren().add(img);

                }
            }

            image = new Image(new FileInputStream("res/photos/runner.png"));

            runner = new ImageView(image);

            runner.setLayoutX(0);
            runner.setLayoutY(29*20);

            pane.getChildren().add(runner);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.D){
            if(gameMap[row][col+1] == 'E'){
                System.out.println("You can move here");
                col++;
                runner.setLayoutX(runner.getLayoutX() + 20);

                runnerObject.setCoins(runnerObject.getCoins() + 1);
                lblMoves.setText(Integer.toString(infoPanel.getCoins()));
                System.out.println(infoPanel.getCoins());
            }
            else {
                System.out.println("You cannot move here");
            }
        }
        else if(keyEvent.getCode() == KeyCode.A){
            if(gameMap[row][col-1] == 'E'){
                System.out.println("You can move here");
                col--;
                runner.setLayoutX(runner.getLayoutX() - 20);

                runnerObject.setCoins(runnerObject.getCoins() + 1);
                lblMoves.setText(Integer.toString(infoPanel.getCoins()));
                System.out.println(infoPanel.getCoins());

            }
            else {
                System.out.println("You cannot move here");
            }
        }
        else if(keyEvent.getCode() == KeyCode.W){
            if(gameMap[row-1][col] == 'E'){
                System.out.println("You can move here");
                row--;
                runner.setLayoutY(runner.getLayoutY() - 20);

                runnerObject.setCoins(runnerObject.getCoins() + 1);
                lblMoves.setText(Integer.toString(infoPanel.getCoins()));
                System.out.println(infoPanel.getCoins());
            }
            else {
                System.out.println("You cannot move here");
            }
        }
        else if(keyEvent.getCode() == KeyCode.S){
            if(gameMap[row+1][col] == 'E'){
                System.out.println("You can move here");
                row++;
                runner.setLayoutY(runner.getLayoutY() + 20);

                runnerObject.setCoins(runnerObject.getCoins() + 1);
                lblMoves.setText(Integer.toString(infoPanel.getCoins()));
                System.out.println(infoPanel.getCoins());
            }
            else {
                System.out.println("You cannot move here");
            }
        }
    }

}