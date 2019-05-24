package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.gui.RenderUtils;

import static info.projekt.InfoProjekt.batch;

public class Screen implements com.badlogic.gdx.Screen {


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		RenderUtils.drawBackground(batch, new Texture("Background.png"));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}





