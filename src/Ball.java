import java.awt.*;

public class Ball {

    private final int speed;

    private double posX;
    private double posY;
    private double Xdir;
    private double aXdir;
    private double Ydir;
    private double ratio;
    private int countingScore;

    public Ball() {
        posX = 120;
        posY = 350;
        Xdir = -1;
        aXdir = 0;
        Ydir = -3;
        speed = 10;
        countingScore = 0;
    }

    public void move(int paddlePosition){
        //check for collision with paddle
        checkCollision(paddlePosition);

        posX += Xdir;
        posY += Ydir;
        if (posX < 0 || posX > 780) {
            Xdir = -Xdir;
        }
        if (posY < 0) {
            Ydir = -Ydir;
        }
    }

    public void checkCollision(int paddlePosition){
        if ((paddlePosition - posX < 20 && posX - paddlePosition < 100 ) && posY > 530) {
            PlayAudio playAudio = new PlayAudio("sound/paddle.wav");

            Ydir = 3;
            if (posX - paddlePosition < 40){
                //left side
                leftSidePaddle(paddlePosition);
            } else {
                //right side
                rightSidePaddle(paddlePosition);
            }
        }
    }

    public void checkCollision(boolean[][] map, Player player, int score){
        int rows = map.length;
        int columns = map[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (!map[i][j]){
                    int brickPosX = j*75 + 25;
                    int brickPosY = i* 40 + 50;

                    Rectangle ballRect = new Rectangle((int)posX, (int)posY, 20, 20);
                    Rectangle brickRect = new Rectangle(brickPosX, brickPosY, 70, 34);

                    if(ballRect.intersects(brickRect)){
                        map[i][j] = true;
                        PlayAudio playAudio = new PlayAudio("sound/brick.wav");
                        player.setScore(player.getScore() + score);
                        countingScore++;
                        if (posX > brickPosX - 20 || posX < brickPosX + 70) {
                            Ydir = -Ydir;
                        } else {
                            Xdir = -Xdir;
                        }
                    }

                }
            }
        }

    }

    public void leftSidePaddle(int paddlePosition){
        //set angle of reflection with left side
        ratio = (posX - paddlePosition + 60)/100;
        Ydir = -Ydir * ratio;
        aXdir = Math.sqrt(speed - Math.pow(Ydir,2));
        Xdir = -aXdir;
    }

    public void rightSidePaddle(int paddlePosition){
        //set angle of reflection with right side
        ratio = (posX - paddlePosition - 140)/100;
        Ydir = Ydir * ratio;
        aXdir = Math.sqrt(speed - Math.pow(Ydir,2));
        Xdir = aXdir;
    }

    public double getPosX() {
        return posX;
    }

    public int getCountingScore() {
        return countingScore;
    }

    public double getPosY() {
        return posY;
    }

}
