package info.projekt.jonas.storage;

import java.io.*;

/**
 * @author Jonas
 */
public class StorageHandler {

	/**
	 * The name of the file to store into
	 */
	public static final String FILE = "Storage.dat";

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
}
