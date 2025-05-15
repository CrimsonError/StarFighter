import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Bullets {
	private List<Ammo> ammo;

	public Bullets() {
		ammo = new ArrayList<Ammo>();
	}

	public void add(Ammo al) {
		ammo.add(al);
	}

	public void drawEmAll(Graphics window) {
		for (Ammo a : ammo) {
			a.draw(window);
		}
	}

	public void moveEmAll() {
		for (Ammo a : ammo) {
			a.move("idk");
		}
	}

	// removes all the ammo objects that have gone off the screen
	public void cleanEmUp() { 
		for (int i = ammo.size() - 1; i >= 0; i--) {
			if (ammo.get(i).getY() <= 0) { // y indicates the height of the ammo object, so if it is less than 0, it has gone off the screen
				ammo.remove(i);
			}
		}
	}

	public List<Ammo> getList() {
		return ammo;
	}

	public String toString() {
		return "" + ammo;
	}
}
