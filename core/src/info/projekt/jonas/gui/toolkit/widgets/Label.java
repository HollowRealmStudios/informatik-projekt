package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.util.Rectangle;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import org.jetbrains.annotations.NotNull;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class Label extends Widget {

	private String text;
	private final BitmapFont font;
	private boolean centerX, centerY;
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
		centerY = true;
		return l;
	}

	public Label centerX() {
		Label l = this;
		l.x = Gdx.graphics.getWidth() / 2 - WidgetUtil.getTextWidth(font, text) / 2;
		centerX = true;
		return l;
	}

	protected void centerYManually() {
		y = Gdx.graphics.getHeight() / 2 - WidgetUtil.getTextHeight(font, text) / 2;
	}

	protected void  centerXManually() {
		x = Gdx.graphics.getWidth() / 2 - WidgetUtil.getTextWidth(font, text) / 2;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		if(centerX) centerXManually();
		if(centerY) centerYManually();
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

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(x, y, WidgetUtil.getTextWidth(FONT, text), WidgetUtil.getTextHeight(FONT, text));
	}
}
