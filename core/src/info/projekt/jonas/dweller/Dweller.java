package info.projekt.jonas.dweller;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.item.ArmorItem;
import info.projekt.jonas.item.WeaponItem;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.Registry;
import info.projekt.jonas.util.TextureLoader;
import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;

/**
 * @author Jonas
 */
public class Dweller implements Serializable {

	private final String completeName;
	private final Tuple<ArmorItem, WeaponItem> items = new Tuple<>((ArmorItem) Registry.getItem("T-Shirt"), (WeaponItem) Registry.getItem("Knife"));
	private final GENDER gender;
	private final int strength;
	private final int intelligence;
	private final int charisma;
	private final int creativity;
	private transient Texture texture;
	private int health = 100;

	public Dweller(String name, String surname, GENDER gender, int strength, int intelligence, int charisma, int creativity) {
		this.completeName = name + ", " + surname;
		this.gender = gender;
		this.strength = strength;
		this.intelligence = intelligence;
		this.charisma = charisma;
		this.creativity = creativity;
	}

	public String getName() {
		return completeName;
	}

	public GENDER getGender() {
		return gender;
	}

	private void repopulate() {
		texture = TextureLoader.getTexture(gender.name().toLowerCase() + ".png");
	}

	@Contract(pure = true)
	private boolean isTextureNull() {
		return texture == null;
	}

	@Nullable
	public ArmorItem getArmor() {
		return items.getOne();
	}

	public void setArmor(ArmorItem armor) {
		if (items.getOne() != null) GameStorage.INSTANCE.armors.add(items.getOne());
		items.setOne(armor);
		GameStorage.INSTANCE.armors.remove(armor);
	}

	@Nullable
	public WeaponItem getWeapon() {
		return items.getTwo();
	}

	public void setWeapon(WeaponItem weapon) {
		if (items.getTwo() != null) GameStorage.INSTANCE.weapons.add(items.getTwo());
		items.setTwo(weapon);
		GameStorage.INSTANCE.weapons.remove(weapon);
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
		return completeName + ", " + strength + ", " + intelligence + ", " + charisma + ", " + creativity + ", " + health + "%";
	}

	public void heal() {
		if (GameStorage.INSTANCE.meds > 0) {
			health = 100;
			GameStorage.INSTANCE.meds--;
		} else {
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("No more meds available", 2));
		}
		LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
	}

	public void damage(int amount) {
		health -= amount;
	}

	public int getHealth() {
		return health;
	}

	public enum GENDER {MALE, FEMALE}
}
