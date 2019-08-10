package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.util.Rectangle;

public abstract class Widget {

	public abstract Rectangle getBoundingBox();

	public abstract void draw(SpriteBatch batch);

	public abstract void debug(ShapeRenderer renderer);
}
