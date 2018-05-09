package sample.model.items.Gifts;


import javafx.scene.image.Image;
import sample.model.Runner;
import sample.model.items.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ArmourGift extends Gifts {

    public ArmourGift(){
        try {
            img = new Image(new FileInputStream("res/photos/armourGift.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Image getImage() {
        return this.img;
    }


    public void change(Runner runner) {
        System.out.println("FOUND AN ARMOUR");
    }
}
