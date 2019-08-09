package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class Notification extends Label {

	private boolean locked = false;

	public Notification() {
		super(0, 70, "", FONT);
		centerX();
	}

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
		setText(request.notification);
		centerX();
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				setText("");
				releaseLock();
			}
		}, request.duration);
	}

	public void onActiveUpdate() {
		if (!isLocked()) {
			if (!LayerSupervisor.NOTIFICATION_STACK.isEmpty()) {
				show(LayerSupervisor.NOTIFICATION_STACK.pop());
				attachLock();
			}
		}
	}
}
