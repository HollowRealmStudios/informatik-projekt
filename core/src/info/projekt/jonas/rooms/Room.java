package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/*
 *The child class, every room inherits from
 * */
public class Room {

	/**
	 * The global width and height of the rooms in pixels
	 */
	public static final int WIDTH = 400;
	public static final int HEIGHT = 200;

	private int level = 1;
	private int capacity;
	private HashMap<Integer, Texture> textures = new HashMap<Integer, Texture>();

	/**
	 * This class' constructor is protected, so you can't directly instantiate it.
	 * Instead, extend from this class to create a new room
	 *
	 * @param capacity the amount of people to fit into the room
	 * @param texture  the textures of the room, in order from lowest to highest level
	 */
	protected Room(int capacity, Texture[] texture) {
		this.capacity = capacity;
		for (int i = 0; i < texture.length; i++) {
			textures.put(i, texture[i]);
		}
	}

	/**
	 * @return return the room's texture
	 */
	public Texture getTexture() {
		return textures.get(level - 1);
	}

	/**
	 * set the level of the room
	 * @param level the level to set to
	 * */
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Level: " + level + ", Capacity: " + capacity + ", Texture: " + textures.get(level).toString();
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
