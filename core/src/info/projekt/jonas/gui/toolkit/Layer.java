package info.projekt.jonas.gui.toolkit;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesKeyboardInput;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesMouseInput;
import info.projekt.jonas.gui.toolkit.widgets.Widget;

import java.util.ArrayList;
import java.util.Collection;

public class Layer {

	private final ArrayList<Widget> widgets = new ArrayList<>();

	protected void addWidget(Widget widget) {
		widgets.add(widget);
	}

	protected void addWidgets(Collection<Widget> widgets) {
		this.widgets.addAll(widgets);
	}

	protected void removeWidget(Widget widget) { widgets.remove(widget); }

	protected void removeWidgets(Collection<Widget> widgets) { this.widgets.removeAll(widgets); }

	protected void removeAll() {
		widgets.clear();
	}

	public final void update() {
		if (this instanceof IHandlesActiveUpdates) ((IHandlesActiveUpdates) this).onActiveUpdate();
	}

	public void handleKeyboard(KeyManager manager) {
		for (Widget widget : widgets) {
			if (widget instanceof IHandlesKeyboardInput) {
				((IHandlesKeyboardInput) widget).onKeyEvent(manager.getKeys());
				return;
			}
		}
	}

	public void handleMouse() {
		for (Widget widget : widgets) {
			if (widget instanceof IHandlesMouseInput) {
				if (((IHandlesMouseInput) widget).onMouseEvent()) {
					return;
				}
			}
		}
	}

	public void draw(SpriteBatch batch, ShapeRenderer renderer) {
		for (Widget widget : widgets) {
			widget.draw(batch);
			boolean debug = false;
			if (debug) widget.debug(renderer);
		}
	}

}
