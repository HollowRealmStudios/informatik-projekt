package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.util.Rectangle;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class Notification extends Widget {

	private final Label label = new Label(0, 70, "", FONT).centerX();

	private boolean locked = false;

	private void attachLock() {
		locked = true;
	}

	private void releaseLock() {
		locked = false;
	}

	@Contract(pure = true)
	private boolean isLocked() {
		return locked;
	}

	private void show(@NotNull NotificationRequest request) {
		label.setText(request.notification);
		label.centerXManually();
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				label.setText("");
				releaseLock();
			}
		}, request.duration);
	}

	public void update() {
		if (!isLocked()) {
			if (!LayerSupervisor.NOTIFICATION_QUEUE.isEmpty()) {
				show(LayerSupervisor.NOTIFICATION_QUEUE.remove());
				attachLock();
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		label.draw(batch);
	}

	@Override
	public void debug(ShapeRenderer renderer) {
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.PURPLE);
		renderer.rect(label.x, label.y - WidgetUtil.getTextHeight(FONT, label.getText()), WidgetUtil.getTextWidth(FONT, label.getText()), WidgetUtil.getTextHeight(FONT, label.getText()));
		renderer.end();
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(label.x, label.y - WidgetUtil.getTextHeight(FONT, label.getText()), WidgetUtil.getTextWidth(FONT, label.getText()), WidgetUtil.getTextHeight(FONT, label.getText()));
	}
}
