package info.projekt.jonas.dweller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.storage.Registry;
import info.projekt.jonas.util.TextureLoader;
import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.Contract;

import java.io.Serializable;

/**
 * @author Jonas
 */
public class Dweller implements Serializable {

	private final String completeName;
	private final Tuple<ArmorItem, WeaponItem> items = new Tuple<>((ArmorItem) Registry.getItem("Hazmat Suit"), (WeaponItem) Registry.getItem("Ballistic-rifle"));
	private final GENDER gender;
	private final int strength;
	private final int intelligence;
	private final int charisma;
	private final int creativity;
	private transient Texture texture;
	private int health;

	public Dweller(String name, String surname, GENDER gender, int strength, int intelligence, int charisma, int creativity) {
		this.completeName = name + ", " + surname;
		this.gender = gender;
		this.strength = MathUtils.clamp(strength, 0, 10);
		this.intelligence = MathUtils.clamp(intelligence, 0, 10);
		this.charisma = MathUtils.clamp(charisma, 0, 10);
		this.creativity = MathUtils.clamp(creativity, 0, 10);
	}

	public String getName() {
		return completeName;
	}

	public GENDER getGender() {
		return gender;
	}

	private void repopulate() {
		texture = TextureLoader.getTextureUnsafe("textures/" + gender.name() + ".png");
	}

	@Contract(pure = true)
	private boolean isTextureNull() {
		return texture == null;
	}

	public ArmorItem getArmor() {
		return items.getOne();
	}

	public void setArmor(ArmorItem armor) {
		items.setOne(armor);
	}

	public WeaponItem getWeapon() {
		return items.getTwo();
	}

	public void setWeapon(WeaponItem weapon) {
		items.setTwo(weapon);
	}

	public Texture getTexture() {
		if (isTextureNull()) repopulate();
		return texture;
	}

	public int getStrength() {
		return strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getCharisma() {
		return charisma;
	}

	public int getCreativity() {
		return creativity;
	}

	@Override
	public String toString() {
		return completeName + ", " + strength + ", " + intelligence + ", " + charisma + ", " + creativity;
	}

	public void heal() {
		health = 100;
	}

	public void damage(int amount) {
		health -= amount;
	}

	public enum GENDER {MALE, FEMALE}
}
