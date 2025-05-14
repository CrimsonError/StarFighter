import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

	private AlienHorde horde;
	private Bullets shots;
	private Ship ship;
	// private Alien alienOne;
	// private Alien alienTwo;
	private boolean[] keys;
	private BufferedImage back;
	private String difficulty;
	private int countdown;

	public OuterSpace() {
		setBackground(Color.black);

		keys = new boolean[5];
		ship = new Ship(310, 450, 5);
		shots = new Bullets();
		countdown = 0;

		this.addKeyListener(this); // add the key listener to the canvas
		new Thread(this).start();

		setVisible(true);
	}

	public OuterSpace(String d) {
		this(); // call the default constructor to set up the game, else all the variables wont
				// be initalized
		difficulty = d;

		if (difficulty.equals("Easy")) {
			horde = new AlienHorde(10);
		} else if (difficulty.equals("Medium")) {
			horde = new AlienHorde(20);
		} else if (difficulty.equals("Hard")) {
			horde = new AlienHorde(30);
		}
	}

	public void update(Graphics window) {
		paint(window);
	}

	// the top part of the paint method is done for you
	public void paint(Graphics window) {
		// set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D) window;

		// take a snap shot of the current screen and save it as an image
		// that is the exact same width and height as the current screen
		// because the screen is constantly changing

		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));

		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, getWidth(), getHeight());

		if (keys[0]) {
			ship.move("LEFT");
		}
		if (keys[1]) {
			ship.move("RIGHT");
		}
		if (keys[2]) {
			ship.move("UP");
		}
		if (keys[3]) {
			ship.move("DOWN");
		}

		if (keys[4] && countdown == 0) { // adding 20 helps move the ammo pos so it appears to be fired from the center,
			// get y so the bullet starts at the same pos as the ship
			// and speed of 5 (obvious)
			shots.add(new Ammo(ship.getX() + 20, ship.getY(), 5));
			countdown = 50;
			keys[4] = false;
		}
		if (countdown > 0) {
			countdown--;
		}

		// drawing time yay

		horde.removeDeadOnes(shots.getList());
		shots.moveEmAll();
		ship.draw(graphToBack);
		horde.drawEmAll(graphToBack);
		shots.drawEmAll(graphToBack);
		twoDGraph.drawImage(back, null, 0, 0);
		back = null;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) {
		// no code needed here
		// method needs to be implemented
		// because class implements KeyListner
	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread();
				Thread.sleep(5);
				repaint();
			}
		} catch (Exception e) {
			System.out.println("bruh");
		}
	}
}