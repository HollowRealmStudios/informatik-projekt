package info.projekt.jonas.gui;

import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.gui.toolkit.widgets.button.TextButton;

import static info.projekt.jonas.gui.RenderUtils.FONT;

public class BuildGui extends Layer {

	public BuildGui() {
		addWidget(new Label(1000, 1000, "Hi!", FONT).centerX());
		addWidget(new TextButton(() -> {
			System.out.println("Image Button");
			LayerSupervisor.LAYER_STACK.push(DwellerListGui.class);
		}, 500, 500, "Click me!", FONT));
	}

}
