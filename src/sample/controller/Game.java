package sample.controller;

public class Game {

    GameState keyWPressed;
    GameState keyDPressed;

    GameState gameState;

    public Game() {
        keyWPressed = new KeyWPressed(this);
        keyDPressed = new KeyDPressed(this);
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


    public void keyDPressed() {
        //keyDPressed.keyPressed();
        gameState.keyDPressed();
    }

    public void keyWPressed() {
        //keyWPressed.keyPressed();
        gameState.keyWPressed();
    }


}
