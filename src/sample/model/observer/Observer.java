package sample.model.observer;

public interface Observer {
    void update(String name, int health, int coins, int moves, int score, int bullets, int lives);
}
