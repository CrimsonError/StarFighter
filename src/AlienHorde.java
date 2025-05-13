import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
	private List<Alien> aliens;

	public AlienHorde(int size) {
		int x = 25;
		int y = 50;
		aliens = new ArrayList<Alien>();

		for (int i = 0; i < size + 2; i++) {
			aliens.add(new Alien(x, y, 30, 30, 1));
			x += 75;
			// fix this bounds issue to consider the window length
			if (x > 775) {
				y += 75;
				x = 25;
			}
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

	/*
	 * public void removeDeadOnes(List<Ammo> shots)
	 * {
	 * //Part 3
	 * //for every shot in the list
	 * //check if you've hit any alien in the list
	 * //(do the coordinates of the shot fall between the boundarises of the alien)
	 * //if they do then remove the alien and the shot
	 * //make sure you break out of the loop if this happens
	 * }
	 */

	public String toString() {
		return "" + aliens;
	}
}