package sample.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class FileClass {

    public char[][] getMaze(){
        char [][] gameMaze = new char[30][30];
        File file = new File(generateRandomFile());
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            int l=0;
            while ((line = bf.readLine()) != null && l < 30) {
                System.out.println(line);
                String [] split = line.split(" ");
                for(int i = 0; i < split.length; i++){
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
        switch (randomNumber){
            case 1:
                return "res/MazeFiles/Maze1";
            case 2:
                return "res/MazeFiles/Maze1";
        }
        return " ";
    }
}
