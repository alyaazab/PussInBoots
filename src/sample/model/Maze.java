package sample.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.files.FileClass;
import sample.model.items.Item;
import sample.model.items.ItemFactory;
import sample.model.memento.CareTaker;
import sample.model.memento.Originator;
import sample.model.observer.InfoPanel;
import sample.model.weapons.Hand;
import sample.model.weapons.Weapon;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Maze {

    private FileClass fileClass = new FileClass();
    private char[][] gameMap = fileClass.getMaze();

    private Item item = null;
    private ItemFactory itemFactory = new ItemFactory();

    private Weapon weapon = null;
    private ImageView bullet;
    private ImageView runner;

    private Pane pane;
    private Label lblBullets, lblTimer, lblMoves, lblHealth, lblLives;

    private int row=29;
    private int col=0;
    private Runner runnerObject = Runner.getInstance();
    private InfoPanel infoPanel = InfoPanel.getInstance();

    private CareTaker careTaker = new CareTaker();
    private Originator originator = new Originator();
    public static int index=-1;
    public static ArrayList<ImageView> imageViews = new ArrayList<>();
    public static int cIndex = -2;

    public void setUpArray(){
        try {
            Image image;

            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (gameMap[i][j] == 'W') {
                        image = new Image(new FileInputStream("res/Photos/wall.png"));
                    } else if (gameMap[i][j] == 'E' || gameMap[i][j] == 'C') {
                        image = new Image(new FileInputStream("res/Photos/empty2.png"));
                    } else {
                        item = itemFactory.createItem(gameMap[i][j]);
                        image = item.getImage();
                        gameMap[i][j] = itemFactory.getX();
                    }

                    ImageView img = new ImageView(image);

                    img.setLayoutX(j * 22);
                    img.setLayoutY(i * 22);

                    pane.getChildren().add(img);
                    imageViews.add(img);

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

            image = new Image(new FileInputStream("res/Photos/runnerIdle.png"));

            runner = new ImageView(image);
            runner.setLayoutX(0);
            runner.setLayoutY(29 * 22);

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

    public Label getLblMoves() {
        return lblMoves;
    }

    public void setLblMoves(Label lblMoves) {
        this.lblMoves = lblMoves;
    }

    public Label getLblHealth() {
        return lblHealth;
    }

    public Label getLblTimer() {
        return lblTimer;
    }

    public void setLblHealth(Label lblHealth) {
        this.lblHealth = lblHealth;
    }

    public void setLblTimer(Label lblTimer) {
        this.lblTimer = lblTimer;
    }

    public void setLblLives(Label lblLives) {
        this.lblLives = lblLives;
    }

    public Label getLblLives(){
        return lblLives;
    }

    public void setLblBullets(Label lblBullets) {
        this.lblBullets = lblBullets;
    }

    public Label getLblBullets() {
        return lblBullets;
    }

    public void updateMoves() {
        runnerObject.setMoves(runnerObject.getMoves() + 1);
        getLblMoves().setText(Integer.toString(infoPanel.getMoves()));
        System.out.println(infoPanel.getMoves());
    }

    public void updateHealth() {
        getItem().change(runnerObject);
        getLblHealth().setText(Integer.toString(infoPanel.getHealth()));
        getLblLives().setText(Integer.toString(infoPanel.getLives()));
        System.out.println("updating health");
    }

    public void updateGame() {
        getPane().getChildren().remove(getRunner());
        setUpArray();
        getPane().getChildren().add(getRunner());
    }
    public CareTaker getCareTaker() {
        return careTaker;
    }

    public Originator getOriginator() {
        return originator;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void setRunnerX(int j){
        getRunner().setLayoutX(j*22);
    }

    public void setRunnerY(int i){
        getRunner().setLayoutY(i*22);
    }

    public void saveMemento(){
        getOriginator().setMemento(getGameMap(), getRow(), getCol(), runnerObject.getHealth(),
                runnerObject.getMoves(), runnerObject.getBullets());
        getCareTaker().addMemento(getOriginator().storeInMemento());
    }

    public void loadMemento(){
        System.out.println("RETURN TO CHECK POINT");
        setGameMap(getOriginator().restoreMapFromMemento(
                getCareTaker().getMemento(Maze.index+1)));
        getRunner().setLayoutX(getOriginator().restoreIndexJFromMemento(
                getCareTaker().getMemento(Maze.index+1))*22);
        getRunner().setLayoutY(getOriginator().restoreIndexIFromMemento(
                getCareTaker().getMemento(Maze.index+1))*22);

        runnerObject.setHealth(getOriginator().restoreHealthFromMemento(
                getCareTaker().getMemento(Maze.index+1)));

        runnerObject.setMoves(getOriginator().restoreMovesFromMemento(
                getCareTaker().getMemento(Maze.index+1)));

        runnerObject.setBullets(getOriginator().restoreBulletsFromMemento(
                getCareTaker().getMemento(Maze.index+1)));

        setCol(getOriginator().restoreIndexJFromMemento(
                getCareTaker().getMemento(Maze.index+1)));
        setRow(getOriginator().restoreIndexIFromMemento(
                getCareTaker().getMemento(Maze.index+1)));
        Maze.index++;
    }
}
