package info.projekt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import info.projekt.jonas.CameraManager;
import info.projekt.jonas.RenderUtils;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.storage.GameStorage;

import java.awt.*;

public class InfoProjekt extends ApplicationAdapter {

	private GameStorage storage;
	private SpriteBatch batch;
	private CameraManager manager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		Texture debugRoom = new Texture("room_debug.png");
		manager = new CameraManager();
		manager.setZoom(1.8f);
		storage = new GameStorage();
		storage.debug();
	}

	@Override
	public void render() {
		processInput();
		RenderUtils.clearScreen(new Color(43, 18, 11));
		batch.setProjectionMatrix(manager.getMatrix());
		RenderUtils.drawRooms(storage.getRooms(), batch);
	}

	private void processInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) manager.translateRelative(new Vector2(0, 15));
		else if (Gdx.input.isKeyPressed(Input.Keys.S)) manager.translateRelative(new Vector2(0, -15));
		if (Gdx.input.isKeyPressed(Input.Keys.A)) manager.translateRelative(new Vector2(-15, 0));
		else if (Gdx.input.isKeyPressed(Input.Keys.D)) manager.translateRelative(new Vector2(15, 0));
		if (Gdx.input.isKeyPressed(Input.Keys.F1)) manager.setZoom(manager.getZoom() + 0.05f);
		else if (Gdx.input.isKeyPressed(Input.Keys.F2)) manager.setZoom(manager.getZoom() - 0.05f);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}

