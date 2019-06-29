package info.projekt.jonas.gui;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class GuiManager {

	private static ArrayList<Gui> guis = new ArrayList<>();

	public static void registerGui(Gui gui) {
		guis.add(gui);
	}

	@Nullable
	public static Gui requestGui(Class<? extends Gui> c) {
		for (Gui gui : guis) if (gui.getClass().equals(c)) return gui;
		return null;
	}

	public static void attachListener(Class<? extends Gui> g, Runnable r) {
		r.run();
	}
}
