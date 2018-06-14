import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class InstructionsButton extends JButton implements ActionListener {

    ImageIcon scoreIcon = new ImageIcon("icons/score2.png");

    InstructionsButton() {
        super(new ImageIcon("graphics/buttons/instructions.png"));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        intruction();
    }

    public void intruction() {
        Scanner scan = null;
        File file = new File("instruction/instruction.txt");
        FileWriter writer = null;
        String instruction = "";
        String n = "";
        boolean empty = checkEmptyFile();

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            if (empty) {
                System.out.println("Hmm" + n);
                writer = new FileWriter(file);
                writer.write("Destroy all the bricks by hitting them with ball."
                        + "\nFor each destoyed brick you will get points."
                        + "\nYou need to use paddle to control the ball.\n"
                        + "\nGame created by Piotr Wyrwiński. All rights reserved.");
                writer.close();
            }
            scan = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {
            instruction += scan.nextLine() + "\n";
        }
        scan.close();

        JOptionPane.showMessageDialog(null, instruction, "Instructions", 0, scoreIcon);
    }

    public boolean checkEmptyFile(){
        File file = new File("instruction/instruction.txt");
        Scanner scan = null;
        String test = "";

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("aha");
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            test += scan.nextLine();
        }
        scan.close();
        if (test.equals("")){
            return true;
        }
        return false;
    }
}
