package info.projekt.jonas.gui.toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;

public class KeyManager implements InputProcessor {

	private final HashMap<Integer, Boolean> keys = new HashMap<>();
	private boolean mouseClicked = false;

	public KeyManager() {
		for (int i = -1; i <= 255; i++) {
			keys.put(i, false);
		}
		Gdx.input.setInputProcessor(this);
	}

	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public HashMap<Integer, Boolean> getKeys() {
		return keys;
	}

	@Override
	public boolean keyDown(int keycode) {
		keys.put(keycode, true);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		keys.put(keycode, false);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void update() {
		mouseClicked = Gdx.input.justTouched();
	}
}
