package sample.model.items.Bombs;

import javafx.scene.image.Image;
import sample.model.Runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LivesBomb extends Bomb {

    public LivesBomb(){
        try {
            img = new Image(new FileInputStream("res/photos/bombB.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setImage(Image img) {
        this.img = img;
    }

    @Override
    public Image getImage() {
        return this.img;
    }

    @Override
    public void change(Runner runner) {
        runner.setLives(runner.getLives()-1);
    }
}
