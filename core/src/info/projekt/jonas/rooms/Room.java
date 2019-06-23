package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.util.LimitedArrayList;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

import static info.projekt.jonas.gui.RenderUtils.*;
import static info.projekt.jonas.gui.RenderUtils.CELL_HEIGHT;

public abstract class Room implements Serializable {

	private int cost = 0;
	protected LimitedArrayList<Dweller> dwellers = new LimitedArrayList<>(4);
	private final String name;
	private int level = 1;
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

	public int getCost() {
		return cost;
	}

	public abstract void produce();

	public abstract void consume();

	public void onTick() {
	}

	public void onPlace() {
	}

	public void onUpgrade() {
	}

	public void onNewDweller() {
	}

	public int getDwellerAmount() {
		return dwellers.size();
	}

	public void addDweller(Dweller dweller) {
		dwellers.add(dweller);
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

	public void removeDweller(Dweller dweller) {
		dwellers.remove(dweller);
	}

	public LimitedArrayList<Dweller> getDwellers() {
		return dwellers;
	}

	private Texture getTexture() {
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

	public void draw(@NotNull SpriteBatch batch, int x, int y) {
		if (!batch.isDrawing()) batch.begin();
		batch.draw(getTexture(), x * CELL_WIDTH, y * CELL_HEIGHT);
		for (int i = 0; i < dwellers.size(); i++) {
			if(dwellers.get(i) != null) batch.draw(dwellers.get(i).getTexture(), x * CELL_WIDTH + i * 100, y * CELL_HEIGHT + 20);
		}
	}
}