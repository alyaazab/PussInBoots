package sample.model.items;

import javafx.scene.image.Image;
import sample.model.Runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tree implements Item {

    private Image img;

    public Tree(){
        try {
            img = new Image(new FileInputStream("res/photos/tree.png"));
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
        runner.setScore(runner.getScore()+5);
    }
}