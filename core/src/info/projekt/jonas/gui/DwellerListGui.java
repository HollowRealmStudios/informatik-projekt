package info.projekt.jonas.gui;

import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.storage.GameStorage;

import java.util.concurrent.atomic.AtomicInteger;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

/**
 * @author Jonas
 */
public class DwellerListGui extends Layer {

	public DwellerListGui() {
		AtomicInteger i = new AtomicInteger(200);
		GameStorage.INSTANCE.getDwellers().forEach(dweller -> {
			addWidget(new Label(0, i.get(), dweller.toString(), FONT).centerX());
			i.addAndGet(100);
		});
	}
}
