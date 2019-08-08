package info.projekt.jonas.gui;

import info.projekt.jonas.Registry;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.gui.toolkit.widgets.button.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.button.TextButton;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class BuildGui extends Layer {

	public BuildGui() {
		int i = 0;
		Registry.getRooms().forEach((s, r) -> {
			addWidget(new ImageButton(() -> System.out.println(s), 0, i * 100, 100, 50, r.getTexture()));
		});
	}

}
