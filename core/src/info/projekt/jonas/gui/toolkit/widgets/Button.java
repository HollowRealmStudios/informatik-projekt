package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.Gdx;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesMouseInput;
import info.projekt.jonas.gui.toolkit.util.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Button extends Widget implements IHandlesMouseInput {

	public final Rectangle hitbox;
	private final Runnable action;
	private ArrayList<Object> storage = new ArrayList<>();

	protected Button(Runnable action, int x, int y, int width, int height, Object... storage) {
		hitbox = new Rectangle(x, y, width, height);
		this.action = action;
		this.storage.addAll(Arrays.asList(storage));
	}

	@Override
	public boolean onMouseEvent() {
		if (hitbox.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
			action.run();
			return true;
		}
		return false;
	}

	@Override
	public Rectangle getBoundingBox() {
		return hitbox;
	}

	public ArrayList<Object> getStorage() {
		return storage;
	}
}
