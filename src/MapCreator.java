import java.awt.*;
import java.awt.image.BufferedImage;

public class MapCreator {

    private boolean[][] map;
    private BufferedImage[] block;
    private int[][] imageMap;

    public MapCreator(BufferedImage[] block) {
        map = new boolean[4][10];
        this.block = block;
        createMap();
    }

    public void draw(Graphics g, Gameplay gameplay){
        //paint all random blocks
        int rows = map.length;
        int columns = map[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (!map[i][j]){
                    g.drawImage(block[imageMap[i][j]], j*75 + 25, i* 40 + 50, gameplay);
                }
            }
        }
    }

    public void createMap(){
        //drawing random blocks
        imageMap = new int[4][10];
        int random;
        int rows = imageMap.length;
        int columns = imageMap[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (!map[i][j]){
                    random = (int)(Math.random() * 7);
                    imageMap[i][j] = random;
                }
            }
        }
    }

    public boolean[][] getMap() {
        return map;
    }
}
