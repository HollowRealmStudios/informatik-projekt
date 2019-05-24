package info.projekt.jonas.gui;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import info.projekt.jonas.storage.GameStorage;

import java.awt.*;

import static info.projekt.InfoProjekt.batch;

public class GameScreen extends InputAdapter implements Screen {

	private GameStorage storage = new GameStorage();

	public GameScreen() {
		storage.debug();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		RenderUtils.clearScreen(new Color(49, 144, 175));
		RenderUtils.drawRooms(storage.getRooms(), batch);
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

	@Override
	public boolean keyDown(int keycode) {
		System.out.println(keycode);
		return false;
	}
}
