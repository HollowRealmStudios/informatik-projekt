package info.projekt.jonas;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.rooms.Room;

import java.util.HashMap;

/**
 * Registry used to register new things, including, but not only, rooms, dwellers and items
 */
public class Registry {

    private static final HashMap<String, Room> ROOMS = new HashMap<>();
    private static final HashMap<String, Item> ITEMS = new HashMap<>();
    private static final HashMap<String, Dweller> DWELLERS = new HashMap<>();

    /**
     * use this to register a new dweller, by passing in an object of the desired dweller to be registered
     *
     * @param dweller the dweller to be registered
     * @see Dweller
     */
    public static void registerDweller(String name, Dweller dweller) {
        DWELLERS.put(name, dweller);
    }

    /**
     * use this to register a new item, by passing in an object of the desired item to be registered
     *
     * @param item the dweller to be registered
     * @see Item
     */
    public static void registerItem(String name, Item item) {
        ITEMS.put(name, item);
    }

    public static void regsterRoom(String name, Room room) {
        ROOMS.put(name, room);
    }
}
