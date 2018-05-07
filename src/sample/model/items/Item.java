package sample.model.items;

import javafx.scene.image.Image;
import sample.model.Runner;

public interface Item {
    void setImage(Image img);
    Image getImage();
    void change(Runner runner);
}
