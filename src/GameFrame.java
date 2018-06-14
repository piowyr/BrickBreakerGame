import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by robholm on 29.04.17.
 */
public class GameFrame extends JFrame{

    private int level;

    public GameFrame() {
        super("Brick Breaker");
        add(new GameMenu(this));

        menu();

        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        PlayAudio playAudio = new PlayAudio("sound/music.wav");
        level = 8;
    }

    public void menu(){
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);

        Menu menuEditor = new Menu("Edit Score");
        Menu menuExit = new Menu("Exit");
        menuBar.add(menuEditor);
        menuBar.add(menuExit);
        MenuItem clearScore = new MenuItem("Clear score table");
        MenuItem quit = new MenuItem("Quit");
        menuEditor.add(clearScore);
        menuExit.add(quit);

        clearScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Highscore highscore = new Highscore();
                highscore.resetScore();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
