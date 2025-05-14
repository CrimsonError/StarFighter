import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridLayout;

public class Main extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    //just a bunch of formatting for a popup menu on start (bc constructor), nothing special

    @SuppressWarnings("unused")
    public Main() {
        super("Star Fighter");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDialog popup = new JDialog(this, "Main Menu", true);
        popup.setSize(300, 200);
        popup.setResizable(false);
        popup.setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Select Difficulty to Start", JLabel.CENTER);
        popup.add(titleLabel);

        JButton easy = new JButton("Easy");
        easy.addActionListener(evt -> {
            runGame("Easy");
            popup.dispose(); 
        });
        popup.add(easy);

        JButton medium = new JButton("Medium");
        medium.addActionListener(evt -> {
            runGame("Medium");
            popup.dispose(); 
        });
        popup.add(medium);

        JButton hard = new JButton("Hard");
        hard.addActionListener(evt -> {
            runGame("Hard");
            popup.dispose(); 
        });
        popup.add(hard);
        popup.setVisible(true);
    }

    public void runGame(String difficulty) { // might be a better way but this i feel is the easiest
        OuterSpace theGame = new OuterSpace(difficulty); // calls the constructor of the OuterSpace class that has a param, which 
        // also calls the default constructor of OuterSpace, which sets up the game
        theGame.requestFocus();
        ((Component) theGame).setFocusable(true);
        getContentPane().add(theGame);
        setVisible(true);
    }

    public static void main(String args[]) {
        @SuppressWarnings("unused")
        Main run = new Main();
    }
}