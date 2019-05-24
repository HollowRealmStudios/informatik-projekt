package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.Screen;

public class InfoProjekt extends Game {

	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private final Screen screen = new Screen();

	@Override
	public void create() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		setScreen(screen);
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(new Texture("Background.png"), 100, 100);
		batch.end();
	}
}

