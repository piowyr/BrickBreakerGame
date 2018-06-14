import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionButton extends JButton implements ActionListener{

    private final GameFrame gameFrame;

    public OptionButton(GameFrame gameFrame) {
        super(new ImageIcon("graphics/buttons/options.png"));
        this.gameFrame = gameFrame;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object[] possibilities = {"Easy", "Medium", "Hard"};
        String s = (String)JOptionPane.showInputDialog(
                gameFrame,
                "Choose a level:",
                "Level",
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("graphics/buttons/options.png"),
                possibilities,
                "Medium");
        if (s == "Easy") {
            gameFrame.setLevel(12);
        } else if (s == "Medium"){
            gameFrame.setLevel(8);
        } else if (s == "Hard"){
            gameFrame.setLevel(4);
            PlayAudio playAudio = new PlayAudio("sound/hard.wav");
        }
    }
}
