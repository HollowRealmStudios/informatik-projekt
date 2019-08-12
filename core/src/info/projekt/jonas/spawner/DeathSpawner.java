package info.projekt.jonas.spawner;

import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.Configuration;

import java.util.Objects;

public class DeathSpawner {

	public DeathSpawner() {
		for (int x = 0; x < Configuration.ROOMS_HOR; x++) {
			for (int y = 0; y < Configuration.ROOMS_VER; y++) {
				if (GameStorage.INSTANCE.getRoomAt(x, y) != null)
					Objects.requireNonNull(GameStorage.INSTANCE.getRoomAt(x, y)).removeDead();
			}
		}
	}

}
