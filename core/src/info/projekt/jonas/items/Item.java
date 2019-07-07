package info.projekt.jonas.items;

import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;

/**
 * @author Jonas
 */
public abstract class Item implements Serializable {

	/**
	 * The name of the item
	 */
	public final String name;

	/**
	 * The texture of the item
	 */
	protected transient Texture texture;

	/**
	 * The default constructor
	 *
	 * @param texture the item's texture
	 * @param name    the item's name
	 */
	protected Item(Texture texture, String name) {
		this.texture = texture;
		this.name = name;
	}

	public Texture getTexture() {
		if (texture == null) texture = new Texture(name);
		return texture;
	}

	public abstract String toString();
}
