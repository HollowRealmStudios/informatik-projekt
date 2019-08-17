package info.projekt.jonas.gui.instance;

import com.badlogic.gdx.Gdx;
import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesOnOpen;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.item.ArmorItem;
import info.projekt.jonas.item.WeaponItem;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.TextureLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.LayerSupervisor.LAYER_QUEUE;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HALF_WIDTH;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HEIGHT;

public class ItemSelectorWeaponGui extends Layer implements IHandlesOnOpen, IHandlesActiveUpdates {

	public static Dweller dweller;
	private int pages;
	private int page = 0;
	private ArrayList<ArmorItem> weapons;

	@NotNull
	@Contract(pure = true)
	private ArrayList<List<WeaponItem>> split() {
		ArrayList<List<WeaponItem>> items = new ArrayList<>();
		int perPage = ((HEIGHT - 400) / 89) * 4;
		int pages = (int) Math.ceil((float) GameStorage.INSTANCE.weapons.size() / (float) perPage);
		this.pages = pages;
		if (pages > 1) {
			for (int page = 0; page < pages; page++) {
				if (page + 1 == pages)
					items.add(GameStorage.INSTANCE.weapons.subList(page * perPage, GameStorage.INSTANCE.weapons.size()));
				else
					items.add(GameStorage.INSTANCE.weapons.subList(page * perPage, (page + 1) * perPage));
			}
		} else items.add(GameStorage.INSTANCE.weapons);
		return items;
	}

	@Override
	public void onOpen() {
		if (GameStorage.INSTANCE.weapons.isEmpty()) {
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("No items available", 2));
			LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
			return;
		}
		pages = GameStorage.INSTANCE.weapons.size() / ((HEIGHT - 400) / 89);
	}

	@Override
	public void onActiveUpdate() {
		removeAll();
		boolean overflow = (GameStorage.INSTANCE.weapons.size() * 89) / 4 > Gdx.graphics.getHeight() - 400;
		AtomicInteger row = new AtomicInteger(HEIGHT - 200);
		AtomicInteger column = new AtomicInteger(1);
		split().get(page).forEach(item -> {
			switch (column.get()) {
				case 1:
					addWidget(new ImageButton(() -> {dweller.setWeapon(item); LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));} , HALF_WIDTH - 203, row.get(), 64, 64, item.getTexture()));
					break;
				case 2:
					addWidget(new ImageButton(() -> {dweller.setWeapon(item); LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));}, HALF_WIDTH - 89, row.get(), 64, 64, item.getTexture()));
					break;
				case 3:
					addWidget(new ImageButton(() -> {dweller.setWeapon(item); LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));}, HALF_WIDTH + 25, row.get(), 64, 64, item.getTexture()));
					break;
				case 4:
					addWidget(new ImageButton(() -> {dweller.setWeapon(item); LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));}, HALF_WIDTH + 139, row.get(), 64, 64, item.getTexture()));
					break;
			}
			if (column.get() == 4) column.set(0);
			column.addAndGet(1);
			if (column.get() == 1) row.addAndGet(-89);
		});
		if (overflow) {
			addWidget(new ImageButton(() -> {
				if (page != pages - 1) page++;
			}, HALF_WIDTH + 25, 200, 64, 64, TextureLoader.getTexture("Right.png")));
			addWidget(new ImageButton(() -> {
				if (page != 0) page--;
			}, HALF_WIDTH - 89, 200, 64, 64, TextureLoader.getTexture("Left.png")));
		}
	}
}