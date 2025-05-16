import java.awt.Graphics;

public abstract class MovingThing implements Moveable {
	int x;
	int y;
	int width;
	int height;

	public MovingThing() {
		this(10, 10, 10, 10);
	}

	public MovingThing(int x, int y) {
		this(x, y, 10, 10);
	}

	public MovingThing(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	// class cannot have objects instantiated from it, but something other classes can extend

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// do not change code below this line
	public abstract void move(String direction);

	public abstract void draw(Graphics window);

	public String toString() {
		return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
	}
}