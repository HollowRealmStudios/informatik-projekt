package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Jonas
 */
public class RenderUtils {

	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int HALF_WIDTH = WIDTH / 2;
	public static final int HALF_HEIGHT = HEIGHT / 2;
	public static final int CELL_WIDTH = 400;
	public static final int CELL_HEIGHT = 200;
	public static final Skin SKIN = new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json"));
	public static final BitmapFont FONT = new BitmapFont(Gdx.files.internal("Ubuntu.fnt"));
	public static final Label.LabelStyle STYLE = new Label.LabelStyle(FONT, com.badlogic.gdx.graphics.Color.BLACK);
	public static boolean guiOpen = false;

	/**
	 * Clears the screen
	 *
	 * @param c The background color
	 */
	public static void clearScreen(@NotNull Color c) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, 1f);
	}

	public static void hideAllGuis() {
		GuiProvider.getGuis().forEach(Gui::hide);
	}

	public static void drawBackground(@NotNull SpriteBatch batch, Texture texture) {
		batch.begin();
		batch.draw(texture, 0, 0, WIDTH, HEIGHT);
		batch.end();
	}

	public static void hideAllGuisExcept(Class<? extends Gui> gui) {
		GuiProvider.getGuis().forEach(g -> {
			if (!g.getClass().equals(gui)) g.hide();
		});
	}
}
