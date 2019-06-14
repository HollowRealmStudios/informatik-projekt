package info.projekt.jonas.storage;

import com.badlogic.gdx.graphics.Texture;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import info.projekt.jonas.Registry;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.rooms.Room;

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
}
