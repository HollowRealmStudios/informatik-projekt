package info.projekt.jonas.gui.toolkit;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesKeyboardInput;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesMouseInput;
import info.projekt.jonas.gui.toolkit.util.Rectangle;
import info.projekt.jonas.gui.toolkit.widgets.Widget;
import info.projekt.jonas.util.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.WIDTH;

public class Layer {

	private final ArrayList<Widget> widgets = new ArrayList<>();

	protected void addWidget(Widget widget) {
		widgets.add(widget);
	}

	protected void addWidgets(Collection<Widget> widgets) {
		this.widgets.addAll(widgets);
	}

	protected void removeWidget(Widget widget) {
		widgets.remove(widget);
	}

	protected void removeWidgets(Collection<Widget> widgets) {
		this.widgets.removeAll(widgets);
	}

	protected void removeAll() {
		widgets.clear();
	}

	public void update() {
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
			if (Configuration.Debug.GUI_DEBUG) widget.debug(renderer);
		}
	}

	public ArrayList<Widget> getWidgets() {
		return widgets;
	}

	public Rectangle getBackgroundSize() {
		final Rectangle rectangle = new Rectangle(1000000, 1000000, 0, 0);
		widgets.forEach(widget -> {
			if (widget.getBoundingBox().x < rectangle.x) rectangle.x = widget.getBoundingBox().x;
			if (widget.getBoundingBox().y < rectangle.y) rectangle.y = widget.getBoundingBox().y;
		});
		widgets.forEach(widget -> {
			if (widget.getBoundingBox().x + widget.getBoundingBox().width > rectangle.x + rectangle.width)
				rectangle.width = widget.getBoundingBox().x + widget.getBoundingBox().width - rectangle.x;
			if (widget.getBoundingBox().y + widget.getBoundingBox().height > rectangle.y + rectangle.height)
				rectangle.height = widget.getBoundingBox().y + widget.getBoundingBox().height - rectangle.y;
		});
		rectangle.x = rectangle.x - Configuration.BACKGROUND_MARGIN;
		rectangle.y = rectangle.y - Configuration.BACKGROUND_MARGIN;
		rectangle.width = rectangle.width + 2 * Configuration.BACKGROUND_MARGIN;
		rectangle.height = rectangle.height + 2 * Configuration.BACKGROUND_MARGIN;
		return rectangle;
	}

}
