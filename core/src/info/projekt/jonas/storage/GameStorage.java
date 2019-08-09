package info.projekt.jonas.storage;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.items.CraftingComponent;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.LimitedArrayList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Jonas
 */
public class GameStorage implements Serializable {

	public static final transient GameStorage INSTANCE = StorageHandler.loadGame();

	private final Room[][] rooms = new Room[5][15];

	public final LimitedArrayList<CraftingComponent> COMPONENTS = new LimitedArrayList<>(0);

	public final LimitedArrayList<Item> ITEMS = new LimitedArrayList<>(0);

	private final ArrayList<Dweller> DWELLERS = new ArrayList<>();

	public int currency = 0;

	public final int food = 0;

	public final int energy = 0;

	public final int water = 0;

	public ArrayList<Dweller> getDwellers() {
		return DWELLERS;
	}

	public Room getRoomAt(int x, int y) {
		return rooms[x][y];
	}

	public void setRoomAt(Room room, int x, int y) {
		rooms[x][y] = room;
	}
}
