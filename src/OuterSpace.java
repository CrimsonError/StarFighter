// fix drawing issues for bullets
// rewrite all collision
// add graphics for lives

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Image;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

	private AlienHorde horde;
	private Bullets shots;
	private Ship ship;
	// private Alien alienOne;
	// private Alien alienTwo;
	private boolean[] keys;
	private BufferedImage back;
	private String difficulty;
	private int countdown; // ammo cooldown timer
	private int lives;
	private int hit;
	private Image heartImage;

	public OuterSpace() {
		setBackground(Color.black);

		keys = new boolean[5];
		ship = new Ship(310, 450, 5);
		shots = new Bullets();
		countdown = 0;
		lives = 3;
		hit = 0;

		URL url = getClass().getResource("pixelheart.png");
		try {
			heartImage = ImageIO.read(url);
		} catch (IOException e) {
			System.err.println("Error: pixelheart.png not found!");
		}

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

		

		if (lives <= 0) {
			horde.removeAll();
			graphToBack.setColor(Color.RED);
			graphToBack.drawString("GAME OVER", 100, 300);

		}

		if (hit > 0) { // checks if the ship has recently been hit and is in its flickering phase.
			if (hit % 2 == 0) {
				// draws the ship every other frame while it has been reccently hit, creating
				// the flicker effect
				// decreasing ever frame until it stops
				ship.draw(graphToBack); // draws the ship visable on some frames and not on others
			}
			hit--; // decreases hit, ill gradually stop until hit reaches 0
		} else { // runs when the ship is in its normal state not greater than 0
			if (horde.hitPlayer(ship)) { // if alien collide with ship
				lives -= 1;
				hit = 200; // sets it to 400, triggering the fikering effect again cuz hit > 0 to indicate
							// dmg
			}
			ship.draw(graphToBack); // draws the ship normally when it's not flickering
		}

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
			// and speed of 5
			// paint is called every frame, so countdown will decrement every 35 frames
			shots.add(new Ammo(ship.getX() + 20, ship.getY(), 5));
			countdown = 35;
			keys[4] = false;
		}
		if (countdown > 0) {
			countdown--;
		}

		// draw all the objects so you can see them on the graphics

		shots.drawEmAll(graphToBack);
		horde.drawEmAll(graphToBack);

		shots.moveEmAll();
		horde.removeDeadOnes(shots.getList());

		// ship.draw(graphToBack);
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
			System.out.println("Error while running!");
		}
	}
}