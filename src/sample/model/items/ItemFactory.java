package sample.model.items;

import sample.model.items.Bombs.LivesBomb;
import sample.model.items.Bombs.LivesCoinsBomb;

import java.util.Random;

public class ItemFactory {

    private char x;

    public Item createItem(char item) {
        switch (item) {
            case 'B':
                x='B';
                return new LivesBomb();
            case 'D':
                x='D';
                return new LivesCoinsBomb();
            case 'E': case 'W':
                return null;
            default:
                return selectItem();
        }
    }

    private Item selectItem() {
        int randomNumber = new Random().nextInt(2) + 1;
        switch (randomNumber) {
            case 1:
                x='B';
                return new LivesBomb();
            case 2:
                x='D';
                return new LivesCoinsBomb();
        }
        return null;
    }

    public char getX() {
        return x;
    }
}
