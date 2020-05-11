package info.projekt.jonas.storage;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import info.projekt.jonas.item.ArmorItem;
import info.projekt.jonas.item.CraftingComponent;
import info.projekt.jonas.item.Item;
import info.projekt.jonas.item.WeaponItem;
import info.projekt.jonas.room.Room;
import info.projekt.jonas.util.Color;
import org.jetbrains.annotations.Contract;
import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @author Jonas
 */
public class Registry {

    private static final Logger logger = Logger.getLogger("Registry");
    private static final HashMap<String, Room> ROOMS = new HashMap<>();
    private static final HashMap<String, Item> ITEMS = new HashMap<>();
    private static final HashMap<String, CraftingComponent> COMPONENTS = new HashMap<>();

    public static void registerWeapons() throws IOException {
        JsonArray in = JsonObject.readFrom(new FileReader("Weapons.json")).get("weapons").asArray();
        for (JsonValue object : in.asArray()) {
            JsonObject obj = object.asObject();
            ITEMS.put(obj.get("name").asString(), new WeaponItem(obj.get("texture").asString(), obj.get("name").asString(), obj.get("damage").asInt(), obj.get("deviation").asInt()));
        }
        logger.info(Color.toColor("Registered " + ITEMS.size() + " items", Color.GREEN));
    }

    public static void registerArmors() throws IOException {
        JsonArray in = JsonObject.readFrom(new FileReader("Armor.json")).get("armors").asArray();
        for (JsonValue object : in.asArray()) {
            JsonObject obj = object.asObject();
            ITEMS.put(obj.get("name").asString(), new ArmorItem(obj.get("texture").asString(), obj.get("name").asString(), obj.get("protection").asInt(), obj.get("deviation").asInt()));
        }
    }

    public static void registerComponents() throws IOException {
        JsonArray in = JsonObject.readFrom(new FileReader("Components.json")).get("components").asArray();
        for (JsonValue object : in.asArray()) {
            JsonObject obj = object.asObject();
            COMPONENTS.put(obj.get("name").asString(), new CraftingComponent(obj.get("name").asString().replace(" ", "") + ".png", obj.get("name").asString(), obj.get("rarity").asInt()));
        }
        logger.info(Color.toColor("Registered " + COMPONENTS.size() + " components", Color.GREEN));
    }

    public static void registerRecipes() {
        System.err.println("Crafting isn't implemented");
    }

    @Contract(pure = true)
    public static HashMap<String, Item> getItems() {
        return ITEMS;
    }

    @SuppressWarnings("deprecation")
    public static void registerRooms() {
        new Reflections("info.projekt.jonas.room").getSubTypesOf(Room.class).forEach((Class<?> c) -> {
            try {
                ROOMS.put(c.getSimpleName(), (Room) c.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        logger.info(Color.toColor("Registered " + ROOMS.size() + " rooms", Color.GREEN));
    }

    @Contract(pure = true)
    public static HashMap<String, Room> getRooms() {
        return ROOMS;
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

    public static void debug() {
        StringBuilder b = new StringBuilder();
        b.append("------------------------------Items------------------------------\n");
        ITEMS.forEach((v, k) -> {
            b.append(k.toString());
            b.append("\n");
        });
        b.append("------------------------------Rooms------------------------------\n");
        ROOMS.forEach((v, k) -> {
            b.append(k.toString());
            b.append("\n");
        });
        b.append("----------------------------Components----------------------------\n");
        COMPONENTS.forEach((v, k) -> {
            b.append(k.toString());
            b.append("\n");
        });
        b.append("-----------------------------Recipes-------------------------------\n");
        logger.info(Color.toColor(b.toString(), Color.YELLOW));
    }

}
