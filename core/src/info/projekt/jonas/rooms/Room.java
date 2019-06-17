package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.dwellers.Dweller;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

import static info.projekt.InfoProjekt.GAME_STORAGE;

public class Room implements Serializable {

	public enum PRODUCT {WATER, FOOD, ENERGY, OTHER, NONE}

	private int cost = 0;
	private Dweller[] dwellers = new Dweller[4];
	private PRODUCT product = PRODUCT.NONE;
	private final String name;
	private int level;
	private transient ArrayList<Texture> textures = new ArrayList<>();
	private ArrayList<String> textureNames = new ArrayList<>();

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setProduct(PRODUCT product) {
		this.product = product;
	}

	public PRODUCT getProduct() {
		return product;
	}

	public int getCost() {
		return cost;
	}

	public void produce() {
		switch (product) {
			case FOOD:
				for (Dweller dweller : dwellers) if (dweller != null) GAME_STORAGE.food += dweller.getCreativity() * 2;
				break;
			case ENERGY:
				for (Dweller dweller : dwellers) if (dweller != null) GAME_STORAGE.energy += dweller.getStrength() * 2;
				break;
			case WATER:
				for (Dweller dweller : dwellers)
					if (dweller != null) GAME_STORAGE.food += dweller.getIntelligence() * 2;
				break;
		}
	}

	public void consume() {
		GAME_STORAGE.energy -= GAME_STORAGE.energy >= 5 ? 5 : 0;
		GAME_STORAGE.water -= GAME_STORAGE.water >= 5 ? 5 : 0;
		switch (product) {
			case FOOD:
				for (Dweller dweller : dwellers)
					if (dweller != null) GAME_STORAGE.food -= GAME_STORAGE.food >= 15 ? 15 : 0;
				break;
			case ENERGY:
				for (Dweller dweller : dwellers)
					if (dweller != null) GAME_STORAGE.energy -= GAME_STORAGE.energy >= 15 ? 15 : 0;
				break;
			case WATER:
				for (Dweller dweller : dwellers)
					if (dweller != null) GAME_STORAGE.water -= GAME_STORAGE.water >= 15 ? 15 : 0;
				break;
		}
	}

	public void onTick() {

	}

	public void onPlace() {

	}

	public void onUpgrade() {

	}

	public void onNewDweller() {

	}

	protected Room(@NotNull String name, @NotNull String texture, @NotNull String... textures) {
		this.name = name;
		this.textures.add(new Texture(texture));
		textureNames.add(texture);
		for (String s : textures) {
			this.textures.add(new Texture(s));
			textureNames.add(s);
		}
	}

	public Dweller[] getDwellers() {
		return dwellers;
	}

	public Texture getTexture() {
		if (textures == null) {
			textures = new ArrayList<>();
			for (String s : textureNames) {
				textures.add(new Texture(s));
			}
		}
		return textures.get(level - 1);
	}

	public boolean upgradable() {
		return level < textures.size();
	}

	public void upgrade() {
		if (level == textures.size())
			throw new IllegalArgumentException("A level " + textures.size() + " room can't be upgraded any further");
		level++;
	}
}