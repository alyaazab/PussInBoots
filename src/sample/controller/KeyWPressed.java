package sample.controller;

import sample.model.Maze;

public class KeyWPressed implements GameState {

    Game game;
    Maze maze;

    public KeyWPressed(Game game, Maze maze) {
        this.game = game;
        this.maze = maze;
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
