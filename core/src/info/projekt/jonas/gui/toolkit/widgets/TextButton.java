package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import org.jetbrains.annotations.NotNull;

public class TextButton extends Button {

	private final String text;
	private final BitmapFont font;
	private final int x;
	private final int y;

	public TextButton(Runnable action, int x, int y, String text, BitmapFont font, Object... storage) {
		super(action, x, y, WidgetUtil.getTextWidth(font, text), WidgetUtil.getTextHeight(font, text), storage);
		this.text = text;
		this.font = font;
		this.x = x;
		this.y = y + WidgetUtil.getTextHeight(font, text);
	}

	public TextButton centerX() {
		TextButton button = this;
		button.hitbox.x = Gdx.graphics.getWidth() / 2 - button.hitbox.width / 2;
		return button;
	}

	@Override
	public void draw(@NotNull SpriteBatch batch) {
		batch.begin();
		font.draw(batch, text, x, y);
		batch.end();
	}

	@Override
	public void debug(@NotNull ShapeRenderer renderer) {
		renderer.setColor(Color.YELLOW);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		renderer.end();
	}
}
