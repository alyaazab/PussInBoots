package sample.model.memento;

import java.util.ArrayList;

public class CareTaker {

    private ArrayList<Memento> savedMementos = new ArrayList<>();

    public void addMemento(Memento memento){
        savedMementos.add(memento);
    }

    public Memento getMemento(int index){
        return savedMementos.get(index);
    }

}
