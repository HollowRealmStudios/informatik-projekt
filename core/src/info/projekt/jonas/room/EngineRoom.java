package info.projekt.jonas.room;

import info.projekt.jonas.room.capabilities.IProduce;
import info.projekt.jonas.storage.GameStorage;

/**
 * @author Jonas
 */
@Buildable(cost = 400)
public class EngineRoom extends Room implements IProduce {

	@Override
	public void produce() {
		getDwellers().forEach(dweller -> GameStorage.INSTANCE.energy += dweller.getStrength() * (getLevel() + 1));
	}
}
