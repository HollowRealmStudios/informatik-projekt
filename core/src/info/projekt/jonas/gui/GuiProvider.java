package info.projekt.jonas.gui;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class GuiProvider {

	private static ArrayList<Gui> guis = new ArrayList<>();

	public static void registerGui(@NotNull Class<? extends Gui> gui) {
		try {
			guis.add(gui.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static Gui requestGui(@NotNull Class<? extends Gui> c) {
		for (Gui gui : guis) if (gui.getClass().equals(c)) return gui;
		return null;
	}

	public static void attachKey(Runnable r, Runnable event) {

	}

	public static ArrayList<Gui> getGuis() {
		return guis;
	}
}
