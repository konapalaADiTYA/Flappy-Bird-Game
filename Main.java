import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");// Title on the title bar
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null); // Places the window at the centre of the screen
        frame.setResizable(false); // Won't allow to resize the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Click on X to close
        FlappyBird fb = new FlappyBird();
        frame.add(fb);
        frame.pack();// So that height and width don't mix up with the title bax
        fb.requestFocus();
        frame.setVisible(true); // Makes the frame visible
    }
}
