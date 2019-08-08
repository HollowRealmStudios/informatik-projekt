package info.projekt.jonas.storage;

import java.io.*;

/**
 * @author Jonas
 */
public class StorageHandler {

	private static final String FILE = "Storage.dat";

	public static GameStorage loadGame() {
		try {
			return (GameStorage) new ObjectInputStream(new FileInputStream(FILE)).readObject();
		} catch (IOException | ClassNotFoundException e) {
			return new GameStorage();
		}
	}

	public static void saveGame(GameStorage storage) {
		try {
			new ObjectOutputStream(new FileOutputStream(FILE)).writeObject(storage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
