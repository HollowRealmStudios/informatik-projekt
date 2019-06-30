package info.projekt.jonas.threads;

import com.badlogic.gdx.utils.Logger;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.NameList;

import java.util.concurrent.ThreadLocalRandom;

import static info.projekt.InfoProjekt.GAME_STORAGE;

public class WorkThread {


	private final Logger LOGGER = new Logger("Work Thread");
	private final Thread thread;
	private boolean newDweller;
	private boolean roomPlaced;
	private boolean roomUpgraded;
	private int pass;

	public WorkThread(int delay) {
		thread = new Thread(() -> {
			while (true) {
				LOGGER.debug(GAME_STORAGE.energy + " | " + GAME_STORAGE.water + " | " + GAME_STORAGE.food);
				notifyRooms();
				generateResources();
				consumeResources();
				pass++;
				if (pass > 200) if (ThreadLocalRandom.current().nextBoolean()) {
					GAME_STORAGE.addDweller(NameList.nextDweller(ThreadLocalRandom.current().nextBoolean() ? Dweller.GENDER.MALE : Dweller.GENDER.FEMALE));
					pass = 0;
					GAME_STORAGE.getDwellers().forEach(d -> LOGGER.debug(d.toString()));
				}
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void notifyRooms() {
		for (Room[] rooms : GAME_STORAGE.getRooms()) {
			for (Room room : rooms) {
				if (room != null) {
					room.onTick();
					if (newDweller) newDweller = false;
					room.onNewDweller();
					if (roomPlaced) roomPlaced = false;
					room.onPlace();
					if (roomUpgraded) roomUpgraded = false;
					room.onUpgrade();
				}
			}
		}
	}

	private void generateResources() {
		for (Room[] rooms : GAME_STORAGE.getRooms()) {
			for (Room room : rooms) {
				if (room != null) {
					room.produce();
				}
			}
		}
	}

	private void consumeResources() {
		for (Room[] rooms : GAME_STORAGE.getRooms()) {
			for (Room room : rooms) {
				if (room != null) {
					room.consume();
				}
			}
		}
	}

	public void start() {
		thread.start();
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		thread.stop();
	}

	public void notify(NOTIFICATION notification) {
		switch (notification) {
			case PLACED:
				roomPlaced = true;
				break;
			case DWELLER:
				newDweller = true;
				break;
			case UPGRADED:
				roomUpgraded = true;
				break;
		}
	}

	public enum NOTIFICATION {PLACED, UPGRADED, DWELLER}
}
