package info.projekt.jonas.spawner;

import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.room.BarrackRoom;
import info.projekt.jonas.room.Room;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.Configuration;
import info.projekt.jonas.util.NameList;
import info.projekt.jonas.util.Random;
import org.jetbrains.annotations.Contract;

public class ChildSpawner {

	@Contract(pure = true)
	public ChildSpawner() {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				spawnChild();
			}
		}, Configuration.Resources.MINUTES_PER_CHILDREN * 60f, Configuration.Resources.MINUTES_PER_CHILDREN * 60f);
	}

	private void spawnChild() {
		for (int x = 0; x < Configuration.ROOMS_HOR; x++) {
			for (int y = 0; y < Configuration.ROOMS_VER; y++) {
				if (GameStorage.INSTANCE.getRoomAt(x, y) instanceof BarrackRoom) {
					Room room = GameStorage.INSTANCE.getRoomAt(x, y);
					if (room.isSpaceRemaining() && room.getDwellers().stream().anyMatch(dweller -> dweller.getGender() == Dweller.GENDER.MALE) && room.getDwellers().stream().anyMatch(dweller -> dweller.getGender() == Dweller.GENDER.FEMALE)) {
						Dweller dweller = NameList.nextDweller(Random.ranGender());
						room.addDweller(dweller);
						GameStorage.INSTANCE.addDweller(dweller);
						LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("New child: " + dweller.toString(), 2));
					}
				}
			}
		}
	}

}
