package sample.model;

import sample.model.observer.InfoPanel;
import sample.model.observer.Observer;
import sample.model.observer.Subject;

public class Runner implements Subject {

    private Observer infoPanel = InfoPanel.getInstance();
    private String name;
    private int lives;
    private int coins;
    private int score;

    private static Runner ourInstance = new Runner();

    public static Runner getInstance() {
        return ourInstance;
    }

    private Runner() {
        this.lives = 9;
    }

    @Override
    public void notifyObserver() {
        infoPanel.update(name,lives ,coins ,score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObserver();
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
        notifyObserver();
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
        notifyObserver();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        notifyObserver();
    }
}
