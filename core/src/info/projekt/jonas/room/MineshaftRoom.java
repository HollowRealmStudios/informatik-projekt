package info.projekt.jonas.room;

import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.item.ArmorItem;
import info.projekt.jonas.item.Item;
import info.projekt.jonas.item.WeaponItem;
import info.projekt.jonas.room.capabilities.IConsume;
import info.projekt.jonas.room.capabilities.IProduce;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.Registry;
import info.projekt.jonas.util.Random;

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
			GameStorage.INSTANCE.water -= 10;
			GameStorage.INSTANCE.food -= 10;
			GameStorage.INSTANCE.energy -= 10;
		});
	}

	@Override
	public void produce() {
		getDwellers().forEach(dweller -> {
			s += dweller.getStrength();
			i += dweller.getIntelligence();
			c += dweller.getCharisma();
			k += dweller.getCreativity();
			if (Random.ranBool() && Random.ranBool() && Random.ranBool() && Random.ranBool() && Random.ranBool() && Random.ranBool()) {
				dweller.damage(ThreadLocalRandom.current().nextInt(2, 10));
				LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("A dweller took damage", 2));
			}
			if(Random.ranBool() && Random.ranBool()  && Random.ranBool()) GameStorage.INSTANCE.currency += 50;
		});
		if (s >= 100 && i >= 100 && c >= 100 && k >= 100) {
			s = 0;
			i = 0;
			c = 0;
			k = 0;
			Item item = (Item) Registry.getItems().values().toArray()[ThreadLocalRandom.current().nextInt(0, Registry.getItems().size() - 1)];
			if(item instanceof WeaponItem) GameStorage.INSTANCE.weapons.add((WeaponItem) item);
			if(item instanceof ArmorItem) GameStorage.INSTANCE.armors.add((ArmorItem) item);
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("New item", 2));
		}
	}
}
