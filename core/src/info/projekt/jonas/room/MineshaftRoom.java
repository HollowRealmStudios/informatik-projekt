package info.projekt.jonas.room;

import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.room.capabilities.IConsume;
import info.projekt.jonas.room.capabilities.IProduce;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.Registry;

import java.util.concurrent.ThreadLocalRandom;


public class MineshaftRoom extends Room implements IProduce, IConsume {

	int s, i, c, k;

	@Override
	public boolean enoughResources() {
		return GameStorage.INSTANCE.water > getDwellers().size() && GameStorage.INSTANCE.energy > getDwellers().size() && GameStorage.INSTANCE.food > getDwellers().size();
	}

	@Override
	public void consume() {
		getDwellers().forEach(dweller -> {
			GameStorage.INSTANCE.water--;
			GameStorage.INSTANCE.food--;
			GameStorage.INSTANCE.energy--;
		});
	}

	@Override
	public void produce() {
		getDwellers().forEach(dweller -> {
			s += dweller.getStrength();
			i += dweller.getIntelligence();
			c += dweller.getCharisma();
			k += dweller.getCreativity();
		});
		if (s >= 100 && i >= 100 && c >= 100 && k >= 100) {
			s = 0;
			i = 0;
			c = 0;
			k = 0;
			GameStorage.INSTANCE.items.add((Item) Registry.getItems().values().toArray()[ThreadLocalRandom.current().nextInt(0, Registry.getItems().size() - 1)]);
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("New item", 2));
		}
	}
}
