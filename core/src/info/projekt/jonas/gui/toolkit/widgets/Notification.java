package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.utils.Timer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class Notification extends Label {

	private boolean locked = false;

	public Notification() {
		super(0, 70, "", FONT);
		centerX();
	}

	private void show(NotificationRequest request) {
		setText(request.notification);
		centerX();
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				setText("");
				locked = false;
			}
		}, request.duration);
	}

	public void onActiveUpdate() {
		if(!locked) {
			if (!LayerSupervisor.NOTIFICATION_STACK.isEmpty()) {
				show(LayerSupervisor.NOTIFICATION_STACK.pop());
				locked = true;
			}
		}
	}
}
