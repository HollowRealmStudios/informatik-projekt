package info.projekt.jonas.room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.util.StreamArray;
import info.projekt.jonas.util.TextureLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_WIDTH;

/**
 * @author Jonas
 */
public abstract class Room implements Serializable {

	private StreamArray<Dweller> dwellers = new StreamArray<>(new Dweller[4]);

	private transient Texture[] textures = new Texture[]{
			TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/1.png"),
			TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/2.png"),
			TextureLoader.getTextureUnsafe("textures/" + this.getClass().getSimpleName() + "/3.png")
	};
	private int level;

	public void addDweller(Dweller dweller) {
		dwellers.add(dweller);
	}

	public boolean isSpaceRemaining() {
		return dwellers.getCarriage() - 1 != dwellers.size();
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

	public void draw(@NotNull SpriteBatch batch, int x, int y) {
		if(isTextureNull()) repopulate();
		batch.begin();
		batch.draw(textures[level], x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
		batch.end();
	}

}