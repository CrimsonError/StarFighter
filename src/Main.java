import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridLayout;

public class Main extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @SuppressWarnings("unused")
    public Main() {
        super("StarFighter");
        setSize(WIDTH, HEIGHT); // 800 * 600 window for the main game
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the window when the x is pressed

        JDialog popup = new JDialog(this, "Main Menu", true);
        popup.setSize(300, 200); // popup menu size
        popup.setResizable(false);
        popup.setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Select Difficulty to Start", JLabel.CENTER); // centeres the text in the box
        popup.add(titleLabel); // each element must be added to the popup menu object

        JButton easy = new JButton("Easy");
        easy.addActionListener(e -> {
            runGame("Easy");
            popup.dispose();
        });
        popup.add(easy);

        JButton medium = new JButton("Medium");
        medium.addActionListener(e -> {
            runGame("Medium");
            popup.dispose(); 
        });
        popup.add(medium);

        JButton hard = new JButton("Hard");
        hard.addActionListener(e -> {
            runGame("Hard");
            popup.dispose();
        });
        popup.add(hard);
        popup.setVisible(true); // set the menu to visible so you can see it
    }

    public void runGame(String difficulty) { 
        OuterSpace theGame = new OuterSpace(difficulty);
        theGame.requestFocus();
        ((Component) theGame).setFocusable(true); // so you can interact with the window
        getContentPane().add(theGame);
        setVisible(true); // makes the actual star fighter game appear so you can interact with it
    }

    public static void main(String args[]) {
        @SuppressWarnings("unused")
        Main run = new Main();
    }
}