package sample.model.observer;

public class InfoPanel implements Observer {

    private String name;
    private int lives;
    private int coins;
    private int score;

    private static InfoPanel ourInstance = new InfoPanel();

    public static InfoPanel getInstance() {
        return ourInstance;
    }

    private InfoPanel() {
    }

    @Override
    public void update(String name, int lives, int coins, int score) {
        this.name = name;
        this.lives = lives;
        this.coins =  coins;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public int getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
    }
}
