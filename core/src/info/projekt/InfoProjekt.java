package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
<<<<<<< HEAD
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.Registry;
import info.projekt.jonas.gui.CameraManager;
import info.projekt.jonas.gui.GameScreenGui;
import info.projekt.jonas.gui.GuiProvider;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;
import info.projekt.jonas.threads.WorkThread;
=======
import com.google.common.base.Stopwatch;
import info.projekt.jonas.gui.BuildGui;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.RenderUtils;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.util.TextureLoader;
>>>>>>> Began entire rewrite of codebase

import java.awt.*;

/**
 * @author Jonas
 * @author Christoph
 */
public class InfoProjekt extends Game {

	private final Stopwatch stopwatch = Stopwatch.createUnstarted();
	private LayerSupervisor supervisor;

	private void init() {
		TextureLoader.loadTextures();
		supervisor = new LayerSupervisor();
	}

	@Override
	public void create() {
<<<<<<< HEAD
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		cameraManager = new CameraManager();
		multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		GuiProvider.registerGui(TitleScreen.class);
		try {
			Registry.registerArmors();
			Registry.registerWeapons();
			Registry.registerRooms();
			Registry.registerComponents();
			Registry.registerRecipes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Objects.requireNonNull(GuiProvider.requestGui(TitleScreen.class)).show();
		Registry.debug();
=======
		init();
		supervisor.setLayer(new BuildGui(), 2);
		supervisor.setLayer(new GameScreen(), 0);
		supervisor.getLayer(2).setDebug(false);
>>>>>>> Began entire rewrite of codebase
	}

	@Override
	public void render() {
<<<<<<< HEAD
		renderer.setProjectionMatrix(cameraManager.getMatrix());
		batch.setProjectionMatrix(cameraManager.getMatrix());
		//noinspection ConstantConditions
		if (GuiProvider.requestGui(GameScreenGui.class) != null)
			((GameScreenGui) GuiProvider.requestGui(GameScreenGui.class)).render();
		GuiProvider.getGuis().forEach(g -> g.act(Gdx.graphics.getDeltaTime()));
=======
		stopwatch.start();
		System.out.println(Gdx.input.getX() + " / " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
		RenderUtils.clearScreen(Color.BLACK);
		supervisor.update();
		supervisor.draw();
		stopwatch.stop();
		//System.out.println("Render of frame took " + stopwatch.elapsed(TimeUnit.NANOSECONDS) + "ns, max. fps: " +1000000 / stopwatch.elapsed(TimeUnit.MICROSECONDS));
		stopwatch.reset();
>>>>>>> Began entire rewrite of codebase
	}

	@Override
	public void dispose() {

	}
}

