package sample.model.weapons;

import sample.model.items.Item;

public class Gun implements Weapon {
    @Override
    public void hit(Item item) {
        System.out.println(item.getClass().getSimpleName() + " gun hit");
    }
}
