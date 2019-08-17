package info.projekt.jonas.item;

public class CraftingComponent extends Item {

	private final int rarity;

	public int getRarity() {
		return rarity;
	}

	public String getName() {
		return name;
	}

	public CraftingComponent(String texture, String name, int rarity) {
		super(texture, name);
		this.rarity = rarity;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Rarity: " + rarity;
	}

	@Override
	public String prettyPrint() {
		return name + " (Rarity: " + rarity + ")";
	}
}
