package sample.model.memento;

public class Originator {
    private char [][] mapMemento;
    private int indexRunnerI, indexRunnerJ;

    public void setMemento(char[][] mapMemento, int i, int j) {
        this.mapMemento = mapMemento;
        this.indexRunnerI = i;
        this.indexRunnerJ = j;
    }

    public Memento storeInMemento(){
        return new Memento(mapMemento, indexRunnerI, indexRunnerJ);
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
}
