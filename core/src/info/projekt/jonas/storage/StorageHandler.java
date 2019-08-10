package info.projekt.jonas.storage;

import info.projekt.jonas.room.*;
import info.projekt.jonas.util.Configuration;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.logging.Logger;

import static info.projekt.jonas.util.Color.*;

/**
 * @author Jonas
 */
public class StorageHandler {

	private static final Logger logger = Logger.getLogger("Storage logger");
	private static final String FILE = "Storage.dat";

	@NotNull
	public static GameStorage loadGame() {
		try {
			logger.info(toColor("Loading game...", YELLOW));
			GameStorage storage = (GameStorage) new ObjectInputStream(new FileInputStream(FILE)).readObject();
			logger.info(toColor("Done loading game...", GREEN));
			return storage;
		} catch (IOException | ClassNotFoundException e) {
			logger.info(RED + "Error loading world. Generating new one..." + RESET);
			GameStorage storage = new GameStorage();
			storage.rooms = newMap();
			logger.info(toColor("Done generating world...", GREEN));
			return storage;
		}
	}

	public static void saveGame(GameStorage storage) {
		try {
			logger.info(toColor("Saving game...", YELLOW));
			new ObjectOutputStream(new FileOutputStream(FILE)).writeObject(storage);
			logger.info(toColor("Done saving game...", GREEN));
		} catch (IOException e) {
			logger.info(toColor("Error saving game...", RED));
		}
	}

	@NotNull
	private static Room[][] newMap() {
		Room[][] rooms = new Room[Configuration.ROOMS_HOR][Configuration.ROOMS_VER];
		rooms[0][14] = new EntranceRoom();
		rooms[1][14] = new SewageTreatmentPlant();
		rooms[2][14] = new EngineRoom();
		rooms[3][14] = new KitchenRoom();
		rooms[4][14] = new BarrackRoom();
		rooms[0][0] = new MineshaftRoom();
		rooms[1][0] = new MineshaftRoom();
		rooms[2][0] = new MineshaftRoom();
		rooms[3][0] = new MineshaftRoom();
		rooms[4][0] = new MineshaftRoom();
		return rooms;
	}
}
