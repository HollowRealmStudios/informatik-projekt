package info.projekt.jonas;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.items.Item;

import java.util.ArrayList;

/**
 * Registry used to register new things, including, but not only, rooms, dwellers and items
 */
public class Registry {

	private static final ArrayList<Item> ITEMS = new ArrayList<Item>();
	private static final ArrayList<Dweller> DWELLERS = new ArrayList<>();

	/**
	 * use this to register a new dweller, by passing in an object of the desired dweller to be registered
	 *
	 * @param dweller the dweller to be registered
	 * @see Dweller
	 */
	public static void registerDweller(Dweller dweller) {
		DWELLERS.add(dweller);
	}

	/**
	 * use this to register a new item, by passing in an object of the desired item to be registered
	 *
	 * @param item the dweller to be registered
	 * @see Item
	 */
	public static void registerItem(Item item) {
		ITEMS.add(item);
	}
}
