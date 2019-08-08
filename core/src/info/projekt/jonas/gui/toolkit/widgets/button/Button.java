package info.projekt.jonas.gui.toolkit.widgets.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesMouseInput;
import info.projekt.jonas.gui.toolkit.widgets.Widget;

import java.util.HashMap;

public abstract class Button extends Widget implements IHandlesMouseInput {

	protected Rectangle hitbox;
	private Runnable action;

	protected Button(Runnable action, int x, int y, int width, int height) {
		hitbox = new Rectangle(x, y, width, height);
		this.action = action;
	}

	@Override
	public boolean onMouseEvent() {
		if (hitbox.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
			action.run();
			return true;
		}
		return false;
	}
}
