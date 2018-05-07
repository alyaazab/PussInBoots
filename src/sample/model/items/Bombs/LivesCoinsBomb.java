package sample.model.items.Bombs;

import javafx.scene.image.Image;
import sample.model.Runner;

public class LivesCoinsBomb extends Bomb{
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
        runner.setLives(runner.getLives()-2);
        runner.setCoins(runner.getCoins()-2);
    }
}
