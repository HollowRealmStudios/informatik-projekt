package info.projekt.jonas.gui;

import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.room.Buildable;
import info.projekt.jonas.room.Room;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.TextureLoader;

import java.util.Arrays;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.*;

public class RoomGui extends Layer implements IHandlesActiveUpdates {

	public static Room room;

	private final Label info = new Label(0, HEIGHT - 300, room != null ? room.getClass().getSimpleName() + ": " + (room.getLevel() + 1) : "*****************", FONT).centerX();
	private final ImageButton upgrade = new ImageButton(() -> {
		if (room.isUpgradeable()) room.upgrade();
	}, HALF_WIDTH - 150, HEIGHT - 500, 100, 100, TextureLoader.getTexture("Arrow.png"));
	private final ImageButton destroy = new ImageButton(() -> {
		if (Arrays.stream(room.getClass().getAnnotations()).anyMatch(annotation -> annotation instanceof Buildable)) {
			GameStorage.INSTANCE.setRoomAt(null, (int) room.getPosition().x, (int) room.getPosition().y);
			LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
			GameStorage.INSTANCE.currency += ((Buildable) room.getClass().getAnnotations()[0]).cost() / 2;
		} else LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("That room can't be destroyed", 2));
	}, HALF_WIDTH + 50, HEIGHT - 500, 100, 100, TextureLoader.getTexture("Delete.png"));

	public RoomGui() {
		addWidget(info);
		addWidget(upgrade);
		addWidget(destroy);

	}

	@Override
	public void onActiveUpdate() {
		info.setText(room.getClass().getSimpleName() + ": " + (room.getLevel() + 1));
	}
}
