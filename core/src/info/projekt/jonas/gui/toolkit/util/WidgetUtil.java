package info.projekt.jonas.gui.toolkit.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class WidgetUtil {

	public static int getTextWidth(BitmapFont font, String text) {
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, text);
		return (int) layout.width;
	}

	public static int getTextHeight(BitmapFont font) {
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, "lorem ipsum dolor sit amet");
		return (int) layout.height;
	}
}
