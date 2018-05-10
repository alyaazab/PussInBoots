package sample.model;

import sample.model.observer.InfoPanel;
import sample.model.observer.Observer;
import sample.model.observer.Subject;

import java.util.Timer;
import java.util.TimerTask;

public class Runner implements Subject, RunnerInterface{

    private Observer infoPanel = InfoPanel.getInstance();
    private String name;
    private int lives;
    private int coins;
    private int score;
    private int moves;
    private boolean armour = false;
    private Timer timer = new Timer();


    private static Runner ourInstance = new Runner();

    public static Runner getInstance() {
        return ourInstance;
    }

    private Runner() {
        this.lives = 9;
    }

    @Override
    public void notifyObserver() {
        infoPanel.update(name, lives, coins, moves, score);
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

    @Override
    public void setArmourValue() {
        armour=true;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                armour = false;
            }
        };
        timer.schedule(timerTask, 2000L);
        if(!armour){
            timerTask.cancel();
            timer.cancel();
        }
    }

    @Override
    public boolean getArmourValue() {
        return this.armour;
    }
}
