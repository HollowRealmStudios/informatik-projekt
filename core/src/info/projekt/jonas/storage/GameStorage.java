package info.projekt.jonas.storage;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.items.CraftingComponent;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.InfoCenter;
import info.projekt.jonas.util.LimitedArrayList;
import info.projekt.jonas.util.LimitedHashMap;
import info.projekt.jonas.util.LimitedInt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jonas
 */
public class GameStorage implements Serializable {

	private final Room[][] ROOMS = new Room[5][50];

	public final LimitedArrayList<CraftingComponent> COMPONENTS = new LimitedArrayList<>(InfoCenter.componentCapacity);

	public final LimitedArrayList<Item> ITEMS = new LimitedArrayList<>(InfoCenter.itemCapacity);

	private final ArrayList<Dweller> DWELLERS = new ArrayList<>();

	public final LimitedInt currency = new LimitedInt(0, 1000000, false);

	public final LimitedInt food = new LimitedInt(0, 1000, false);

	public final LimitedInt energy = new LimitedInt(0, 1000, false);

	public final LimitedInt water = new LimitedInt(0, 1000, false);

	public void addDweller(Dweller dweller) {
		DWELLERS.add(dweller);
	}

	public ArrayList<Dweller> getDwellers() {
		return DWELLERS;
	}

	public Room[][] getRooms() {
		return ROOMS;
	}

	public void setRoom(Room room, int x, int y) {
		ROOMS[x][y] = room;
	}
}
