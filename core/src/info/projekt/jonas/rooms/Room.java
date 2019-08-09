package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.util.StreamArray;
import info.projekt.jonas.util.TextureLoader;

import java.io.Serializable;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_WIDTH;

/**
 * @author Jonas
 */
public abstract class Room implements Serializable {

	private StreamArray<Dweller> dwellers = new StreamArray<>(new Dweller[4]);
	private final transient Texture[] textures = TextureLoader.getRoomTextures(this.getClass());
	private int level;

	public Texture getTexture() {
		return textures[0];
	}

	public void draw(SpriteBatch batch, int x, int y) {
		batch.begin();
		batch.draw(textures[level], x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
		batch.end();
	}

}