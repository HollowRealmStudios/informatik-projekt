package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import org.jetbrains.annotations.NotNull;

public class Label extends Widget {

	private String text;
	private final BitmapFont font;
	public int x, y;

	public Label(int x, int y, String text, BitmapFont font) {
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y;
	}

	public Label centerY() {
		Label l = this;
		l.y = Gdx.graphics.getHeight() / 2 - WidgetUtil.getTextHeight(font, text) / 2;
		return l;
	}

	public Label centerX() {
		Label l = this;
		l.x = Gdx.graphics.getWidth() / 2 - WidgetUtil.getTextWidth(font, text) / 2;
		return l;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void draw(@NotNull SpriteBatch batch) {
		batch.begin();
		font.draw(batch, text, x, y);
		batch.end();
	}

	@Override
	public void debug(@NotNull ShapeRenderer renderer) {
		renderer.setColor(Color.RED);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.rect(x, y - WidgetUtil.getTextHeight(font, text), WidgetUtil.getTextWidth(font, text), WidgetUtil.getTextHeight(font, text));
		renderer.end();
	}
}
