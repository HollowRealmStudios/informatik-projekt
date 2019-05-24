package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import info.projekt.jonas.storage.GameStorage;

import java.awt.*;

import static info.projekt.InfoProjekt.batch;
import static info.projekt.InfoProjekt.manager;

public class GameScreen implements Screen {

	private GameStorage storage = new GameStorage();

	@Override
	public void show() {
		storage.debug();
	}

	@Override
	public void render(float delta) {
		keyDown();
		RenderUtils.clearScreen(new Color(49, 144, 175));
		RenderUtils.drawRooms(storage.getRooms(), batch);
	}

	private void keyDown() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10));
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10, 0));
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10, 0));
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
