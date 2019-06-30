package info.projekt.jonas.storage;

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
