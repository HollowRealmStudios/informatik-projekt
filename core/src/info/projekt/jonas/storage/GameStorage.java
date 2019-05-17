package info.projekt.jonas.storage;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.rooms.DebugRoom;
import info.projekt.jonas.rooms.Room;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Jonas
 */
public class GameStorage implements Serializable {

	/**
	 * The main 2D-Array of rooms
	 */
	private final Room[][] ROOMS = new Room[5][50];

	/**
	 * @return the 2D-Array of rooms
	 */
	public Room[][] getRooms() {
		return ROOMS;
	}

	/**
	 * Fills the entire array with debug rooms
	 *
	 * @see DebugRoom
	 * @deprecated
	 */
	@Deprecated
	public void debug() {
		for (int x = 0; x < ROOMS.length; x++) {
			for (int y = 0; y < ROOMS[0].length; y++) {
				if (new Random().nextBoolean())
					ROOMS[x][y] = new DebugRoom(0, 0, new Texture("baracke____mega fake___ pls change.png"));
				else ROOMS[x][y] = new DebugRoom(0, 0, new Texture("küche___mega fake___ pls change.png"));
			}
		}
	}
}
