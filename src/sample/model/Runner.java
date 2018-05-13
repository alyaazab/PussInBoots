package sample.model;

import sample.model.items.Item;
import sample.model.observer.InfoPanel;
import sample.model.observer.Observer;
import sample.model.observer.Subject;

import java.util.Timer;
import java.util.TimerTask;

public class Runner implements Subject, RunnerInterface{

    private Observer infoPanel = InfoPanel.getInstance();
    private String name;
    private int health;
    private int lives;
    public static int liv = 3;
    private int coins;
    private int score;
    private int moves;
    private int bullets = 6;
    private Item item;
    private Timer timer = new Timer();


    private static Runner ourInstance = new Runner();

    public static Runner getInstance() {
        return ourInstance;
    }

    private Runner() {
        this.health = 50;
        this.lives = 3;
    }

    @Override
    public void notifyObserver() {
        infoPanel.update(name, health, coins, moves, score, bullets, lives);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObserver();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        notifyObserver();
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
        notifyObserver();
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
        notifyObserver();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        notifyObserver();
    }

    public int getBullets(){
        return bullets;
    }

    public void setBullets(int bullets){
        this.bullets = bullets;
        notifyObserver();
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void setArmourValue(Item i) {
        item = i;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                item = null;
            }
        };
        timer.schedule(timerTask, 2000L);
        if(item==null){
            timerTask.cancel();
            timer.cancel();
        }
    }

    @Override
    public Item getArmourValue() {
        return this.item;
    }
}
