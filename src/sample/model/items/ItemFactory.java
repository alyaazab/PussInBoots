package sample.model.items;

import sample.model.items.Bombs.LivesBomb;
import sample.model.items.Bombs.LivesCoinsBomb;

public class ItemFactory {

    public Item createItem(char item){
        switch (item){
            case 'B':
                return new LivesBomb();
            case 'D':
                return new LivesCoinsBomb();
        }
        return null;
    }
}
