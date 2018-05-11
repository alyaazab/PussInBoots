package sample.model.weapons;

import sample.model.items.Bombs.LivesBomb;
import sample.model.items.Gifts.ArmourGift;
import sample.model.items.Gifts.BulletGift;
import sample.model.items.Gifts.LivesGift;
import sample.model.items.Item;

public class Gun implements Weapon {
    @Override
    public boolean hit(Item item) {
        System.out.println(item.getClass().getSimpleName() + " gun hit");
        return (item instanceof ArmourGift || item instanceof BulletGift || item instanceof LivesGift
                || item instanceof LivesBomb);
    }
}
