package info.projekt.jonas.gui.toolkit.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	public static final BitmapFont FONT = new BitmapFont(Gdx.files.internal("Ubuntu.fnt"));

	public static void clearScreen(@NotNull Color c) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, 1f);
	}
}
