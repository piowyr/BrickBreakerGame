import java.awt.image.BufferedImage;

public class GpObjects {

    private Player player;
    private Ball ball;
    private MapCreator mapCreator;

    public GpObjects(BufferedImage[] block) {
        player = new Player();
        ball = new Ball();
        mapCreator = new MapCreator(block);
    }

    public Player getPlayer() {
        return player;
    }

    public Ball getBall() {
        return ball;
    }

    public MapCreator getMapCreator() {
        return mapCreator;
    }
}
