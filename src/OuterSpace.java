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
import javax.imageio.ImageIO;

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
	private int hit; // hit counter for flicker effect
	private BufferedImage heartImage; // imported life counter

	public OuterSpace() {
		setBackground(Color.black);

		keys = new boolean[5];
		ship = new Ship(310, 450, 5); // new ship object at 310, 450 with speed of 5
		shots = new Bullets();
		countdown = 0;
		lives = 3;
		hit = 0;

		// import the heart image for the life counter
		try {
			heartImage = ImageIO.read(getClass().getResource("pixelheart.png"));
		} catch (Exception e) {
			heartImage = null;
			System.err.println("Error: pixelheart.png not found!");
		}

		this.addKeyListener(this);
		new Thread(this).start();
		setVisible(true);
	}

	public OuterSpace(String d) {
		this(); // call the default constructor to initalize the game
		difficulty = d; // initalize difficulty

		if (difficulty.equals("Easy")) {
			horde = new AlienHorde(10, 1); // 10 aliens, speed 1
		} else if (difficulty.equals("Medium")) {
			horde = new AlienHorde(20, 3); // 20 aliens, speed 3
		} else if (difficulty.equals("Hard")) {
			horde = new AlienHorde(30, 5); // 30 aliens, speed 5
		}
	}

	public void update(Graphics window) {
		paint(window);
	}

	public void paint(Graphics window) {
		// set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D) window;

		/*
		 * take a snap shot of the current screen and save it as an image
		 * that is the exact same width and height as the current screen
		 * because the screen is constantly changing
		 */

		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));

		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, getWidth(), getHeight());

		if (lives <= 0) {
			horde.removeAll(); // kill all aliens
			graphToBack.setColor(Color.RED); // change the background color to red
			graphToBack.drawString("GAME OVER", 100, 300); // draw the game over message
		}

		/*
		 * the flicker effect, ship blinks twice when hit
		 * blink off for 20 frames, on for 20 frames (%), and repeat that twice
		 * normal starting condition will have the else happen, drawing it normally
		 * total of 80 for 4 on/off cycles of 20
		 */

		if (hit > 0) {

			if ((hit / 20) % 2 == 0) {
				ship.draw(graphToBack);
			}
			hit--;
		} else {
			ship.draw(graphToBack);
			if (horde.hitPlayer(ship)) {
				lives--;
				hit = 80;
			}
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

		if (keys[4] && countdown == 0) {
			shots.add(new Ammo(ship.getX() + 20, ship.getY(), 5)); // getX() + 20 to make the bullet to appear centered,
																	// speed to 5
			countdown = 35;
			keys[4] = false;
		}
		if (countdown > 0) {
			countdown--;
		}

		// draw hearts for lives in the top left corner
		for (int i = 0; i < lives; i++) {
			if (heartImage != null) { // sometimes the image may not load
				if (i < lives) {
					graphToBack.drawImage(heartImage, 10 + i * 40, 10, 32, 32, null); // normally draw it in the corner
				}
			}

			else {
				System.out.println("Error: failed to load heartImage");
			}
		}

		// draw all the objects so you can see them on the graphics
		shots.drawEmAll(graphToBack);
		horde.drawEmAll(graphToBack);
		shots.moveEmAll();
		horde.removeDeadOnes(shots.getList());
		twoDGraph.drawImage(back, null, 0, 0);
		back = null;
	}

	public void keyPressed(KeyEvent e) {
		if (lives <= 0)
			return; // so the ship cant move after the game ends
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
		if (lives <= 0)
			return; // so the ship cant move after end game
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
		// method needs to be implemented because class implements KeyListner
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