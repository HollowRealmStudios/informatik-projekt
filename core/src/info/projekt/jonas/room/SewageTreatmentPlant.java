package info.projekt.jonas.room;

import info.projekt.jonas.room.capabilities.IProduce;
import info.projekt.jonas.storage.GameStorage;

/**
 * @author Jonas
 */
@Buildable(cost = 400)
public class SewageTreatmentPlant extends Room implements IProduce {

	@Override
	public void produce() {
		getDwellers().forEach(dweller -> GameStorage.INSTANCE.water += dweller.getIntelligence() * (getLevel() + 1));
	}
}
