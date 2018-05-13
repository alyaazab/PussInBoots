package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.files.FileClass;

import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<String> savedGames = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Maze Runner");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            FileClass fileClass = new FileClass();
            fileClass.writeListOfSavedGames(savedGames);
            System.out.println("saved");
        });
    }


    public static void main(String[] args) {
        FileClass.readListOfSavedGames();
        launch(args);
    }
}
