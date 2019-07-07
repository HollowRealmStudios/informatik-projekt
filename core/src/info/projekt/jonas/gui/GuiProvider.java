package info.projekt.jonas.gui;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Jonas
 */
public class GuiProvider {

	private static final ArrayList<Gui> guis = new ArrayList<>();

	public static void registerGui(@NotNull Class<? extends Gui> gui) {
		try {
			guis.add(gui.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("ConstantConditions")
	@NotNull
	public static Gui requestGui(@NotNull Class<? extends Gui> c) {
		for (Gui gui : guis) if (gui.getClass().equals(c)) return gui;
		return null;
	}

	public static ArrayList<Gui> getGuis() {
		return guis;
	}
}
