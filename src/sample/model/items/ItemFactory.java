package sample.model.items;

import sample.model.items.Bombs.LivesBomb;
import sample.model.items.Bombs.LivesCoinsBomb;
import sample.model.items.Gifts.BulletGift;
import sample.model.items.Gifts.LivesGift;
import sample.model.items.Gifts.ArmourGift;

import java.util.Random;

public class ItemFactory {

    private char x;

    public Item createItem(char item) {
        switch (item) {
            case 'B':
                x='B';
                return new LivesBomb();

            case 'G':
                x='G';
                return new LivesGift();

            case 'L':
                x='L';
                return new BulletGift();

            case 'D':
                x='D';
                return new LivesCoinsBomb();

            case 'A':
                x='A';
                return new ArmourGift();

            case 'E': case 'W':
                return null;

            default:
                return selectItem();
        }
    }

    private Item selectItem() {
        int randomNumber = new Random().nextInt(5) + 1;
        switch (randomNumber) {
            case 1:
                x='B';
                return new LivesBomb();

            case 2:
                x='D';
                return new LivesCoinsBomb();

            case 3:
                x='G';
                return new LivesGift();

            case 4:
                x='L';
                return new BulletGift();

            case 5:
                x='A';
                return new ArmourGift();
        }
        return null;
    }

    public char getX() {
        return x;
    }
}
