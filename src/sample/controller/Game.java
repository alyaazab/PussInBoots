package sample.controller;

import sample.controller.keypresses.*;
import sample.model.Maze;

public class Game {

    private GameState keyWPressed;
    private GameState keyAPressed;
    private GameState keySPressed;
    private GameState keyDPressed;
    private GameState enterWPressed;
    private GameState enterAPressed;
    private GameState enterSPressed;
    private GameState enterDPressed;

    private GameState gameState;

    Game(Maze maze) {
        keyWPressed = new KeyWPressed(this, maze);
        keyDPressed = new KeyDPressed(this, maze);
        keyAPressed = new KeyAPressed(this, maze);
        keySPressed = new KeySPressed(this, maze);
        enterWPressed = new EnterWKeyPressed(this, maze);
        enterAPressed = new EnterAKeyPressed(this, maze);
        enterSPressed = new EnterSKeyPressed(this, maze);
        enterDPressed = new EnterDKeyPressed(this, maze);
        gameState = keyDPressed;
    }


    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getKeyWPressed() {
        return keyWPressed;
    }

    public GameState getKeyDPressed() {
        return keyDPressed;
    }

    public GameState getKeyAPressed() {
        return keyAPressed;
    }

    public GameState getKeySPressed() {
        return keySPressed;
    }

    public GameState getEnterWPressed() {
        return enterWPressed;
    }

    public GameState getEnterAPressed() {
        return enterAPressed;
    }

    public GameState getEnterSPressed() {
        return enterSPressed;
    }

    public GameState getEnterDPressed() {
        return enterDPressed;
    }

    public GameState getGameState() {
        return gameState;
    }


    public void keyDPressed() {
        gameState.keyDPressed();
    }

    public void keyWPressed() {
        gameState.keyWPressed();
    }

    public void keyAPressed() {
        gameState.keyAPressed();
    }

    public void keySPressed() {
        gameState.keySPressed();
    }


    public void keyEnterDPressed() {
        gameState.keyEnterDPressed();
    }

    public void keyEnterWPressed() {
        gameState.keyEnterWPressed();
    }

    public void keyEnterAPressed() {
        gameState.keyEnterAPressed();
    }

    public void keyEnterSPressed() {
        gameState.keyEnterSPressed();
    }
}
