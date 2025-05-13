import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {
	private int speed;
	private Image image;

	public Ship() {
		// default constructor with all default values init
		this(0, 0, 50, 50, 0);
	}

	public Ship(int x, int y) {
		// constructor with position only (default size and speed)
		this(x, y, 50, 50, 0);
	}

	public Ship(int x, int y, int s) {
		// constructor with position and speed (default size)
		this(x, y, 50, 50, s);
	}

	public Ship(int x, int y, int w, int h, int s) {
		super(x, y, w, h); //calls MovingThing parent constructor
		// this is technecally the real constructor in a way
		this.speed = s;
		// load ship image from resources
		try {
			URL url = getClass().getResource("ship.jpg");
			image = ImageIO.read(url);
		} catch (Exception e) {
			System.err.println("Error: ship.jpg not found");
		}
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	// these bounds work the best idk why just mass tested a bunch of stuff
	public void move(String direction) {
		if (direction.equals("LEFT") && getX() >= 0) {
			setX(getX() - speed);
		} else if (direction.equals("RIGHT") && getX() <= 750) {
			setX(getX() + speed);
		} else if (direction.equals("UP") && getY() >= 0) {
			setY(getY() - speed);
		} else if (direction.equals("DOWN") && getY() <= 520) {
			setY(getY() + speed);
		}
	}

	public void draw(Graphics window) {
		window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

	public String toString() {
		return super.toString() + " " + getSpeed();
	}
}