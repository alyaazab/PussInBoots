package sample.model.memento;

public class Memento {
    private char [][] mapMemento;
    private int indexRunnerI, indexRunnerJ, health, moves, bullets;

    public Memento(char[][] mapMemento, int indexRunnerI, int indexRunnerJ, int health, int moves, int bullets) {
        this.mapMemento = mapMemento;
        this.indexRunnerI = indexRunnerI;
        this.indexRunnerJ = indexRunnerJ;
        this.health = health;
        this.moves = moves;
        this.bullets = bullets;
    }

    public char[][] getMapMemento() {
        return mapMemento;
    }

    public int getIndexRunnerI() {
        return indexRunnerI;
    }

    public int getIndexRunnerJ() {
        return indexRunnerJ;
    }

    public int getHealth() {
        return health;
    }

    public int getMoves() {
        return moves;
    }

    public int getBullets() {
        return bullets;
    }
}
