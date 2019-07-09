package info.projekt.jonas.util;

import com.badlogic.gdx.Game;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.rooms.StorageRoom;
import org.jetbrains.annotations.NotNull;

import static info.projekt.InfoProjekt.GAME_STORAGE;
import static info.projekt.InfoProjekt.renderer;

/**
 * @author Jonas
 */
public class InfoCenter {

	public static int itemCapacity;
	public static int componentCapacity;

	public static void recalculate() {
		itemCapacity = 5;
		componentCapacity = 5;
		for (Room[] rooms : GAME_STORAGE.getRooms()) {
			for (Room room : rooms) {
				if (room != null) {
					if (room instanceof StorageRoom) {
						itemCapacity += 5 * room.getLevel();
						componentCapacity += 10 * room.getLevel();
					}
				}
			}
		}
	}

	@NotNull
	public static String debug() {
		recalculate();
		return "Item Capacity: " + itemCapacity + ", Component Capacity: " + componentCapacity;
	}

	public static boolean isComponentSpace() {
		return GAME_STORAGE.COMPONENTS.isSpace();
	}

	public static boolean isItemSpace() {
		return GAME_STORAGE.ITEMS.isSpace();
	}
}
