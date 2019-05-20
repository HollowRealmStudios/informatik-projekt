package info.projekt.jonas;

import info.projekt.jonas.items.Item;
import info.projekt.jonas.rooms.Room;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

/**
 * Registry used to register new things, including, but not only, rooms, dwellers and items
 * */
public class Registry {

	private static final ArrayList<Class<? extends Item>> ITEMS = new ArrayList<>();
	private static final ArrayList<Class<? extends Room>> ROOMS = new ArrayList<>();
	private static final ArrayList<Class<? extends Dweller>> DWELLERS = new ArrayList<>();

	/**
	 * use this to register a new room, by passing in an object of the desired room to be registered
	 * @param room the room to be registered
	 * @see Room
	 * */
	public static void registerRoom(Room room) {
		if(ROOMS.contains(room.getClass())) throw new IllegalArgumentException("Room " + room.getClass().getName() + " already registered!");
		ROOMS.add(room.getClass());
	}

	/**
	 * use this to register a new dweller, by passing in an object of the desired dweller to be registered
	 * @param dweller the dweller to be registered
	 * @see Dweller
	 *  */
	public static void registerDweller(Dweller dweller) {
		if(DWELLERS.contains(dweller.getClass())) throw new IllegalArgumentException("Dweller " + dweller.getClass().getName() + " already registered!");
		DWELLERS.add(dweller.getClass());
	}

	/**
	 * use this to register a new item, by passing in an object of the desired item to be registered
	 * @param item the dweller to be registered
	 * @see Item
	 *  */
	public static void registerItem(Item item) {
		if(ITEMS.contains(item.getClass())) throw new IllegalArgumentException("Item " + item.getClass().getName() + " already registered!");
		ITEMS.add(item.getClass());
	}
}
