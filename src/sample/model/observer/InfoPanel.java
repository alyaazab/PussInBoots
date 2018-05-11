package sample.model.observer;

public class InfoPanel implements Observer {

    private String name;
    private int health;
    private int coins;
    private int score;
    private int moves;

    private static InfoPanel ourInstance = new InfoPanel();

    public static InfoPanel getInstance() {
        return ourInstance;
    }

    private InfoPanel() {
    }

    @Override
    public void update(String name, int health, int coins, int moves, int score) {
        this.name = name;
        this.health = health;
        this.coins =  coins;
        this.moves = moves;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getCoins() {
        return coins;
    }

    public int getMoves() {
        return moves;
    }
    public int getScore() {
        return score;
    }
}
