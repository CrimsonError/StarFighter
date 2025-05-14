import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
	private List<Alien> aliens;

	public AlienHorde(int size) {
		int x = 25;
		int y = 50;
		aliens = new ArrayList<Alien>();

		// Create a grid of aliens, if the alien hits the right side of the screen
		// move down 75 px and start the alien again on the x axis at 25 px
		for (int i = 0; i < size; i++) {
			if (x >= 775) {
				x = 25;
				y += 75;
			}
			add(new Alien(x, y, 1)); // add the new alien (speed is 1)
			x += 75; // move to the right 75 px for the next alien in the list, so forth
		}
	}

	public void add(Alien al) {
		aliens.add(al);
	}

	public void drawEmAll(Graphics window) {
		for (Alien al : aliens) {
			al.draw(window);
		}
	}

	public void moveEmAll() {
		for (Alien al : aliens) {
			al.move("idk");
		}
	}

	public void removeAll() {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			Alien al = aliens.get(i);
			aliens.remove(al);
		}
	}

	public boolean hitPlayer(Ship p) {
		// cehck and see if aliens hit the ship
		for (int i = aliens.size() - 1; i >= 0; i--) {
			Alien al = aliens.get(i);
			if (al.getX() - p.getX() <= 30 && al.getX() - p.getX() > -1 && al.getY() - p.getY() <= 30
					&& al.getY() - p.getY() > -1) {
				return true;
			}
		}
		return false;
	}

	// if an ammo object has collided with an alien object, remove both
	public int removeDeadOnes(List<Ammo> shots) { 

	}

	// le bounds:
	// if the horiz distance between ammo and alien is <= 30 && not to the left of
	// the alien
	// if the vert distance between ammo and alien is <= 30 && ammo is not above the
	// alien
	// subtracting gives the distance between the two objects

	public List<Alien> getAliens() {
		return aliens;
	}

	public String toString() {
		return "" + aliens;
	}
}