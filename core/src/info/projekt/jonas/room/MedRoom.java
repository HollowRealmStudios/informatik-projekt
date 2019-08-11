package info.projekt.jonas.room;

import info.projekt.jonas.room.capabilities.IConsume;
import info.projekt.jonas.room.capabilities.IProduce;
import info.projekt.jonas.storage.GameStorage;

/**
 * @author Jonas
 */
@Buildable(cost = 2000)
public class MedRoom extends Room implements IProduce, IConsume {

	private int i = 0;

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
		getDwellers().forEach(dweller -> i++);
		if(i == 10) {
			i = 0;
			GameStorage.INSTANCE.meds++;
		}
	}
}
