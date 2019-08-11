package info.projekt.jonas.spawner;

import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.Configuration;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class ResourceSpawner {

	@Contract(pure = true)
	public ResourceSpawner() {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				spawnResources();
			}
		}, 0, 1f / Configuration.Resources.RESOURCE_MULTIPLIER);
	}

	private void spawnResources() {
		for (int x = 0; x < Configuration.ROOMS_HOR; x++) {
			for (int y = 0; y < Configuration.ROOMS_VER; y++) {
				if (GameStorage.INSTANCE.getRoomAt(x, y) != null)
					Objects.requireNonNull(GameStorage.INSTANCE.getRoomAt(x, y)).generate();
			}
		}
	}
}
