package info.projekt.christoph;

//https://stackoverflow.com/questions/25837013/switching-between-screens-libgdx
//https://github.com/libgdx/libgdx/wiki/Extending-the-simple-game


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import info.projekt.InfoProjekt;

/**
 * @author Christoph
 */

public class TitleScreen implements Screen {

	private info.projekt.christoph.Screen game;

	public TitleScreen(final info.projekt.christoph.Screen game) {
			this.game = game;





	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

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


