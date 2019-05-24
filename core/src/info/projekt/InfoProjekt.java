package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.Screen;
import info.projekt.jonas.gui.CameraManager;
import info.projekt.jonas.gui.GameScreen;

public class InfoProjekt extends Game {

	public static SpriteBatch batch;
	private static ShapeRenderer renderer;
	private Screen screen;
	private GameScreen gameScreen;
	public static CameraManager manager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		screen = new Screen();
		gameScreen = new GameScreen();
		manager = new CameraManager();
		setScreen(gameScreen);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(manager.getMatrix());
		getScreen().render(0);
	}
}

