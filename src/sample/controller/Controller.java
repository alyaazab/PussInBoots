package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.files.FileClass;

import java.io.FileInputStream;

public class Controller {

    @FXML
    Pane pane;

    private FileClass fileClass = new FileClass();
    private char [][] gameMap = fileClass.getMaze();
    private int row=29, col=0;

    private ImageView runner;

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
            }
            else {
                System.out.println("You cannot move here");
            }
        }
    }

}