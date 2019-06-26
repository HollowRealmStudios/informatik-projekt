package info.projekt.jonas;

import com.badlogic.gdx.graphics.Texture;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.rooms.Room;
import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Jonas
 * Registry used to register new rooms, dwellers and items
 */
public class Registry {

    private static final HashMap<String, Room> ROOMS = new HashMap<>();
    private static final HashMap<String, Item> ITEMS = new HashMap<>();

    public static void registerWeapons() throws IOException {

        JsonArray in = JsonObject.readFrom(new FileReader("Weapons.json")).get("weapons").asArray();
        for (JsonValue object : in.asArray()) {
            JsonObject obj = object.asObject();
            ITEMS.put(obj.get("name").asString(), new WeaponItem(new Texture(obj.get("texture").asString()), obj.get("name").asString(), obj.get("damage").asInt(), obj.get("deviation").asInt()));
        }
    }

    public static void registerArmors() throws IOException {
        JsonArray in = JsonObject.readFrom(new FileReader("Armor.json")).get("armors").asArray();
        for (JsonValue object : in.asArray()) {
            JsonObject obj = object.asObject();
            ITEMS.put(obj.get("name").asString(), new ArmorItem(new Texture(obj.get("texture").asString()), obj.get("name").asString(), obj.get("protection").asInt(), obj.get("deviation").asInt()));
        }
    }

    public static HashMap<String, Item> getITEMS() {
        return ITEMS;
    }

    public static void registerRooms() {
        new Reflections("info.projekt.jonas.rooms").getSubTypesOf(Room.class).forEach((Class c) -> {
            try {
                ROOMS.put(c.getSimpleName(), (Room) c.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Return a room by its name
     *
     * @param name the name of the room
     * @return the room with the name specified
     * @see Room
     */
    public static Room getRoom(String name) {
        try {
            return ROOMS.get(name).getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return an Item by its name
     *
     * @param name the name of the Item
     * @return the Item with the name specified
     * @see Item
     */
    public static Item getItem(String name) {

        if (!ITEMS.containsKey(name)) throw new IllegalArgumentException(name + " doesn't exist");
        return ITEMS.get(name);
    }

    public static String allToString() {
        StringBuilder b = new StringBuilder();
        ROOMS.forEach((key, value) -> b.append(value.getClass().getSimpleName()).append("\n"));
        ITEMS.forEach((key, value) -> b.append(value.toString()).append("\n"));
        return b.toString();
    }
}
