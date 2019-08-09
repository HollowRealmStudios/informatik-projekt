package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.KeyManager;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.rooms.EngineRoom;
import info.projekt.jonas.storage.GameStorage;
import org.jetbrains.annotations.NotNull;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_WIDTH;

public class GameScreen extends Layer {

	public static SpriteBatch cameraBatch;
	private final OrthographicCamera camera;

	public GameScreen() {
		cameraBatch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cameraBatch.setProjectionMatrix(camera.combined);
		camera.position.set(0, 0, 1);
		camera.update();

	}

	@Override
	public void handleMouse() {
	}

	@Override
	public void handleKeyboard(@NotNull KeyManager manager) {
		if (manager.getKeys().get(Input.Keys.W)) {
			camera.position.y += 5;
			camera.update();
		}
		if (manager.getKeys().get(Input.Keys.S)) {
			camera.position.y -= 5;
			camera.update();
		}
		if (manager.getKeys().get(Input.Keys.A)) {
			camera.position.x -= 5;
			camera.update();
		}
		if (manager.getKeys().get(Input.Keys.D)) {
			camera.position.x += 5;
			camera.update();
		}
	}

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer) {
		cameraBatch.setProjectionMatrix(camera.combined);
		cameraBatch.begin();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 15; y++) {
				cameraBatch.draw(GameStorage.INSTANCE.getRoomAt(x, y).getTexture(), x * CELL_WIDTH, y * CELL_HEIGHT);
			}
		}
		cameraBatch.end();
	}
}
