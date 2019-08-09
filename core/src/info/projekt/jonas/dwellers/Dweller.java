package info.projekt.jonas.dwellers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import info.projekt.jonas.storage.Registry;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.util.Tuple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Jonas
 */
@SuppressWarnings("unused")
public class Dweller implements Serializable {

	public enum GENDER {MALE, FEMALE}

	private final String completeName;
	private final Tuple<ArmorItem, WeaponItem> items = new Tuple<>((ArmorItem) Registry.getItem("Hazmat Suit"), (WeaponItem) Registry.getItem("Ballistic-rifle"));
	private final GENDER gender;
	private final int strength;
	private final int intelligence;
	private final int charisma;
	private final int creativity;
	private transient Texture texture;

	public Dweller(String name, String surname, GENDER gender, int strength, int intelligence, int charisma, int creativity) {
		this.completeName = surname + ", " + name;
		this.gender = gender;
		this.strength = MathUtils.clamp(strength, 0, 10);
		this.intelligence = MathUtils.clamp(intelligence, 0, 10);
		this.charisma = MathUtils.clamp(charisma, 0, 10);
		this.creativity = MathUtils.clamp(creativity, 0, 10);
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

	public GENDER getGender() {
		return gender;
	}

	public Texture getTexture() {
		if (texture == null) texture = new Texture(gender == GENDER.MALE ? "Dweller.png" : "Female.png");
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

	public ArrayList<String> prettyPrint() {
		return new ArrayList<>(Arrays.asList(completeName + "   ", strength + "   ", intelligence + "   ", charisma + "   ", String.valueOf(creativity)));
	}

	@Override
	public String toString() {
		return completeName + ", " + strength + ", " + intelligence + ", " + charisma + ", " + creativity;
	}
}
