package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.jetbrains.annotations.NotNull;

public class ImageButton extends Button {

	private final Texture texture;

	public ImageButton(Runnable action, int x, int y, int width, int height, Texture texture, Object... storage) {
		super(action, x, y, width, height, storage);
		this.texture = texture;
	}

	public ImageButton centerX() {
		ImageButton button = this;
		button.hitbox.x = Gdx.graphics.getWidth() / 2 - button.hitbox.width / 2;
		return button;
	}

	@Override
	public void draw(@NotNull SpriteBatch batch) {
		batch.begin();
		batch.draw(texture, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		batch.end();
	}

	@Override
	public void debug(@NotNull ShapeRenderer renderer) {
		renderer.setColor(Color.YELLOW);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		renderer.setColor(Color.GREEN);
		renderer.circle(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 5);
		renderer.end();
	}
}
