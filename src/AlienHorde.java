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

		for (int i = 0; i < size; i++) {
			if (x >= 775) {
				x = 25;
				y += 75;
			}
			add(new Alien(x, y, 1));
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

	public int removeDeadOnes(List<Ammo> shots) {
        int count = 0;
        if (shots.size() == 0 || aliens.size() == 0)
            return 0;
        for (int i = shots.size() - 1; i >= 0; i--) {
            Ammo am = shots.get(i);
            for (int j = aliens.size() - 1; j >= 0; j--) {
                Alien al = aliens.get(j);
                if (am.getX() - al.getX() <= 30 && am.getX() - al.getX() > -1 && am.getY() - al.getY() <= 30 && am.getY() - al.getY() > -1) {
                    aliens.remove(j);
                    shots.remove(i);
                    count++;
                    break;
                }
            }
        }
        return count;
    }

	public String toString() {
		return "" + aliens;
	}
}