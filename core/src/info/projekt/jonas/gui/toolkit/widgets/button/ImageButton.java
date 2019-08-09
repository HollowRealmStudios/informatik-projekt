package info.projekt.jonas.gui.toolkit.widgets.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ImageButton extends Button {

	private Texture texture;

	public ImageButton(Runnable action, int x, int y, Texture texture) {
		super(action, x, y, texture.getWidth(), texture.getHeight());
		this.texture = texture;
	}

	public ImageButton(Runnable action, int x, int y, int width, int height, Texture texture) {
		super(action, x, y, width, height);
		this.texture = texture;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.begin();
		batch.draw(texture, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		batch.end();
	}

	@Override
	public void debug(ShapeRenderer renderer) {
		renderer.setColor(Color.RED);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		renderer.setColor(Color.GREEN);
		renderer.circle(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 5);
		renderer.end();
	}
}
