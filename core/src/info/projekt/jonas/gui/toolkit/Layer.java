package info.projekt.jonas.gui.toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesKeyboardInput;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesMouseInput;
import info.projekt.jonas.gui.toolkit.widgets.Widget;
import info.projekt.jonas.gui.toolkit.widgets.button.Button;

import java.util.ArrayList;
import java.util.Collection;

public class Layer {

	private boolean debug = true;

	private ArrayList<Widget> widgets = new ArrayList<>();

	protected void addWidget(Widget widget) {
		widgets.add(widget);
	}

	public void addWidgets(Collection<Widget> widgets) {
		this.widgets.addAll(widgets);
	}

	public final void update() {
		if (this instanceof IHandlesActiveUpdates) ((IHandlesActiveUpdates) this).onUpdate();
	}

	public boolean handleKeyboard(KeyManager manager) {
		for (Widget widget : widgets) {
			if (widget instanceof IHandlesKeyboardInput)
				return ((IHandlesKeyboardInput) widget).onKeyEvent(manager.getKeys());
		}
		return false;
	}

	public boolean handleMouse() {
		for (Widget widget : widgets) {
			if (widget instanceof IHandlesMouseInput) {
				if (((IHandlesMouseInput) widget).onMouseEvent()) {
					return true;
				}
			}
		}
		return false;
	}

	public void draw(SpriteBatch batch, ShapeRenderer renderer) {
		for (Widget widget : widgets) {
			widget.draw(batch);
			if (debug) widget.debug(renderer);
		}
	}

}
