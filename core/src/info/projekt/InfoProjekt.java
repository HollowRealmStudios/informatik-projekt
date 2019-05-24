package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.gui.CameraManager;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;

import java.io.IOException;

public class InfoProjekt extends Game {

	public static GameStorage GAME_STORAGE;
	public static SpriteBatch batch;
	public static ShapeRenderer renderer;
	public static GameScreen gameScreen;
	public static CameraManager manager;

	public static void loadGame() {
		try {
			GAME_STORAGE = StorageHandler.loadGame();
		} catch (IOException | ClassNotFoundException e) {
			newGame();
		}
	}

	public static void newGame() {
		GAME_STORAGE = new GameStorage();
		GAME_STORAGE.debug();
	}

	public void changeScreen(com.badlogic.gdx.Screen screen) {
		setScreen(screen);
		if (screen instanceof InputProcessor) Gdx.input.setInputProcessor((InputProcessor) screen);
		render();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		TitleScreen titleScreen = new TitleScreen(this);
		gameScreen = new GameScreen(this);
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

