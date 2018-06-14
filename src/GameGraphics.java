import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by robholm on 03.05.17.
 */
public class GameGraphics {

    private BufferedImage imageBackground;
    private BufferedImage imagePaddle;
    private BufferedImage imageBall;
    private BufferedImage[] block;

    public GameGraphics() {
       loadImage();
    }

    public void loadImage(){
        File backgroundFile = new File("graphics/background/gameplayBackground.png");
        File paddleFile = new File("graphics/gameplay/paddle.png");
        File ballFile = new File("graphics/gameplay/ball.png");
        File blockFile[] = new File[7];
        for (int i = 0; i < blockFile.length; i++){
            //load list with blocks image
            blockFile[i] = new File("graphics/blocks/" + (i+1) + ".png");
        }
        try {
            block = new BufferedImage[blockFile.length];
            imageBackground = ImageIO.read(backgroundFile);
            imagePaddle = ImageIO.read(paddleFile);
            imageBall = ImageIO.read(ballFile);
            for (int j = 0; j < block.length; j++){
                block[j] = ImageIO.read(blockFile[j]);
            }
        } catch (IOException e) {
            System.err.println("Error reading the image");
            e.printStackTrace();
        }
    }

    public BufferedImage getImageBackground() {
        return imageBackground;
    }

    public BufferedImage getImageBall() {
        return imageBall;
    }

    public BufferedImage getImagePaddle() {
        return imagePaddle;
    }

    public BufferedImage[] getBlock() {
        return block;
    }
}
