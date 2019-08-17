package info.projekt.jonas.item;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jonas
 */
public class WeaponItem extends Item {

	/**
	 * The damage of the weapon
	 */
	private final int damage;

	/**
	 * The default constructor
	 *
	 * @param name      the name of the weapon
	 * @param damage    the weapon's damage
	 * @param deviation the deviation from the weapon's damage, can be scaled up or down
	 */
	public WeaponItem(String texture, String name, int damage, int deviation) {
		super(texture, name);
		this.damage = (deviation == 0 ? damage : ThreadLocalRandom.current().nextBoolean() ? damage - ThreadLocalRandom.current().nextInt(deviation) : damage + ThreadLocalRandom.current().nextInt(deviation));
	}

	@Override
	public String toString() {
		return name + ", Texture: " + texture + ", Damage: " + damage;
	}

	@Override
	public String prettyPrint() {
		return name + " (Damage: " + damage + ")";
	}
}
