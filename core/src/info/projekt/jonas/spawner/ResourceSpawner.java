package info.projekt.jonas.spawner;

import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.Configuration;
import org.jetbrains.annotations.Contract;

public class ResourceSpawner {

	@Contract(pure = true)
	public ResourceSpawner() {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				spawnResources();
			}
		}, 0, 1 / Configuration.RESOURCE_MULTIPLIER);
	}

	private void spawnResources() {
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 15; y++) {
				if (GameStorage.INSTANCE.getRoomAt(x, y) != null) GameStorage.INSTANCE.getRoomAt(x, y).generate();
			}
		}
	}
}
