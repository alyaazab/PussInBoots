package sample.lang;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Diag {
    public static String showDiag(){
        TextInputDialog saveDiag = new TextInputDialog();
        saveDiag.setTitle("Save Game");
        saveDiag.setHeaderText("Type a Name");
        Optional<String> name = saveDiag.showAndWait();
        return name.orElse(null);
    }

    public static void showWINLOSEDiag(Alert.AlertType alertType, String title, String header, String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
