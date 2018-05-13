package sample.model.items.Gifts;


import javafx.scene.image.Image;
import sample.model.Maze;
import sample.model.Runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BulletGift extends Gift {

    public BulletGift(){
        try {
            img = new Image(new FileInputStream("res/photos/bullets.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Image getImage() {
        return this.img;
    }


    public void change(Runner runner) {
        runner = Runner.getInstance();
        runner.setBullets(runner.getBullets()+2);

    }
}
