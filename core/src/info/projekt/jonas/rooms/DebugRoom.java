package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.Animated;

public class DebugRoom extends Room {

	public DebugRoom() {
		super(-1, new Texture[]{new Texture("room_debug.png")});
	}
}
