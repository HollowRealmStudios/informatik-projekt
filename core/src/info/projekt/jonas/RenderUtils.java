package info.projekt.jonas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * @author Jonas
 */
public final class RenderUtils {

	public static void clearScreen() {
		Gdx.gl.glClearColor(205, 133, 63, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

}
