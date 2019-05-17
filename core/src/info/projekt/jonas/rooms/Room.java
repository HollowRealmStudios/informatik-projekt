package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;

/*
 *The child room class, every room is inheriting from
 * */
public class Room {

	/**
	 * The global width and height of the rooms in pixels
	 */
	public static final int WIDTH = 400;
	public static final int HEIGHT = 200;

	private int level;
	private int capacity;
	private Texture texture;

	/**
	 * This class' constructor is protected, so you can't directly instantiate it.
	 * Instead, extend from this class to create a new room
	 *
	 * @param level    the level of the room
	 * @param capacity the amount of people to fit into the room
	 * @param texture  the texture of the room
	 */
	protected Room(int level, int capacity, Texture texture) {
		this.level = level;
		this.capacity = capacity;
		this.texture = texture;
	}

	/**
	 * @return return the room's texture
	 */
	public Texture getTexture() {
		return texture;
	}

	@Override
	public String toString() {
		return "Level: " + level + ", Capacity: " + capacity + ", Texture: " + texture.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Room && ((Room) obj).capacity == capacity && ((Room) obj).level == level;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
