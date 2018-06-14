import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by robholm on 30.04.17.
 */
public class ExitButton extends JButton implements ActionListener{

    public ExitButton() {
        super(new ImageIcon("graphics/buttons/exit.png"));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);

    }
}
