package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class Gui extends InputAdapter {

	public Object source;
	protected boolean visible = false;
	protected ArrayList<GuiComponent> COMPONENTS = new ArrayList<GuiComponent>();

	protected Gui(Object source) {
		this.source = source;
	}

	public void addComponent(GuiComponent component) {
		COMPONENTS.add(component);
	}

	public void show() {
		visible = true;
		Gdx.input.setInputProcessor(this);
	}

	public void hide() {
		visible = false;
		Gdx.input.setInputProcessor((InputProcessor) source);
	}

	public abstract void buttonPressed(Button button);

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == 57) {
			for (GuiComponent component : COMPONENTS) {
				if (component instanceof Button) {
					if (((Button) component).clicked(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))) {
						buttonPressed((Button) component);
						return false;
					}
				}
			}
		}
		return false;
	}

	public void paint(SpriteBatch batch, ShapeRenderer renderer) {
		if (visible) {
			for (GuiComponent component : COMPONENTS) {
				if (component instanceof Button || component instanceof Icon || component instanceof Label) {
					component.paint(batch);
				} else if (component instanceof ProgressBar) {
					component.paint(renderer);
				}
			}
		}
	}
}
