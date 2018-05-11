package sample.model.weapons;

import sample.model.items.Gifts.ArmourGift;
import sample.model.items.Gifts.BulletGift;
import sample.model.items.Gifts.LivesGift;
import sample.model.items.Item;

public class Hand implements Weapon {
    @Override
    public boolean hit(Item item) {
        System.out.println(item.getClass().getSimpleName() + " hand hit");
        return (item instanceof ArmourGift || item instanceof BulletGift || item instanceof LivesGift);
    }
}
