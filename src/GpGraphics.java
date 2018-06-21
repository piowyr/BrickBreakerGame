import java.awt.*;
import java.awt.image.BufferedImage;

public class GpGraphics {

    private GameGraphics gameGraphics;
    private Graphics gfx;
    private BufferedImage img;

    public GpGraphics() {
        gameGraphics = new GameGraphics();
        img = new BufferedImage(1000,600,BufferedImage.TYPE_INT_RGB);
        gfx = img.getGraphics();
    }

    public GameGraphics getGameGraphics() {
        return gameGraphics;
    }

    public BufferedImage getImg() {
        return img;
    }

    public Graphics getGfx() {
        return gfx;
    }

}
