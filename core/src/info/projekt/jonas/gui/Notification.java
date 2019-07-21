package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;

import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

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
		setPosition(HALF_WIDTH - getWidth() - 100, 100);
		setVisible(true);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				setVisible(false);
			}
		}, i);
	}
}
