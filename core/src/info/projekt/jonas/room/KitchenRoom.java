package info.projekt.jonas.room;

import info.projekt.jonas.room.capabilities.IProduce;
import info.projekt.jonas.storage.GameStorage;

/**
 * @author Jonas
 */
@Buildable(cost = 600)
public class KitchenRoom extends Room implements IProduce {

	@Override
	public void produce() {
		getDwellers().forEach(dweller -> GameStorage.INSTANCE.food += dweller.getCreativity() * (getLevel() + 1));
	}
}
