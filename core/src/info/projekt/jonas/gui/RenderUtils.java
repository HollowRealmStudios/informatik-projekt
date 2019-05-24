package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import info.projekt.jonas.rooms.Room;

import java.awt.*;

/**
 * @author Jonas
 */
public class RenderUtils {

	public static final int HALF_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
	public static final int HALF_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	/**
	 * Clears the screen
	 *
	 * @param c The background color
	 */
	public static void clearScreen(Color c) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, 1f);
	}

	/**
	 * Draw the rooms in a 2D-Array
	 *
	 * @param rooms a 2D-Array of rooms to draw
	 * @param batch the SpriteBatch to draw onto
	 */
	public static void drawRooms(Room[][] rooms, SpriteBatch batch) {
		batch.begin();
		for (int x = 0; x < rooms.length; x++) {
			for (int y = 0; y < rooms[0].length; y++) {
				batch.draw(rooms[x][y].getTexture(), x * Room.WIDTH, y * Room.HEIGHT);
			}
		}
		batch.end();
	}

	public static void drawBackground(SpriteBatch batch, Texture texture) {
		batch.begin();
		batch.draw(texture, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		batch.end();
	}
}
