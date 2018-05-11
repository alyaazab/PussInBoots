package sample.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.files.FileClass;
import sample.model.items.Item;
import sample.model.items.ItemFactory;
import sample.model.weapons.Hand;
import sample.model.weapons.Weapon;

import java.io.FileInputStream;

public class Maze {

    private FileClass fileClass = new FileClass();
    private char[][] gameMap = fileClass.getMaze();

    private Item item = null;
    private ItemFactory itemFactory = new ItemFactory();

    private Weapon weapon = null;
    private ImageView bullet;
    private ImageView runner;

    private Pane pane;

    private int row=29;
    private int col=0;

    public void setUpArray(){
        try {
            Image image;

            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (gameMap[i][j] == 'W') {
                        image = new Image(new FileInputStream("res/Photos/wall.png"));
                    } else if (gameMap[i][j] == 'E') {
                        image = new Image(new FileInputStream("res/Photos/empty.png"));
                    } else {
                        item = itemFactory.createItem(gameMap[i][j]);
                        image = item.getImage();
                        gameMap[i][j] = itemFactory.getX();
                    }

                    ImageView img = new ImageView(image);

                    img.setLayoutX(j * 20);
                    img.setLayoutY(i * 20);

                    pane.getChildren().add(img);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setUpGame(){
        weapon = new Hand();
        try {
            Image image;
            bullet = new ImageView(new Image(new FileInputStream("res/Photos/bullet.jpg")));
            bullet.setFitWidth(10);
            bullet.setFitHeight(10);
            setUpArray();

            image = new Image(new FileInputStream("res/Photos/runner.png"));

            runner = new ImageView(image);
            runner.setLayoutX(0);
            runner.setLayoutY(29 * 20);

            pane.getChildren().add(runner);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public char[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(char[][] gameMap) {
        this.gameMap = gameMap;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ItemFactory getItemFactory() {
        return itemFactory;
    }

    public void setItemFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public ImageView getBullet() {
        return bullet;
    }

    public ImageView getRunner() {
        return runner;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setBullet(ImageView bullet) {
        this.bullet = bullet;
    }

    public void setRunner(ImageView runner) {
        this.runner = runner;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

}
