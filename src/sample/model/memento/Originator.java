package sample.model.memento;

public class Originator {
    private char [][] mapMemento;
    private int indexRunnerI, indexRunnerJ, health, moves, bullets;

    public void setMemento(char[][] mapMemento, int i, int j, int health, int moves, int bullets) {
        this.mapMemento = mapMemento;
        this.indexRunnerI = i;
        this.indexRunnerJ = j;
        this.health = health;
        this.moves = moves;
        this.bullets = bullets;
    }

    public Memento storeInMemento(){
        return new Memento(mapMemento, indexRunnerI, indexRunnerJ, health, moves, bullets);
    }

    public char[][] restoreMapFromMemento(Memento memento){
        mapMemento = memento.getMapMemento();
        return mapMemento;
    }

    public int restoreIndexIFromMemento(Memento memento){
        indexRunnerI = memento.getIndexRunnerI();
        return indexRunnerI;
    }

    public int restoreIndexJFromMemento(Memento memento){
        indexRunnerJ = memento.getIndexRunnerJ();
        return indexRunnerJ;
    }

    public int restoreHealthFromMemento(Memento memento){
        health = memento.getHealth();
        return health;
    }


    public int restoreMovesFromMemento(Memento memento){
        moves = memento.getMoves();
        return moves;
    }

    public int restoreBulletsFromMemento(Memento memento){
        bullets = memento.getBullets();
        return bullets;
    }
}
