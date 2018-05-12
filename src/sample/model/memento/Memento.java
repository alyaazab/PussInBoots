package sample.model.memento;

public class Memento {
    private char [][] mapMemento;
    private int indexRunnerI, indexRunnerJ;

    public Memento(char[][] mapMemento, int indexRunnerI, int indexRunnerJ) {
        this.mapMemento = mapMemento;
        this.indexRunnerI = indexRunnerI;
        this.indexRunnerJ = indexRunnerJ;
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
}
