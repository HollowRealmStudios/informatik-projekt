package info.projekt.jonas.rooms;

import info.projekt.InfoProjekt;
import info.projekt.jonas.Registry;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.util.InfoCenter;

import java.util.concurrent.ThreadLocalRandom;

import static info.projekt.InfoProjekt.GAME_STORAGE;
import static info.projekt.jonas.gui.GameScreen.notification;

public class MineshaftRoom extends Room {

	private int pass = 0;

	public MineshaftRoom() {
		super("Mineshaft", "Mineshaft/Mineshaft_1.png");
	}

	@Override
	public void produce() {
		getDwellers().forEach(d -> pass++);
		if (pass >= 10) {
			InfoCenter.recalculate();
			if (InfoCenter.storageCapacity >= GAME_STORAGE.ITEMS.size()) {
				GAME_STORAGE.ITEMS.add((Item) Registry.getITEMS().values().toArray()[ThreadLocalRandom.current().nextInt(0, Registry.getITEMS().values().toArray().length - 1)]);
				notification.show("New item acquired", 2);
			} else notification.show("Not enough storage space", 4);
			pass = 0;
		}
	}

	@Override
	public void consume() {
		getDwellers().forEach(d -> {
			GAME_STORAGE.food.subtract(4);
			GAME_STORAGE.water.subtract(4);
			GAME_STORAGE.energy.subtract(4);
		});
	}

	@Override
	public boolean enoughResources() {
		return GAME_STORAGE.food.get() >= 4 && GAME_STORAGE.food.get() >= 4 && GAME_STORAGE.energy.get() >= 4;
	}
}
