import java.awt.Color;
import java.awt.Graphics;

public class Ammo extends MovingThing {
	private int speed;

	public Ammo() {
		this(10, 10, 10, 10, 0);
	}

	public Ammo(int x, int y) {
		this(x, y, 10, 10, 0);
	}

	public Ammo(int x, int y, int s) {
		this(x, y, 10, 10, s);
	}

	public Ammo(int x, int y, int w, int h, int s) {
		super(x, y, w, h);
		speed = s;
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	/*
	 * Makes sure the left side of the ship is to the left of the right side of the
	 * alien
	 * Right side of the ship is to the right of the left side of the alien
	 * Top side of the ship is above the bottom side of the alien
	 * Bottom side of the ship is below the top side of the alien
	 */

	public boolean hitAlien(Alien al) {

		int ax = al.getX();
		int ay = al.getY();
		int awid = al.getWidth();
		int ahei = al.getHeight();
		int bx = getX();
		int by = getY();
		int bwid = getWidth();
		int bhei = getHeight();

		return bx < ax + awid && bx + bwid > ax && by < ay + ahei && by + bhei > ay;
	}

	public void draw(Graphics window) {
		window.setColor(Color.YELLOW);
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public void move(String direction) {
		setY(getY() - speed);
	}

	public String toString() {
		return super.toString() + getSpeed();
	}
}