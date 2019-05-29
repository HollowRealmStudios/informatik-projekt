package info.projekt.jonas;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.rooms.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonas
 * Registry used to register new rooms, dwellers and items
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
    public static void registerDweller(Dweller dweller) {
        DWELLERS.put(dweller.completeName, dweller);
    }

    /**
     * use this to register a new item, by passing in an object of the desired item to be registered
     *
     * @param item the dweller to be registered
     * @see Item
     */
    public static void registerItem(Item item) {
        ITEMS.put(item.name, item);
    }

    /**
     * use this to register a new dweller, by passing in an object of the desired dweller to be registered
     *
     * @param room the Room to be registered
     * @see Room
     */
    public static void registerRoom(Room room) {
        ROOMS.put(room.name, room);
    }

    /**
     * Return a room by its name
     *
     * @param name the name of the room
     * @return the room with the name specified
     * @see Room
     */
    public static Room getRoom(String name) {
        return ROOMS.get(name);
    }

    /**
     * Return an Item by its name
     *
     * @param name the name of the Item
     * @return the Item with the name specified
     * @see Item
     */
    public static Item getItem(String name) {
        return ITEMS.get(name);
    }

    /**
     * Return a Dweller by its name
     *
     * @param name the name of the Dweller
     * @return the Dweller with the name specified
     * @see Dweller
     */
    public static Dweller getDweller(String name) {
        return DWELLERS.get(name);
    }

    public static String allToString() {
        StringBuilder b = new StringBuilder();
        for (Map.Entry<String, Room> i : ROOMS.entrySet()) {
            b.append(i.getValue().toString()).append("\n");
        }
        for (Map.Entry<String, Item> i : ITEMS.entrySet()) {
            b.append(i.getValue().toString()).append("\n");
        }
        for (Map.Entry<String, Dweller> i : DWELLERS.entrySet()) {
            b.append(i.getValue().toString()).append("\n");
        }
        return b.toString();
    }
}
