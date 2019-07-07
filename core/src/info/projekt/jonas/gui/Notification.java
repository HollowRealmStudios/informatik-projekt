package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;

/**
 * @author Jonas
 */
public class Notification extends Label {


	public Notification(CharSequence text, LabelStyle style) {
		super(text, style);
		setVisible(false);
	}

	public void show(String s, int i) {
		setVisible(false);
		setText(s);
		setVisible(true);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				setVisible(false);
			}
		}, i);
	}
}
