import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by robholm on 29.04.17.
 */
public class GameMenu extends JPanel{

    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;

    private BufferedImage backgroundImage;

    private JButton gButton;
    private JButton sButton;
    private JButton iButton;
    private JButton oButton;
    private JButton eButton;

    public GameMenu(GameFrame gameFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension minSize = new Dimension(5, 100);
        Dimension prefSize = new Dimension(5, 100);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        add(new Box.Filler(minSize, prefSize, maxSize));



        addButtons(gameFrame);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setButtonPosition();
    }

    public void paintComponent(Graphics g){
        //draw Background Image
        super.paintComponent(g);
        File backgroundImageFile = new File("graphics/background/menuBackground.jpg");
        try {
            backgroundImage = ImageIO.read(backgroundImageFile);
        } catch (IOException e) {
            System.err.println("Error reading the image");
            e.printStackTrace();
        }

        g.drawImage(backgroundImage, 0,0,null);
    }

    private void addButtons(GameFrame gameFrame){
        gButton = new GameButton(gameFrame);
        sButton = new ScoresButton();
        iButton = new InstructionsButton();
        oButton = new OptionButton(gameFrame);
        eButton = new ExitButton();

        add(gButton);
        add(sButton);
        add(iButton);
        add(oButton);
        add(eButton);
    }

    private void setButtonPosition(){
        //set buttons at the center
        gButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        iButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        oButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        eButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
