package info.projekt.jonas.dwellers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import info.projekt.jonas.Registry;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.util.Tuple;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Jonas
 */
public class Dweller implements Serializable {

	public enum GENDER {MALE, FEMALE}

	private Tuple<ArmorItem, WeaponItem> items = new Tuple<>((ArmorItem) Registry.getItem("Hazmat Suit"), (WeaponItem) Registry.getItem("Ballistic-rifle"));
	private transient Texture texture;
	private GENDER gender;
	private String name;
	private String surname;
	private final String completeName;
	private int strength;
	private int intelligence;
	private int charisma;
	private int creativity;

	public Dweller(String name, String surname, GENDER gender, int strength, int intelligence, int charisma, int creativity) {
		this.name = name;
		this.surname = surname;
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

	public WeaponItem getWeapon() {
		return items.getTwo();
	}

	public void setArmor(ArmorItem armor) {
		items.setOne(armor);
	}

	public void setWeapon(WeaponItem weapon) {
		items.setTwo(weapon);
	}

	public GENDER getGender() {
		return gender;
	}

	public Texture getTexture() {
		if (texture == null) texture = new Texture(gender == GENDER.MALE ? "Male.png" : "Female.png");
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
		return new ArrayList<>(Arrays.asList(completeName + "   ", String.valueOf(strength) + "   ", String.valueOf(intelligence) + "   ", String.valueOf(charisma) + "   ", String.valueOf(creativity)));
	}

	@Override
	public String toString() {
		return completeName + ", " + strength + ", " + intelligence + ", " + charisma + ", " + creativity;
	}
}
