package info.projekt.jonas.item;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jonas
 */
public class ArmorItem extends Item {

	/**
	 * The protection of the armor
	 */
	private final int protection;

	/**
	 * The default constructor
	 *
	 * @param name       the armor's name
	 * @param deviation  the armor's protection deviation. Can be positive or negative
	 * @param protection the armor's protection
	 * @param texture    the armor's texture
	 */
	public ArmorItem(String texture, String name, int protection, int deviation) {
		super(texture, name);
		this.protection = (deviation == 0 ? protection : ThreadLocalRandom.current().nextBoolean() ? protection - ThreadLocalRandom.current().nextInt(deviation) : protection + ThreadLocalRandom.current().nextInt(deviation));
	}

	@Override
	public String toString() {
		return name + ", Texture: " + texture + ", Protection: " + protection;
	}

	@Override
	public String prettyPrint() {
		return name + " (Protection: " + protection + ")";
	}
}
