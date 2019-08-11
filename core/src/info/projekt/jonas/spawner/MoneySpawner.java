package info.projekt.jonas.spawner;

import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.Configuration;

public class MoneySpawner {

	public MoneySpawner() {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				GameStorage.INSTANCE.currency++;
			}
		}, 1f / Configuration.Resources.MONEY_MULTIPLIER, 1f / Configuration.Resources.MONEY_MULTIPLIER);
	}
}


