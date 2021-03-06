package info.projekt.jonas.storage;

import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.item.ArmorItem;
import info.projekt.jonas.item.CraftingComponent;
import info.projekt.jonas.item.WeaponItem;
import info.projekt.jonas.room.Room;
import info.projekt.jonas.util.LimitedArrayList;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Jonas
 */
public class GameStorage implements Serializable {

	public static transient GameStorage INSTANCE;

	public Room[][] rooms = new Room[5][15];

	public final LimitedArrayList<CraftingComponent> components = new LimitedArrayList<>(1000);

	public final LimitedArrayList<WeaponItem> weapons = new LimitedArrayList<>(1000);
	public final LimitedArrayList<ArmorItem> armors = new LimitedArrayList<>(1000);

	private final ArrayList<Dweller> dwellers = new ArrayList<>();

	public int currency = 0;

	public int food = 0;

	public int energy = 0;

	public int water = 0;

	public int meds = 0;

	public void addDweller(Dweller dweller) {
		dwellers.add(dweller);
	}

	public ArrayList<Dweller> getDwellers() {
		return dwellers;
	}

	@Nullable
	public Room getRoomAt(int x, int y) {
		return rooms[x][y];
	}

	public void setRoomAt(Room room, int x, int y) {
		rooms[x][y] = room;
	}
}
