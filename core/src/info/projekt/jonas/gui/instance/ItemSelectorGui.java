package info.projekt.jonas.gui.instance;

import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesOnOpen;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.storage.GameStorage;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HALF_WIDTH;

public class ItemSelectorGui extends Layer implements IHandlesOnOpen {

	public static Class<? extends Item> type;
	public static Dweller dweller;

	@Override
	public void onOpen() {
		if (type.equals(WeaponItem.class))
			if (GameStorage.INSTANCE.items.stream().anyMatch(item -> item.getClass().equals(WeaponItem.class)))
				selectWeapon();
			else {
				LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("No Weapons available", 2));
				LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
			}
		if (type.equals(ArmorItem.class))
			if (GameStorage.INSTANCE.items.stream().anyMatch(item -> item.getClass().equals(ArmorItem.class)))
				selectArmor();
			else {
				LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("No Armors available", 2));
				LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
			}
	}

	private void selectArmor() {
		removeAll();
		int amount = (int) GameStorage.INSTANCE.items.stream().filter(item -> item.getClass().equals(ArmorItem.class)).count();
		final AtomicInteger[] i = {new AtomicInteger(HALF_HEIGHT - (amount * 114) / 8)};
		final AtomicIntegerArray columns = new AtomicIntegerArray(new int[]{1});
		GameStorage.INSTANCE.items.stream().filter(item -> item.getClass().equals(ArmorItem.class)).forEach(item -> {
			switch (columns.get(0)) {
				case 1:
					addWidget(new ImageButton(() -> dweller.setArmor((ArmorItem) item), HALF_WIDTH - 203, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 2:
					addWidget(new ImageButton(() -> dweller.setArmor((ArmorItem) item), HALF_WIDTH - 89, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 3:
					addWidget(new ImageButton(() -> dweller.setArmor((ArmorItem) item), HALF_WIDTH + 25, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 4:
					addWidget(new ImageButton(() -> dweller.setArmor((ArmorItem) item), HALF_WIDTH + 139, i[0].get(), 64, 64, item.getTexture()));
					break;
			}
			if (columns.get(0) == 4) columns.set(0, 0);
			columns.getAndIncrement(0);
			if (columns.get(0) == 1) i[0].addAndGet(115);
		});
	}

	private void selectWeapon() {
		removeAll();
		int amount = (int) GameStorage.INSTANCE.items.stream().filter(item -> item.getClass().equals(WeaponItem.class)).count();
		final AtomicInteger[] i = {new AtomicInteger(HALF_HEIGHT - (amount * 114) / 8)};
		final AtomicIntegerArray columns = new AtomicIntegerArray(new int[]{1});
		GameStorage.INSTANCE.items.stream().filter(item -> item.getClass().equals(WeaponItem.class)).forEach(item -> {
			switch (columns.get(0)) {
				case 1:
					addWidget(new ImageButton(() -> dweller.setWeapon((WeaponItem) item), HALF_WIDTH - 203, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 2:
					addWidget(new ImageButton(() -> dweller.setWeapon((WeaponItem) item), HALF_WIDTH - 89, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 3:
					addWidget(new ImageButton(() -> dweller.setWeapon((WeaponItem) item), HALF_WIDTH + 25, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 4:
					addWidget(new ImageButton(() -> dweller.setWeapon((WeaponItem) item), HALF_WIDTH + 139, i[0].get(), 64, 64, item.getTexture()));
					break;
			}
			if (columns.get(0) == 4) columns.set(0, 0);
			columns.getAndIncrement(0);
			if (columns.get(0) == 1) i[0].addAndGet(115);
		});
	}
}
