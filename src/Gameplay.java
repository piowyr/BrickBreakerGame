import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by robholm on 29.04.17.
 */
public class Gameplay extends JPanel implements KeyListener, MouseListener, MouseMotionListener, ActionListener {

    private final GameFrame gameFrame;
    private final int delay;
    private final GpGraphics gpGraphics;
    private Highscore highscore;
    private Timer timer;
    private int score;
    private int playerScore;
    private int level;
    private GpObjects gpObjects;
    private boolean gameStarted;
    private String gameStatus;
    private String bestPlayer;
    private Color gameStatusColor;


    public Gameplay(GameFrame gameFrame, int delay) {
        this.gameFrame = gameFrame;
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.delay = delay;
        setScore(this.delay);
        level = 1;
        playerScore = 0;
        timer = new Timer(this.delay, this);
        timer.start();
        gpGraphics = new GpGraphics();
        gpObjects = new GpObjects(gpGraphics.getGameGraphics().getBlock());
        highscore = new Highscore();
        bestPlayer = highscore.bestPlayer();
        gameStarted = false;
        gameStatus = "Hit Enter to Start";
        gameStatusColor = Color.GREEN;
    }

    public void paint(Graphics g) {
        //background
        gpGraphics.getGfx().drawImage(gpGraphics.getGameGraphics().getImageBackground(), 0, 0, this);

        //the paddle
        gpGraphics.getGfx().drawImage(gpGraphics.getGameGraphics().getImagePaddle(), gpObjects.getPlayer().getPosition(), 550, this);

        //the ball
        gpGraphics.getGfx().drawImage(gpGraphics.getGameGraphics().getImageBall(), (int) gpObjects.getBall().getPosX(), (int) gpObjects.getBall().getPosY(), this);

        //blocks
        gpObjects.getMapCreator().draw(gpGraphics.getGfx(), this);

        //score
        gpGraphics.getGfx().setColor(Color.BLACK);
        gpGraphics.getGfx().setFont(new Font("Serif", Font.BOLD, 20));
        gpGraphics.getGfx().drawString(String.valueOf(gpObjects.getPlayer().getScore()), 875, 200);
        gpGraphics.getGfx().drawString(String.valueOf(level), 890, 520);
        gpGraphics.getGfx().drawString(bestPlayer, 830, 400);
        gpGraphics.getGfx().setFont(new Font("Serif", Font.BOLD, 30));


        //game status
        statusString();

        //draw gameplay
        g.drawImage(gpGraphics.getImg(), 0, 0, this);
        g.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (gameStarted) {
            //Allow to mouse with mouse
            gpObjects.getPlayer().mouseMove(mouseEvent.getX());
        }
        //Repaint paddle
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int k = keyEvent.getKeyCode();
        if (k == KeyEvent.VK_ENTER) {
            if (!gameStatus.equals("Hit Enter to continue!")) {
                //Allow to start moving
                gameStarted = true;
            } else if (!gameStarted) {
                playerScore = gpObjects.getPlayer().getScore();
                restartGame();
                level++;
                gameStarted = true;
                actionPerformed(null);
            }

        }
        if (k == KeyEvent.VK_ESCAPE) {
            //Pause
            timer.stop();
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Would you like to Quit?",
                    "An Exit Question",
                    JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                //Stop and back to menu
                gameStarted = false;
                if (gpObjects.getPlayer().getScore() > 0) {
                    saveScore();
                }
                SwapPanel.swapPanel(gameFrame);
            } else {
                //Unpaused
                timer.restart();
            }
        }
        if (gameStarted) {
            //Allow to keyboard move
            gpObjects.getPlayer().keyboardMove(k);
        }
        //repaint gameplay
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //Start game
        if (gameStarted) {
            timer.start();
            gpObjects.getBall().move(gpObjects.getPlayer().getPosition());
            gpObjects.getBall().checkCollision(gpObjects.getMapCreator().getMap(), gpObjects.getPlayer(), score);
        }
        if (gpObjects.getBall().getPosY() > 550) {
            //End game
            gameOver();
        }
        //check if player win a level
        checkGameStatus();
        repaint();
    }

    public void gameOver() {
        gameStarted = false;
        saveScore();
        int n = JOptionPane.showConfirmDialog(
                this,
                "You lose!\nWould you like to Play again?",
                "An Question",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            gameStatus = "Hit Enter to Start";
            //Restart
            restartGame();
        } else {
            //Back to menu
            SwapPanel.swapPanel(gameFrame);
            timer.stop();
        }
    }

    public void restartGame() {
        //Paint new blocks
        gpObjects = new GpObjects(gpGraphics.getGameGraphics().getBlock());
        gpObjects.getPlayer().setScore(playerScore);
        bestPlayer = highscore.bestPlayer();
    }

    public void statusString() {
        //Set game status
        if (!gameStarted) {
            gpGraphics.getGfx().setColor(gameStatusColor);
            gpGraphics.getGfx().setFont(new Font("Serif", Font.BOLD, 30));
            gpGraphics.getGfx().drawString(gameStatus, 250, 300);
            if (gameStatus.equals("Hit Enter to continue!")) {
                gpGraphics.getGfx().setFont(new Font("Serif", Font.BOLD, 45));
                gpGraphics.getGfx().drawString("Level UP!", 300, 250);
            }
            gpGraphics.getGfx().setFont(new Font("Serif", Font.BOLD, 30));
        }
    }

    public void setScore(int delay) {
        //Set given points
        if (delay == 4) {
            score = 50;
        } else if (delay == 8) {
            score = 20;
        } else {
            score = 10;
        }
    }

    public void checkGameStatus() {
        int countingScore = gpObjects.getBall().getCountingScore();
        if (countingScore != 0) {
            if (countingScore % 40 == 0) {
                PlayAudio playAudio = new PlayAudio("sound/levelUp.wav");
                continueGame();
            }
        }
    }

    public void continueGame() {
        timer.stop();
        gameStatus = "Hit Enter to continue!";
        gameStarted = false;
    }

    public void saveScore() {
        String name = JOptionPane.showInputDialog(null, "What is Your name?", "New Score!", 0);
        if (name != null) {
            if (name.trim().length() > 0) {
                System.out.println(name.length());
                highscore.addNewScore(name, gpObjects.getPlayer().getScore());
            } else {
                int n = JOptionPane.showConfirmDialog(
                        this,
                        "Wrong name!\nWould you like to write the name again?",
                        "An Question",
                        JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    saveScore();
                }
            }
        }
    }

}
