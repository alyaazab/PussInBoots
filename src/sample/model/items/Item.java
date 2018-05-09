package sample.model.items;

import javafx.scene.image.Image;
import sample.model.Runner;

public interface Item {
    Image getImage();
    void change(Runner runner);
}
