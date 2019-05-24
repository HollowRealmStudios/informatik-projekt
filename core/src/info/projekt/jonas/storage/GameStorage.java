package info.projekt.jonas.storage;

import info.projekt.jonas.rooms.DebugRoom;
import info.projekt.jonas.rooms.ElectricityRoom;
import info.projekt.jonas.rooms.EmptyRoom;
import info.projekt.jonas.rooms.Room;

import java.io.Serializable;

/**
 * @author Jonas
 */
public class GameStorage implements Serializable {

	/**
	 * The main 2D-Array of rooms
	 */
	private final Room[][] ROOMS = new Room[5][50];

	public GameStorage() {
		for (int x = 0; x < ROOMS.length; x++) {
			for (int y = 0; y < ROOMS[0].length; y++) {
				ROOMS[x][y] = new EmptyRoom();
			}
		}
	}

	/**
	 * @return the 2D-Array of rooms
	 */
	public Room[][] getRooms() {
		return ROOMS;
	}

	/**
	 * Set a specific room at a specific position
	 *
	 * @param room the room to set
	 * @param x    the x position
	 * @param y    the y position
	 */
	public void setRoom(Room room, int x, int y) {
		ROOMS[x][y] = room;
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
				ROOMS[x][y] = new ElectricityRoom();
			}
		}
	}
}
