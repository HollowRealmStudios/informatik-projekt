package info.projekt.jonas.gui.toolkit.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Widget {

	public abstract void draw(SpriteBatch batch);

	public abstract void debug(ShapeRenderer renderer);

}
