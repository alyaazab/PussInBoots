package sample.model.items.Bombs;

import javafx.scene.image.Image;
import sample.model.Runner;
import sample.model.items.Gifts.ArmourGift;

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
    public Image getImage() {
        return this.img;
    }

    @Override
    public void change(Runner runner) {
        if(runner.getArmourValue() instanceof ArmourGift) {
            System.out.println("ANA LABES ARMOUR");
            return;
        }
        runner.setHealth(runner.getHealth()-1);
    }
}
