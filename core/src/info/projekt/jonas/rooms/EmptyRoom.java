package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;

public class EmptyRoom extends Room {

	public EmptyRoom() {
		super(0, new Texture[]{new Texture("room_empty.png")});
	}

}
