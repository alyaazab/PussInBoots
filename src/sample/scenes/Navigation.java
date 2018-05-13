package sample.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {

    public void navigate(ActionEvent actionEvent, String fxml, String context)throws Exception{
        Class cl = Class.forName(context);
        Object obj = cl.newInstance();
        Parent root = FXMLLoader.load(obj.getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
