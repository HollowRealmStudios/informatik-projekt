package info.projekt.jonas.items;

import com.badlogic.gdx.graphics.Texture;

public class CraftingComponent extends Item {

	private int rarity;

	public int getRarity() {
		return rarity;
	}

	public String getName() {
		return name;
	}

	public Texture getTexture() {
		return texture;
	}

	public CraftingComponent(Texture texture, String name, int rarity) {
		super(texture, name);
		this.rarity = rarity;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Rarity: " + rarity;
	}
}
