package sample.model.observer;

import sample.model.Runner;

public interface Observer {
    void update(String name, int health, int coins, int moves, int score);
}
