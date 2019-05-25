package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

/*
 *The child class, every room inherits from
 * */
public class Room implements Serializable {

	private int level = 1;
	private String name;
	private transient Texture texture;
	private String textureString;

	public Room(String name, String texture) {
		this.textureString = texture;
		this.name = name;
	}

	/**
	 * @return return the room's texture
	 */
	public Texture getTexture() {
		if(texture == null) texture = new Texture(textureString);
		return texture;
	}

	/**
	 * set the level of the room
	 *
	 * @param level the level to set to
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Level: " + level + ", Capacity: " + texture.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Room && ((Room) obj).level == level;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
