package sample.files;

import sample.Main;
import sample.model.Maze;
import sample.model.Runner;
import sample.model.observer.InfoPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileClass {

    public char[][] getMaze() {
        char[][] gameMaze = new char[30][30];
        File file = new File(generateRandomFile());
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            int l = 0;
            while ((line = bf.readLine()) != null && l < 30) {
                System.out.println(line);
                String[] split = line.split(" ");
                for (int i = 0; i < split.length; i++) {
                    gameMaze[l][i] = split[i].charAt(0);
                }
                l++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameMaze;
    }

    private String generateRandomFile() {
        int randomNumber = new Random().nextInt(2) + 1;
        switch (randomNumber) {
            case 1:
                return "res/MazeFiles/Maze1";
            case 2:
                return "res/MazeFiles/Maze1";
        }
        return " ";
    }

    public void saveGame(char[][] gameMap, InfoPanel infoPanel, int r, int c, String timeString, String filepath) {
        System.out.println(filepath);
        File file = new File("res/SavedGames/" + filepath + ".txt");
        boolean b = false;
        try {
            if (!file.exists()) {
                b = file.createNewFile();
            }
            if (b) {
                System.out.println("Success");
            }
            FileOutputStream fos = new FileOutputStream(file, false);
            String charset = "UTF-8";
            OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw, true);
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    pw.print(gameMap[i][j] + " ");
                }
                pw.println();
            }
            pw.println(infoPanel.getMoves());
            pw.println(infoPanel.getHealth());
            pw.println(infoPanel.getBullets());
            pw.println(r);
            pw.println(c);
            pw.println(timeString);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(FileClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String filepath, Maze maze) {
        System.out.println(filepath);
        File file = new File("res/SavedGames/" + filepath + ".txt");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            int l = 0;
            while (l < 30 && null != (line = bf.readLine())) {
                System.out.println(line);
                String[] split = line.split(" ");
                for (int i = 0; i < split.length; i++) {
                    maze.getGameMap()[l][i] = split[i].charAt(0);
                }
                l++;
            }
            Runner runner = Runner.getInstance();
            line=bf.readLine();
            System.out.println("----------"+ line);
            runner.setMoves(Integer.parseInt(line.trim()));

            line=bf.readLine();
            System.out.println("----------"+ line);
            runner.setHealth(Integer.parseInt(line.trim()));

            line=bf.readLine();
            System.out.println("----------"+ line);
            runner.setBullets(Integer.parseInt(line.trim()));

            line=bf.readLine();
            System.out.println("----------"+ line);
            maze.setRunnerY(Integer.parseInt(line.trim()));
            maze.setRow(Integer.parseInt(line.trim()));

            line=bf.readLine();
            System.out.println("----------"+ line);
            maze.setRunnerX(Integer.parseInt(line.trim()));
            maze.setCol(Integer.parseInt(line.trim()));

            line=bf.readLine();
            System.out.println("----------"+ line);
            maze.getLblTimer().setText(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeListOfSavedGames(ArrayList<String> savedGames){
        File file = new File("res/SavedGames/ListofSavedGames");
        boolean b = false;
        try {
            if (!file.exists()) {
                b = file.createNewFile();
            }
            if (b) {
                System.out.println("Success");
            }
            FileOutputStream fos = new FileOutputStream(file, false);
            String charset = "UTF-8";
            OutputStreamWriter osw = new OutputStreamWriter(fos, charset);
            BufferedWriter bw = new BufferedWriter(osw);
            PrintWriter pw = new PrintWriter(bw, true);
            for (String savedGame : savedGames) {
                pw.println(savedGame);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(FileClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readListOfSavedGames(){
        File file = new File("res/SavedGames/ListofSavedGames" );
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            while (null != (line = bf.readLine())) {
                Main.savedGames.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
