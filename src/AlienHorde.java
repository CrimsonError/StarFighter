import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
	private List<Alien> aliens;

	public AlienHorde(int size) {
		int x = 25;
		int y = 50;
		aliens = new ArrayList<Alien>();

		for (int i = 0; i < size; i++) {
			if (x >= 800) {
				x = 25;
				y += 75;
			}
			add(new Alien(x, y, 1));
			x += 75;
		}
	}

	/*
	 * start the first alien at 25 px on the x axis
	 * start the first alien at 50 px on the y axis
	 * if the x coordinate is greater than 800 (off the screen)
	 * reset x to 25
	 * and move down 75 px on the y axis
	 * add that new alien at the specified position
	 * increment x by 75 px for the next alien, and repeat the loop
	 */

	public AlienHorde(int size, int speed) {
		int x = 25;
		int y = 50;
		aliens = new ArrayList<Alien>();

		for (int i = 0; i < size; i++) {
			if (x >= 800) {
				x = 25;
				y += 75;
			}
			add(new Alien(x, y, speed));
			x += 75;
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

	/*
	 * Makes sure the left side of the ship is to the left of the right side of the
	 * alien
	 * Right side of the ship is to the right of the left side of the alien
	 * Top side of the ship is above the bottom side of the alien
	 * Bottom side of the ship is below the top side of the alien
	 */

	public boolean hitPlayer(Ship p) {
		for (int i = 0; i < aliens.size(); i++) {
			int ax = aliens.get(i).getX();
			int ay = aliens.get(i).getY();
			int awid = aliens.get(i).getWidth();
			int ahei = aliens.get(i).getHeight();
			int bx = p.getX();
			int by = p.getY();
			int bwid = p.getWidth();
			int bhei = p.getHeight();

			if (bx < ax + awid && bx + bwid > ax && by < ay + ahei && by + bhei > ay) {
				return true;
			}
		}
		return false;
	}

	// checks if the ammo hit the alien
	public int removeDeadOnes(List<Ammo> shots) {
		for (int i = shots.size() - 1; i >= 0; i--) {
			for (int j = aliens.size() - 1; j >= 0; j--) {
				if (shots.get(i).hitAlien(aliens.get(j))) {
					aliens.remove(j);
					shots.remove(i);
					return 1;
				}
			}
		}
		return 0;
	}

	public List<Alien> getAliens() {
		return aliens;
	}

	public String toString() {
		return "" + aliens;
	}
}