import java.awt.event.KeyEvent;

public class Player {

    private int position, score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player() {
        position = 350;
        score = 0;
    }

    public void moveRight() {
        position += 20;
    }

    public void moveLeft() {
        position -= 20;
    }

    public int getPosition() {
        return position;
    }

    public void mouseMove(int mousePosition) {
        //mouse positioning
        if (mousePosition > 691) {
            position = 690;
        } else if (mousePosition < 11) {
            position = 10;
        } else {
            position = mousePosition;
        }
    }

    public void keyboardMove(int k) {
        //keyboard positioning
        if (k == KeyEvent.VK_RIGHT) {
            if (position >= 690) {
                position = 690;
            } else {
                moveRight();
            }
        }

        if (k == KeyEvent.VK_LEFT) {
            if (position <= 10) {
                position = 10;
            } else {
                moveLeft();
            }
        }
    }
}
