package info.projekt.jonas.gui;

import info.projekt.jonas.Registry;
import info.projekt.jonas.gui.toolkit.KeyManager;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.widgets.button.ImageButton;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.TextureLoader;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HALF_WIDTH;

public class BuildGui extends Layer {

	public BuildGui() {
		boolean dir = false;
		int i = 300;
		for (Room room : Registry.getRooms().values()) {
			ImageButton button = new ImageButton(() -> System.out.println(room.getClass().getSimpleName()), 0, 0, 200, 100, room.getTexture() != null ? room.getTexture() : TextureLoader.getTexture("room_debug.png"));
			if (dir) {
				button.hitbox.x = HALF_WIDTH - 220;
				button.hitbox.y = i;
			} else {
				button.hitbox.x = HALF_WIDTH + 20;
				button.hitbox.y = i;
				i += 120;
			}
			dir = !dir;
			addWidget(button);
		}
	}

	@Override
	public boolean handleKeyboard(KeyManager manager) {
		return true;
	}
}
