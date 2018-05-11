package sample.controller;

import javafx.scene.layout.Pane;
import sample.model.Maze;

public class Game {

    GameState keyWPressed;
    GameState keyAPressed;
    GameState keySPressed;
    GameState keyDPressed;

    GameState gameState;

    public Game(Maze maze) {
        keyWPressed = new KeyWPressed(this, maze);
        keyDPressed = new KeyDPressed(this, maze);
        gameState = keyDPressed;
    }


    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setKeyWPressed(GameState keyWPressed) {
        this.keyWPressed = keyWPressed;
    }

    public void setKeyDPressed(GameState keyDPressed) {
        this.keyDPressed = keyDPressed;
    }

    public GameState getKeyWPressed() {
        return keyWPressed;
    }

    public GameState getKeyDPressed() {
        return keyDPressed;
    }

    public GameState getGameState() {
        return gameState;
    }


    public void keyDPressed(Maze maze) {
        gameState.keyDPressed();
    }

    public void keyWPressed() {
        gameState.keyWPressed();
    }


}
