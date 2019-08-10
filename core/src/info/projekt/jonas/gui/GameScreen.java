package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import info.projekt.jonas.gui.toolkit.KeyManager;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.room.Room;
import info.projekt.jonas.storage.GameStorage;
import org.jetbrains.annotations.NotNull;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.CELL_WIDTH;

public class GameScreen extends Layer {

	private final SpriteBatch cameraBatch;
	private final OrthographicCamera camera;

	public GameScreen() {
		cameraBatch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cameraBatch.setProjectionMatrix(camera.combined);
		camera.position.set(0, 0, 1);
		camera.update();

	}

	@Override
	public boolean handleMouse() {
		System.out.println("handling...");
		Vector2 pos = unproject();
		try {
			Room room = GameStorage.INSTANCE.getRoomAt((int) Math.floor(pos.x / CELL_WIDTH), (int) Math.floor(pos.y / CELL_HEIGHT));
			if (room != null) {
				RoomGui.room = room;
				LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(RoomGui.class, LayerSupervisor.GUI_LAYER, true));
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
		return true;
	}

	@Override
	public void handleKeyboard(@NotNull KeyManager manager) {
		if (manager.getKeys().get(Input.Keys.W)) {
			camera.position.y += 20;
			camera.update();
		}
		if (manager.getKeys().get(Input.Keys.S)) {
			camera.position.y -= 20;
			camera.update();
		}
		if (manager.getKeys().get(Input.Keys.A)) {
			camera.position.x -= 20;
			camera.update();
		}
		if (manager.getKeys().get(Input.Keys.D)) {
			camera.position.x += 20;
			camera.update();
		}
	}

	@Override
	public void draw(SpriteBatch batch, ShapeRenderer renderer) {
		cameraBatch.setProjectionMatrix(camera.combined);
		cameraBatch.begin();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 15; y++) {
				if (GameStorage.INSTANCE.getRoomAt(x, y) != null)
					GameStorage.INSTANCE.getRoomAt(x, y).draw(cameraBatch, x, y);
			}
		}
		cameraBatch.end();
	}

	private Vector2 unproject() {
		Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		return new Vector2(pos.x, pos.y);
	}
}
