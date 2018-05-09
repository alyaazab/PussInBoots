package sample.model.items.Gifts;


import javafx.scene.image.Image;
import sample.model.Runner;
import sample.model.items.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LivesGift extends Gifts{

    public LivesGift(){
        try {
            img = new Image(new FileInputStream("res/photos/livesGift.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Image getImage() {
        return this.img;
    }

    @Override
    public void change(Runner runner) {
        runner.setLives(runner.getLives()+1);
    }
}
