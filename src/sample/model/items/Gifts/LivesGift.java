package sample.model.items.Gifts;


import javafx.scene.image.Image;
import sample.model.Runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LivesGift extends Gift {

    public LivesGift(){
        try {
            img = new Image(new FileInputStream("res/photos/coin.png"));
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
        runner.setHealth(runner.getHealth()+1);
    }
}
