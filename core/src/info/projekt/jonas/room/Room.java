package info.projekt.jonas.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.room.capabilities.IConsume;
import info.projekt.jonas.room.capabilities.IProduce;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.LimitedArrayList;
import info.projekt.jonas.util.TextureLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Arrays;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_WIDTH;

/**
 * @author Jonas
 */
public abstract class Room implements Serializable {

	private int x, y;
	private LimitedArrayList<Dweller> dwellers = new LimitedArrayList<>(4);

	private transient Texture[] textures = new Texture[]{
			TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/1.png"),
			TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/2.png"),
			TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/3.png")
	};
	private int level = 0;

	@Nullable
	public static Room clone(@NotNull Class<? extends Room> room) {
		try {
			return room.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addDweller(Dweller dweller) {
		dwellers.add(dweller);
	}

	public boolean isSpaceRemaining() {
		return dwellers.isSpace();
	}

	private void repopulate() {
		textures = new Texture[]{
				TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/1.png"),
				TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/1.png"),
				TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/1.png")
		};
	}

	@Contract(pure = true)
	private boolean isTextureNull() {
		return textures == null || textures[0] == null;
	}

	public Texture getTexture() {
		if (isTextureNull()) repopulate();
		return textures[0];
	}

	public Vector2 getPosition() {
		return new Vector2(x, y);
	}

	public void draw(@NotNull SpriteBatch batch, int x, int y) {
		this.x = x;
		this.y = y;
		if (isTextureNull()) repopulate();
		batch.draw(textures[level], x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
		for (int i = 0; i < dwellers.size(); i++)
			if (dwellers.get(i) != null)
				batch.draw(dwellers.get(i).getTexture(), x * CELL_WIDTH + i * 100 + 10, y * CELL_HEIGHT);
	}

	public int getLevel() {
		return level;
	}

	public boolean isUpgradeable() {
		if(level == 2) {
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("Already at max level", 2));
			return false;
		}
		if(Arrays.stream(this.getClass().getAnnotations()).noneMatch(annotation -> annotation instanceof Buildable)) {
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("That room can't be upgraded", 2));
			return false;
		}
		else if(GameStorage.INSTANCE.currency < ((Buildable) this.getClass().getAnnotations()[0]).cost()) {
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("Not enough money", 2));
			return false;
		}
		return true;
	}

	public void upgrade() {
		level++;
		GameStorage.INSTANCE.currency -= ((Buildable) this.getClass().getAnnotations()[0]).cost();
	}

	public LimitedArrayList<Dweller> getDwellers() {
		return dwellers;
	}

	public void generate() {
		if(this instanceof IProduce) {((IProduce) this).produce();}
		if(this instanceof IConsume) {if(((IConsume)this).enoughResources()) ((IConsume)this).consume();}
	}
}