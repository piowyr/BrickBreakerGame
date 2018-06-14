import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Highscore {

    private final File scoreFile = new File("scores/highscore.txt");

    ImageIcon scoreIcon = new ImageIcon("icons/score.png");

    private String[] nick;
    private int[] score;

    public Highscore() {
        if (!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
                System.out.println("creating file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int countScores() throws FileNotFoundException {
        //Count lines in file
        int i = 0;
        Scanner read = new Scanner(scoreFile);
        while (read.hasNextLine()) {
            read.nextLine();
            i++;
        }
        return i;
    }

    private void createTable(int i) throws FileNotFoundException {
        nick = new String[i+1];
        score = new int[i+1];
        Scanner read = new Scanner(scoreFile);
        for (int j = 0; j < i; j++) {
            nick[j] = read.next();
            try {
                score[j] = Integer.parseInt(read.next());
            } catch (NumberFormatException e) {
                score[j] = 0;
            }
        }
        read.close();
    }

    private void createTable(int i, String playerNick, int playerScore) throws FileNotFoundException {
        //Create table and add new Player to table
        createTable(i);
        if (i > 24){
            nick[i-1] = playerNick;
            score[i-1] = playerScore;
        } else {
            nick[i] = playerNick;
            score[i] = playerScore;
        }
    }

    public void addNewScore(String playerNick, int playerScore) {
        try {
            createTable(countScores(), playerNick, playerScore);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sortTable();
        int i = nick.length;
        FileWriter writer = null;
        try {
            writer = new FileWriter("scores/highscore.txt");
            for (int j = 0; j < i; j++) {
                writer.write(nick[j] + " " + score[j] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortTable(){
        boolean unsort = true;
        int aScore;
        String aNick;
        while (unsort){
            unsort = false;
            for (int j=0; j<score.length-1; j++){
                if (score[j] < score[j+1]){
                    aScore = score[j];
                    score[j] = score[j+1];
                    score[j+1] = aScore;
                    aNick = nick[j];
                    nick[j] = nick[j+1];
                    nick[j+1] = aNick;
                    unsort = true;
                }
            }
        }
    }

    public void showScore(){
        String scoreTable = "";
        File file = new File("scores/highscore.txt");
        Scanner read = null;
        try {
            read = new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (read.hasNextLine()) {
            scoreTable += read.nextLine() + "\n";
        }
        read.close();
        JOptionPane.showMessageDialog(null, scoreTable, "Highscores", 0, scoreIcon);
    }

    public void resetScore(){
        try {
            FileWriter writer = new FileWriter("scores/highscore.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String bestPlayer(){
        File file = new File("scores/highscore.txt");
        Scanner read = null;
        String nick = "";
        try {
            read = new Scanner(file);
            nick = read.nextLine();
            read.close();
        } catch (Exception e) {
        }
        if (nick.equals("")){
            return "Nobody 0";
        }
        return nick;
    }


}
