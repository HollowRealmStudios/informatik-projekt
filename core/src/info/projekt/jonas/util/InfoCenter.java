package info.projekt.jonas.util;

import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.rooms.StorageRoom;

import static info.projekt.InfoProjekt.GAME_STORAGE;

/**
 * @author Jonas
 */
public class InfoCenter {

	public static int storageCapacity;

	public static void recalculate() {
		storageCapacity = 0;
		for (Room[] rooms : GAME_STORAGE.getRooms()) {
			for (Room room : rooms) {
				if (room != null) {
					if (room instanceof StorageRoom) storageCapacity += 5 * room.getLevel();
				}
			}
		}
	}


}
