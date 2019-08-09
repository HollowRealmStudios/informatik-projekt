package info.projekt.jonas.storage;

import info.projekt.jonas.rooms.EngineRoom;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.logging.Logger;

/**
 * @author Jonas
 */
public class StorageHandler {

	private static final Logger logger = Logger.getLogger("Storage logger");
	private static final String FILE = "Storage.dat";

	@NotNull
	public static GameStorage loadGame() {
		try {
			logger.info("Loading game...");
			GameStorage storage = (GameStorage) new ObjectInputStream(new FileInputStream(FILE)).readObject();
			if(storage.getRoomAt(0, 0) == null) {
				for(int x = 0; x < 5; x++) {
					for(int y = 0; y < 15; y++) {
						storage.setRoomAt(new EngineRoom(), x, y);
					}
				}
			}
			logger.info("Done loading game...");
			return storage;
		} catch (IOException | ClassNotFoundException e) {
			GameStorage storage = new GameStorage();
			for(int x = 0; x < 5; x++) {
				for(int y = 0; y < 15; y++) {
					storage.setRoomAt(new EngineRoom(), x, y);
				}
			}
			return storage;
		}
	}

	public static void saveGame(GameStorage storage) {
		try {
			logger.info("Saving game...");
			new ObjectOutputStream(new FileOutputStream(FILE)).writeObject(storage);
			logger.info("Done saving game...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
