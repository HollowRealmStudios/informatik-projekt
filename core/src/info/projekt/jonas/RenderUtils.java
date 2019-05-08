package info.projekt.jonas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public final class RenderUtils {

    public static void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
