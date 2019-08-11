package info.projekt.jonas.spawner;

import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.Configuration;
import info.projekt.jonas.util.NameList;
import info.projekt.jonas.util.Random;
import org.jetbrains.annotations.Contract;

public class DwellerSpawner {

	@Contract(pure = true)
	public DwellerSpawner() {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				spawnDweller();
			}
		}, Configuration.Resources.MINUTES_PER_DWELLER * 60f, Configuration.Resources.MINUTES_PER_DWELLER * 60f);
	}

	private void spawnDweller() {
		if (GameStorage.INSTANCE.getRoomAt(0, 14).isSpaceRemaining()) {
			Dweller dweller = NameList.nextDweller(Random.ranGender());
			GameStorage.INSTANCE.getRoomAt(0, 14).addDweller(dweller);
			GameStorage.INSTANCE.addDweller(dweller);
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("New dweller arrived: " + dweller.toString(), 2f));
		}
		else {
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("No more space", 2f));
		}
	}
}
