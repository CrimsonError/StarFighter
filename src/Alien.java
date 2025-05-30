import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {
	private int speed;
	private Image image;

	public Alien() {
		this(0, 0, 30, 30, 0);
	}

	public Alien(int x, int y) {
		this(x, y, 30, 30, 0);
	}

	public Alien(int x, int y, int s) {
		this(x, y, 30, 30, s);
	}

	/*
	 * this inherits from MovingThing,
	 * where you need to pass in the parent constructor
	 */

	public Alien(int x, int y, int w, int h, int s) {
		super(x, y, w, h);
		speed = s;
		try {
			URL url = getClass().getResource("alien.JPG");
			image = ImageIO.read(url);
		} catch (Exception e) {
			System.err.println("Error: no alien.jpg found!");
		}
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	public void move(String direction) {
		if (getX() <= 0 || getX() >= 800) { // if the alien either hits the left or right side of the screen
			setY(getY() + 40); // move down 40 pixels
			speed = -speed; // reverse the alien's direction
		}
		setX(getX() + speed); // then move the alien in the direction its going (from speed = -speed)
	}

	public void draw(Graphics window) {
		move("DOWN");
		window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
	}

	public String toString() {
		return super.toString() + getSpeed();
	}
}