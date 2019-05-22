package info.projekt.jonas.storage;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.io.*;

/**
 * @author Jonas
 */
public class StorageHandler {

    /**
     * The name of the file to store into
     */
    private static final String FILE = "Storage.dat";

    /**
     * loads an instance of the game storage class, containing everything saved when left off
     *
     * @return an instance of the game storage class, containing everything saved when left off
     */
    public static GameStorage loadGame() throws IOException, ClassNotFoundException {
        return (GameStorage) new ObjectInputStream(new FileInputStream(FILE)).readObject();
    }

    /**
     * saved an instance of the game storage class, containing everything in passed in instance
     *
     * @param storage the instance to save to the file
     */
    public static void saveGame(GameStorage storage) throws IOException {
        new ObjectOutputStream(new FileOutputStream(FILE)).writeObject(storage);
    }

    public static void registerWeapons() throws IOException {
        JsonObject in = JsonObject.readFrom(new FileReader("Weapons.json"));
        System.out.println(in.get("weapons").asArray().get(0).toString());
    }

    public static void registerArmors() throws IOException {
        JsonObject in = JsonObject.readFrom(new FileReader("Armor.json"));
        for (JsonValue value : in.get("Armors").asArray()) {
            System.out.println(value.asString());
        }
    }
}
