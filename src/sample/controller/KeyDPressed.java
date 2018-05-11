package sample.controller;

public class KeyDPressed implements GameState {

    Game game;

    public KeyDPressed(Game game) {
        this.game = game;
    }


    @Override
    public void keyWPressed() {
        game.setGameState(game.getKeyWPressed());
        System.out.println("key W was pressed");

    }

    @Override
    public void keyAPressed() {

    }

    @Override
    public void keySPressed() {

    }

    @Override
    public void keyDPressed() {
        game.setGameState(game.getKeyDPressed());
        System.out.println("key D was pressed");
    }
}
