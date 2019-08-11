package info.projekt.jonas.gui;

import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesOnOpen;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.gui.toolkit.widgets.TextButton;
import info.projekt.jonas.room.Buildable;
import info.projekt.jonas.room.Room;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.TextureLoader;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.*;

public class RoomGui extends Layer implements IHandlesActiveUpdates, IHandlesOnOpen {

	public static Room room;
	public static Dweller dweller;

	private Label info;
	private ImageButton upgrade;
	private ImageButton destroy;
	private Label lose;
	private Label gain;

	public static void acceptMove() {
		room.getDwellers().remove(dweller);
		dweller = null;
	}

	public static void declineMove() {
		dweller = null;
	}

	@Override
	public void onActiveUpdate() {
		info.setText(room.getClass().getSimpleName() + ": " + (room.getLevel() + 1));
	}

	@Override
	public void onOpen() {
		removeAll();
		info = new Label(0, HEIGHT - 300, room.getClass().getSimpleName() + ": " + (room.getLevel() + 1), FONT).centerX();
		upgrade = new ImageButton(() -> {
			if (room.isUpgradeable()) room.upgrade();
		}, HALF_WIDTH - 150, HEIGHT - 500, 100, 100, TextureLoader.getTexture("Arrow.png"));
		destroy = new ImageButton(() -> {
			if (Arrays.stream(room.getClass().getAnnotations()).anyMatch(annotation -> annotation instanceof Buildable)) {
				GameStorage.INSTANCE.setRoomAt(null, (int) room.getPosition().x, (int) room.getPosition().y);
				LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
				GameStorage.INSTANCE.currency += ((Buildable) room.getClass().getAnnotations()[0]).cost() / 3;
			} else LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("That room can't be sold", 2));
		}, HALF_WIDTH + 50, HEIGHT - 500, 100, 100, TextureLoader.getTexture("Delete.png"));
		if (Arrays.stream(room.getClass().getAnnotations()).anyMatch(annotation -> annotation instanceof Buildable)) {
			gain = new Label((HALF_WIDTH + 50) - (((WidgetUtil.getTextWidth(FONT, String.valueOf(((Buildable) room.getClass().getAnnotations()[0]).cost())) - destroy.hitbox.width) / 2) * -1), HEIGHT - 550, "+" + ((Buildable) room.getClass().getAnnotations()[0]).cost() / 3, FONT);
			lose = new Label(HALF_WIDTH - 150, HEIGHT - 550, "-" + ((Buildable) room.getClass().getAnnotations()[0]).cost(), FONT);
			addWidget(gain);
			addWidget(lose);
			addWidget(info);
			addWidget(upgrade);
			addWidget(destroy);
			AtomicInteger i = new AtomicInteger();
			room.getDwellers().forEach(dweller -> {
				TextButton button = new TextButton(() -> {
					RoomGui.dweller = dweller;
					LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
					LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("Selected " + dweller.getName(), 2));
				}, HALF_WIDTH - WidgetUtil.getTextWidth(FONT, dweller.toString()) / 2, HEIGHT - 625 - i.get() * 75, dweller.toString(), FONT);
				addWidget(button);
				i.getAndIncrement();
			});
		} else {
			addWidget(info);
			addWidget(upgrade);
			addWidget(destroy);
			AtomicInteger i = new AtomicInteger();
			room.getDwellers().forEach(dweller -> {
				TextButton button = new TextButton(() -> {
					RoomGui.dweller = dweller;
					LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
					LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("Selected " + dweller.getName(), 2));
				}, HALF_WIDTH - WidgetUtil.getTextWidth(FONT, dweller.toString()) / 2, HEIGHT - 550 - i.get() * 75, dweller.toString(), FONT);
				addWidget(button);
				i.getAndIncrement();
			});
		}
	}
}
