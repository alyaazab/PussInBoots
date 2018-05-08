package sample.model.weapons;

import sample.model.items.Item;

public class Hand implements Weapon {
    @Override
    public void hit(Item item) {
        System.out.println(item.getClass().getSimpleName() + " hand hit");
    }
}
