package info.projekt.jonas.storage;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;

import static info.projekt.InfoProjekt.GAME_STORAGE;

/**
 * @author Jonas
 */
public class GameStorage implements Serializable {

	/**
	 * The main 2D-Array of rooms
	 */
	private final Room[][] ROOMS = new Room[5][50];

	private final ArrayList<Dweller> DWELLERS = new ArrayList<>();

	public int currency;

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

	public void debug() {
		for (int x = 0; x < GAME_STORAGE.getRooms().length; x++) {
			for (int y = 0; y < GAME_STORAGE.getRooms()[0].length; y++) {
				GAME_STORAGE.ROOMS[x][y] = new Room("EngineRoom", "Power");
			}
		}
	}
}
