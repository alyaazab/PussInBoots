package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class Controller {

    @FXML
    Pane pane;
    char [][] gameMap = {{'W', 'W', 'E', 'E'}, {'W', 'W', 'E', 'W'}, {'E', 'E', 'E', 'W'}};
    int row=2, col=0;

    ImageView img, runner;

    @FXML
    public void initialize()
    {
        try{
            Image image = null;

            for(int i=0; i<3; i++)
            {
                for(int j=0; j<4; j++)
                {
                    if(gameMap[i][j] == 'W')
                    {
                        image = new Image(new FileInputStream("res/wall.png"));
                        System.out.printf("at [%d][%d], %c found\n", i, j, gameMap[i][j]);
                    }
                    else if(gameMap[i][j] == 'E')
                    {
                        image = new Image(new FileInputStream("res/empty.png"));
                        System.out.printf("at [%d][%d], %c found\n", i, j, gameMap[i][j]);

                    }

                    img = new ImageView(image);

                    img.setLayoutX(j*24);
                    img.setLayoutY(i*24);

                    pane.getChildren().add(img);

                }
            }

            image = new Image(new FileInputStream("res/runner.png"));

            runner = new ImageView(image);

            runner.setLayoutX(0);
            runner.setLayoutY(48);

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
                runner.setLayoutX(runner.getLayoutX() + 24);

            }
            else {
                System.out.println("You cannot move here");
            }
        }
        else if(keyEvent.getCode() == KeyCode.A){
            if(gameMap[row][col-1] == 'E'){
                System.out.println("You can move here");
                col--;
                runner.setLayoutX(runner.getLayoutX() - 24);

            }
            else {
                System.out.println("You cannot move here");
            }
        }
        else if(keyEvent.getCode() == KeyCode.W){
            if(gameMap[row-1][col] == 'E'){
                System.out.println("You can move here");
                row--;
                runner.setLayoutY(runner.getLayoutY() - 24);

            }
            else {
                System.out.println("You cannot move here");
            }
        }
        else if(keyEvent.getCode() == KeyCode.S){
            if(gameMap[row+1][col] == 'E'){
                System.out.println("You can move here");
                row++;
                runner.setLayoutY(runner.getLayoutY() + 24);
            }
            else {
                System.out.println("You cannot move here");
            }
        }
    }

}