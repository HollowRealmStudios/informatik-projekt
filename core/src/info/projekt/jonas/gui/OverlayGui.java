package info.projekt.jonas.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static info.projekt.InfoProjekt.manager;

public abstract class OverlayGui extends Gui {

	public OverlayGui(Object source) {
		super(source);
	}

	@Override
	public void paint(SpriteBatch batch, ShapeRenderer renderer) {
		super.paint(batch, renderer);
	}
}
