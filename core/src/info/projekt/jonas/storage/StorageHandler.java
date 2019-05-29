package info.projekt.jonas.storage;

import com.badlogic.gdx.graphics.Texture;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import info.projekt.jonas.Registry;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.WeaponItem;

import java.io.*;

/**
 * @author Jonas
 */
public class StorageHandler {

    private static final String FILE = "Storage.dat";

    public static GameStorage loadGame() throws IOException, ClassNotFoundException {
        return (GameStorage) new ObjectInputStream(new FileInputStream(FILE)).readObject();
    }

    public static void saveGame(GameStorage storage) throws IOException {
        new ObjectOutputStream(new FileOutputStream(FILE)).writeObject(storage);
    }

    public static void registerWeapons() throws IOException {
        try {
            JsonArray in = JsonObject.readFrom(new FileReader("Weapons.json")).get("weapons").asArray();
            for (JsonValue object : in.asArray()) {
                JsonObject obj = object.asObject();
                Registry.registerItem(new WeaponItem(new Texture(obj.get("texture").asString()), obj.get("name").asString(), obj.get("damage").asInt(), obj.get("deviation").asInt()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerArmors() throws IOException {
        try {
            JsonArray in = JsonObject.readFrom(new FileReader("Armor.json")).get("armors").asArray();
            for (JsonValue object : in.asArray()) {
                JsonObject obj = object.asObject();
                Registry.registerItem(new ArmorItem(new Texture(obj.get("texture").asString()), obj.get("name").asString(), obj.get("protection").asInt(), obj.get("deviation").asInt()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
