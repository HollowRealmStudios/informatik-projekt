package info.projekt.jonas.gui;

import java.util.ArrayList;

public class GuiManager {

	private ArrayList<Gui> guis = new ArrayList<>();

	public void registerGui(Gui gui) {
		guis.add(gui);
	}

	public Gui requestGui(Class<? extends Gui> c) {
		guis.stream().filter(c).
	}
}
