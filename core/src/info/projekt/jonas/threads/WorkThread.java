package info.projekt.jonas.threads;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.NameList;

import java.util.concurrent.ThreadLocalRandom;

import static info.projekt.InfoProjekt.GAME_STORAGE;
import static info.projekt.jonas.gui.GameScreenGui.notification;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

/**
 * @author Jonas
 */
public class WorkThread extends Thread {

	public enum NOTIFICATION {PLACED, UPGRADED, DWELLER}

	private boolean newDweller;
	private boolean roomPlaced;
	private boolean roomUpgraded;
	private int pass;
	private final int delay;

	public WorkThread(int delay) {
		this.delay = delay;
	}

	@Override
	public void run() {
		while (true) {
			notifyRooms();
			generateResources();
			consumeResources();
			pass++;
			if (pass > 5 && ThreadLocalRandom.current().nextBoolean()) {
				if (GAME_STORAGE.getRooms()[0][49].getDwellers().size() < 4) {
					Dweller dweller = NameList.nextDweller(ThreadLocalRandom.current().nextBoolean() ? Dweller.GENDER.MALE : Dweller.GENDER.FEMALE);
					GAME_STORAGE.addDweller(dweller);
					GAME_STORAGE.getRooms()[0][49].addDweller(dweller);
					notification.setPosition(HALF_WIDTH, 100, 1);
					notification.show("New Dweller: " + dweller.toString(), 2);
				} else {
					notification.show("Your entrance is full!", 2);
				}
				pass = 0;
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
}
