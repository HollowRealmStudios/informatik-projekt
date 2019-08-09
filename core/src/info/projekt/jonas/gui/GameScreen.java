package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.KeyManager;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.util.TextureLoader;

import java.util.Objects;

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
	public void handleKeyboard(KeyManager manager) {
		if(manager.getKeys().get(Input.Keys.W)) {
			camera.position.y += 5;
			camera.update();
			return;
		}
		if(manager.getKeys().get(Input.Keys.S)) {
			camera.position.y -= 5;
			camera.update();
			return;
		}
		if(manager.getKeys().get(Input.Keys.A)) {
			camera.position.x -= 5;
			camera.update();
			return;
		}
		if(manager.getKeys().get(Input.Keys.D)) {
			camera.position.x += 5;
			camera.update();
		}
	}

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer) {
		cameraBatch.setProjectionMatrix(camera.combined);
		cameraBatch.begin();
		for (int i = 0; i < 5; i++) {
			cameraBatch.draw(Objects.requireNonNull(TextureLoader.getTexture("armor.png")), i * 64, 500);
		}
		cameraBatch.end();
	}
}
