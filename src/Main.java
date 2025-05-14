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
        super("Star Fighter");
        setSize(WIDTH, HEIGHT); // default height/width per instructions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDialog popup = new JDialog(this, "Main Menu", true);
        popup.setSize(300, 200); // best looking size
        popup.setResizable(false); // dont allow people to change the window size, may mess up formatting
        popup.setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Select Difficulty to Start", JLabel.CENTER); // centeres the text in the box
        popup.add(titleLabel); // you need to add all the titleelements to the object popout

        JButton easy = new JButton("Easy");
        easy.addActionListener(e -> {
            runGame("Easy");
            popup.dispose();
        });
        popup.add(easy);

        JButton medium = new JButton("Medium");
        medium.addActionListener(e -> {
            runGame("Medium");
            popup.dispose(); // closes the popup after the button is activated
        });
        popup.add(medium);

        JButton hard = new JButton("Hard");
        hard.addActionListener(e -> {
            runGame("Hard");
            popup.dispose(); // any option closes the popup, this stuff in here is only fired when the button is pressed
        });
        popup.add(hard);
        popup.setVisible(true); // set the menu to vis so you can see it
    }

    public void runGame(String difficulty) { // might be a better way but this i feel is the easiest
        OuterSpace theGame = new OuterSpace(difficulty);
        theGame.requestFocus();
        ((Component) theGame).setFocusable(true); // keeps the game focused in a tab manager
        getContentPane().add(theGame);
        setVisible(true); // makes the actual star fighter game appear
    }

    public static void main(String args[]) {
        @SuppressWarnings("unused")
        Main run = new Main();
    }
}