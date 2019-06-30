package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.*;

/**
 * @author Jonas
 */
public class RenderUtils {

	public static boolean guiOpen = false;
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int HALF_WIDTH = WIDTH / 2;
    public static final int HALF_HEIGHT = HEIGHT / 2;
    public static final int CELL_WIDTH = 400;
    public static final int CELL_HEIGHT = 200;

    public static final Skin SKIN = new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json"));

    /**
     * Clears the screen
     *
     * @param c The background color
     */
    public static void clearScreen(Color c) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, 1f);
    }

}
