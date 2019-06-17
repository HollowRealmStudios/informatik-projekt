package info.projekt.jonas.threads;

import info.projekt.InfoProjekt;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class WorkThread {


	public enum NOTIFICATION {PLACED, UPGRADED, DWELLER}

	private final Thread thread;
	private boolean newDweller;
	private boolean roomPlaced;
	private boolean roomUpgraded;
	private int pass;

	public WorkThread(int delay) {
		thread = new Thread(() -> {
			while (true) {
				notifyRooms();
				generateResources();
				consumeResources();
				pass++;
				if (pass > 200) if (ThreadLocalRandom.current().nextBoolean()) {
					InfoProjekt.GAME_STORAGE.addDweller(new Dweller());
					pass = 0;
					InfoProjekt.GAME_STORAGE.getDwellers().forEach(d -> System.out.println(d.toString()));
					JOptionPane.showMessageDialog(null, "New Dweller");
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
		for (Room[] rooms : InfoProjekt.GAME_STORAGE.getRooms()) {
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
		for (Room[] rooms : InfoProjekt.GAME_STORAGE.getRooms()) {
			for (Room room : rooms) {
				if (room != null) {
					room.produce();
				}
			}
		}
	}

	private void consumeResources() {
		for (Room[] rooms : InfoProjekt.GAME_STORAGE.getRooms()) {
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
}
