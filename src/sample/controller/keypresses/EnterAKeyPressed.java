package sample.controller.keypresses;

import javafx.animation.AnimationTimer;
import sample.controller.Game;
import sample.controller.GameState;
import sample.model.Maze;
import sample.model.Runner;
import sample.model.weapons.Hand;

public class EnterAKeyPressed implements GameState {

    private Maze maze;
    private Game game;
    private Runner runnerObject = Runner.getInstance();

    private AnimationTimer timer = new AnimationTimer() {
        private long timestamp;
        private long time = 0;
        private long fraction = 0;
        private boolean b = false;
        @Override
        public void start() {
            //current time adjusted by remaining time from last run
            timestamp = System.currentTimeMillis() - fraction;
            maze.getBullet().setLayoutX(maze.getRunner().getLayoutX());
            maze.getBullet().setLayoutY(maze.getRunner().getLayoutY());
            maze.getPane().getChildren().add(maze.getBullet());
            System.out.println("STARTTT");
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            //save leftover time not handled with the last update
            maze.getPane().getChildren().remove(maze.getBullet());
            fraction = System.currentTimeMillis() - timestamp;
            System.out.println("STOPPPP");
        }

        @Override
        public void handle(long now) {
            long newTime = System.currentTimeMillis();
            maze.getBullet().setLayoutX(maze.getBullet().getLayoutX()-3);
            System.out.println("HANDLEEE");
        }
    };


    public EnterAKeyPressed(Game game, Maze maze) {
        this.game = game;
        this.maze = maze;
    }


    @Override
    public void keyWPressed() {
        game.setGameState(game.getKeyWPressed());
        game.keyWPressed();
        System.out.println("key W was pressed");
    }

    @Override
    public void keySPressed() {
        game.setGameState(game.getKeySPressed());
        game.keySPressed();
        System.out.println("key S was pressed");
    }

    @Override
    public void keyDPressed() {
        game.setGameState(game.getKeyDPressed());
        game.keyDPressed();
        System.out.println("key D was pressed");
    }

    @Override
    public void keyAPressed() {
        game.setGameState(game.getKeyAPressed());
        game.keyAPressed();
        System.out.println("key A was pressed");
    }

    @Override
    public void keyEnterAPressed() {
        game.setGameState(game.getEnterAPressed());
        System.out.println("Enter A Pressed");
        if (maze.getWeapon() instanceof Hand) {
            maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[maze.getRow()][maze.getCol() - 1]));
            if (maze.getItem() != null){
                if(maze.getWeapon().hit(maze.getItem())) {
                    maze.getGameMap()[maze.getRow()][maze.getCol() - 1] = 'E';

                    maze.updateHealth();
                    maze.updateGame();
                }
            }
        } else {
            /*maze.getBullet().setLayoutX(maze.getRunner().getLayoutX());
            maze.getBullet().setLayoutY(maze.getRunner().getLayoutY());
            maze.getPane().getChildren().add(maze.getBullet());*/
            runnerObject.setBullets(runnerObject.getBullets()-1);
            maze.getLblBullets().setText(runnerObject.getBullets()+"");
            System.out.println("bullets = " + runnerObject.getBullets());

            for (int i = maze.getCol() - 1; i >= 0; i--) {
                timer.start();
                timer.handle(1000);
                if (maze.getGameMap()[maze.getRow()][i] != 'E' && maze.getGameMap()[maze.getRow()][i] != 'W'
                        && maze.getGameMap()[maze.getRow()][i] != 'C') {
                    maze.setItem(maze.getItemFactory().createItem(maze.getGameMap()[maze.getRow()][i]));
                    if(maze.getWeapon().hit(maze.getItem())){
                        maze.getGameMap()[maze.getRow()][i] = 'E';

                        maze.updateHealth();
                        maze.updateGame();
                    }
                    break;
                }
                maze.getPane().getChildren().remove(maze.getBullet());
                timer.stop();
            }
        }

        game.setGameState(game.getKeyAPressed());
    }

    @Override
    public void keyEnterWPressed() {
        game.setGameState(game.getEnterWPressed());
        System.out.println("Enter W Pressed");
        game.keyEnterWPressed();
    }

    @Override
    public void keyEnterSPressed() {
        game.setGameState(game.getEnterSPressed());
        System.out.println("Enter S Pressed");
        game.keyEnterSPressed();
    }

    @Override
    public void keyEnterDPressed() {
        game.setGameState(game.getEnterDPressed());
        System.out.println("Enter D Pressed");
        game.keyEnterDPressed();
    }
}
