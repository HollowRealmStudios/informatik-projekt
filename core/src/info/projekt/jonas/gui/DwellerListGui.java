package info.projekt.jonas.gui;

import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.widgets.Label;

import static info.projekt.jonas.gui.RenderUtils.FONT;

/**
 * @author Jonas
 */
public class DwellerListGui extends Layer {

	public DwellerListGui() {
		addWidget(new Label(0, 0, "Hello!", FONT).centerX().centerY());
	}
}
