package sample.model.items.Gifts;

import javafx.scene.image.Image;
import sample.model.Runner;
import sample.model.RunnerInterface;
import sample.model.items.Item;


abstract class Gift implements Item, RunnerInterface {
    Image img;

    private RunnerInterface runner = Runner.getInstance();

    @Override
    public void setArmourValue()
    {
        runner.setArmourValue();
    }

    @Override
    public boolean getArmourValue() {
        return runner.getArmourValue();
    }
}
