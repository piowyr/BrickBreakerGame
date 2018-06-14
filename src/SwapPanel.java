public class SwapPanel {

    //static methods for swaping Panel elements

    public static void swapPanel(Gameplay gameplay, GameFrame gameFrame) {
        //Deleting menu and adding gameplay panel
        gameFrame.getContentPane().removeAll();
        gameFrame.add(gameplay);
        gameFrame.setSize(1000,650);
        gameFrame.revalidate();
        gameFrame.repaint();
        gameplay.requestFocusInWindow();
    }

    public static void swapPanel(GameFrame gameFrame){
        //Deleting gameplay and adding menu panel
        GameMenu gameMenu = new GameMenu(gameFrame);
        gameFrame.getContentPane().removeAll();
        gameFrame.add(gameMenu);
        gameFrame.setSize(800,600);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

}

