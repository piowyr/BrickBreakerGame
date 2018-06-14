import java.awt.event.*;

import javax.swing.*;

public class ScoresButton extends JButton implements ActionListener {

    private Highscore highscore;

    ScoresButton() {
        super(new ImageIcon("graphics/buttons/scores.png"));
        addActionListener(this);
        highscore = new Highscore();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        highscore.showScore();
    }
}
