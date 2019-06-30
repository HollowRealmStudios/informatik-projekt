package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.Registry;
import info.projekt.jonas.gui.CameraManager;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.GuiProvider;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;
import info.projekt.jonas.threads.WorkThread;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Jonas
 * @author Christoph
 */
public class InfoProjekt extends Game {

	public static final WorkThread WORK_THREAD = new WorkThread(1000);
	public static GameStorage GAME_STORAGE;
	public static SpriteBatch batch;
	public static ShapeRenderer renderer;
	public static CameraManager cameraManager;
	public static InputMultiplexer multiplexer;

	@Override
	public void create() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		cameraManager = new CameraManager();
		multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		GuiProvider.registerGui(TitleScreen.class);
		try {
			Registry.registerArmors();
			Registry.registerWeapons();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Registry.registerRooms();
		Objects.requireNonNull(GuiProvider.requestGui(TitleScreen.class)).show();
	}

	@Override
	public void render() {
		renderer.setProjectionMatrix(cameraManager.getMatrix());
		batch.setProjectionMatrix(cameraManager.getMatrix());
		//noinspection ConstantConditions
		if (GuiProvider.requestGui(GameScreen.class) != null)
			((GameScreen) GuiProvider.requestGui(GameScreen.class)).render();
		GuiProvider.getGuis().forEach(g -> g.act(Gdx.graphics.getDeltaTime()));
	}

	@Override
	public void dispose() {
		WORK_THREAD.stop();
		try {
			StorageHandler.saveGame(GAME_STORAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

