package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.util.Rectangle;
import info.projekt.jonas.gui.toolkit.widgets.Widget;

public class Image extends Widget {

	private final Texture texture;
	private Rectangle hitbox;

	public Image(int x, int y, int width, int height, Texture texture) {
		this.texture = texture;
		hitbox = new Rectangle(x, y, width, height);
	}

	@Override
	public Rectangle getBoundingBox() {
		return hitbox;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.begin();
		batch.draw(texture, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		batch.end();
	}

	@Override
	public void debug(ShapeRenderer renderer) {
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.CYAN);
		renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		renderer.end();
	}
}
