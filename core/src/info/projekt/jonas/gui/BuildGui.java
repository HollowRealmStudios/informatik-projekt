package info.projekt.jonas.gui;

import info.projekt.jonas.gui.toolkit.KeyManager;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.room.Buildable;
import info.projekt.jonas.room.Room;
import info.projekt.jonas.storage.Registry;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.logging.Logger;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HALF_WIDTH;

public class BuildGui extends Layer {

	String name;
	private static Class<? extends Room> selected;

	@Contract(pure = true)
	public static Class<? extends Room> getRoom() {
		return selected;
	}

	public BuildGui() {
		boolean dir = false;
		int i = 300;
		for (Room room : Registry.getRooms().values()) {
			if (Arrays.stream(room.getClass().getAnnotations()).anyMatch(annotation -> annotation instanceof Buildable)) {
				int cost = ((Buildable) room.getClass().getAnnotations()[0]).cost();
				ImageButton button = new ImageButton(() -> {
					selected = room.getClass();
					LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, LayerSupervisor.GUI_LAYER, true));
					LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("Selected " + room.getClass().getSimpleName(), 1.5f));
				}, 0, 0, 200, 100, room.getTexture());
				Label label = new Label(0, 0, String.valueOf(cost), FONT);
				if (dir) {
					button.hitbox.x = HALF_WIDTH - 230;
					button.hitbox.y = i;
					label.x = HALF_WIDTH - 220;
					label.y = i + 40;
				} else {
					button.hitbox.x = HALF_WIDTH + 10;
					button.hitbox.y = i;
					label.x = HALF_WIDTH + 20;
					label.y = i + 40;
					i += 120;
				}
				dir = !dir;
				addWidget(button);
				addWidget(label);
			}
		}
	}

	@Override
	public void handleKeyboard(KeyManager manager) {
	}
}
