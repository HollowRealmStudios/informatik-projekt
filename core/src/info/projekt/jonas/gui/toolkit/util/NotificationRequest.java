package info.projekt.jonas.gui.toolkit.util;

import org.jetbrains.annotations.Contract;

public class NotificationRequest {

	public String notification;
	public float duration;

	@Contract(pure = true)
	public NotificationRequest(String notification, float duration) {
		this.notification = notification;
		this.duration = duration;
	}

}
