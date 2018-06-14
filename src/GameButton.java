import java.awt.event.*;
import javax.swing.*;

public class GameButton extends JButton implements ActionListener {

    private final GameFrame gameFrame;

    public GameButton(GameFrame gameFrame) {
        super(new ImageIcon("graphics/buttons/game.png"));
        this.gameFrame = gameFrame;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Gameplay gameplay = new Gameplay(gameFrame, gameFrame.getLevel());
        SwapPanel.swapPanel(gameplay, gameFrame);
    }
}
