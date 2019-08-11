package info.projekt.jonas.gui;

import info.projekt.jonas.gui.toolkit.widgets.Image;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesOnOpen;
import info.projekt.jonas.storage.GameStorage;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HALF_WIDTH;

public class StorageGui extends Layer implements IHandlesOnOpen {

	@Override
	public void onOpen() {
		removeAll();
		int amount = GameStorage.INSTANCE.items.size();
		final AtomicInteger[] i = {new AtomicInteger(HALF_HEIGHT - (amount * 114) / 8)};
		final AtomicIntegerArray columns = new AtomicIntegerArray(new int[]{1});
		GameStorage.INSTANCE.items.forEach(item -> {
			switch (columns.get(0)) {
				case 1:
					addWidget(new Image(HALF_WIDTH - 203, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 2:
					addWidget(new Image(HALF_WIDTH - 89, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 3:
					addWidget(new Image(HALF_WIDTH + 25, i[0].get(), 64, 64, item.getTexture()));
					break;
				case 4:
					addWidget(new Image(HALF_WIDTH + 139, i[0].get(), 64, 64, item.getTexture()));
					break;
			}
			System.out.println(columns.get(0));
			if (columns.get(0) == 4) columns.set(0, 0);
			columns.getAndIncrement(0);
			if (columns.get(0) == 1) i[0].addAndGet(115);
		});
	}
}
