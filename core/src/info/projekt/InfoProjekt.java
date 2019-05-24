package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.Screen;
import info.projekt.jonas.gui.GameScreen;

public class InfoProjekt extends Game {

	public static SpriteBatch batch;
	private static ShapeRenderer renderer;
	private Screen screen;
	private GameScreen gameScreen;

	@Override
	public void create() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		screen = new Screen();
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void render() {
		Gdx.input.setInputProcessor((InputProcessor) getScreen());
		getScreen().render(0);
	}
}

