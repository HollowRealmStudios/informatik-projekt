package info.projekt.jonas.gui.instance;

import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.widgets.TextButton;
import info.projekt.jonas.storage.GameStorage;

import java.util.concurrent.atomic.AtomicInteger;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

/**
 * @author Jonas
 */
public class DwellerListGui extends Layer {

	public DwellerListGui() {
		removeAll();
		AtomicInteger i = new AtomicInteger(200);
		GameStorage.INSTANCE.getDwellers().forEach(dweller -> {
			addWidget(new TextButton(() -> {
				DwellerGui.dweller = dweller;
				LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(DwellerGui.class, GUI_LAYER, true));
			}, 0, i.get(), dweller.toString(), FONT).centerX());
			i.addAndGet(100);
		});
	}
}
