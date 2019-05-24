package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.gui.CameraManager;
import info.projekt.jonas.gui.GameScreen;

public class InfoProjekt extends Game {

	public static SpriteBatch batch;
	public static ShapeRenderer renderer;
	private TitleScreen titleScreen;
	public static GameScreen gameScreen;
	public static CameraManager manager;

	public void changeScreen(com.badlogic.gdx.Screen screen) {
		setScreen(screen);
		if (screen instanceof InputProcessor) Gdx.input.setInputProcessor((InputProcessor) screen);
		render();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		titleScreen = new TitleScreen(this);
		gameScreen = new GameScreen();
		manager = new CameraManager();
		Gdx.input.setInputProcessor((InputProcessor) titleScreen);
		setScreen(titleScreen);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(manager.getMatrix());
		getScreen().render(0);
	}
}

