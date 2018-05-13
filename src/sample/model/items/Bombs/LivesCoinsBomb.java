package sample.model.items.Bombs;

import javafx.scene.image.Image;
import sample.model.Runner;
import sample.model.items.Gifts.ArmourGift;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LivesCoinsBomb extends Bomb{

    public LivesCoinsBomb(){
        try {
            img = new Image(new FileInputStream("res/photos/bomb1.png"));
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
        runner.setHealth(runner.getHealth()-10);
        runner.setCoins(runner.getCoins()-5);
        if(runner.getHealth() == 0){
            runner.setLives(runner.getLives()-1);
            runner.setHealth(50 + runner.getHealth());
        }
    }
}
