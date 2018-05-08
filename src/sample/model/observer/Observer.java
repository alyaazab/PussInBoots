package sample.model.observer;

import sample.model.Runner;

public interface Observer {
    void update(String name, int lives, int coins, int moves, int score);
}
