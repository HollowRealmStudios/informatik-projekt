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
	@SuppressWarnings("WeakerAccess")
	public final String name;

	/**
	 * The texture of the item
	 */
	@SuppressWarnings("WeakerAccess")
	protected transient Texture texture;

	/**
	 * The default constructor
	 *
	 * @param texture the item's texture
	 * @param name    the item's name
	 */
	@SuppressWarnings("WeakerAccess")
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
