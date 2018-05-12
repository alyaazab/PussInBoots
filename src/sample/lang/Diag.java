package sample.lang;

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
}
