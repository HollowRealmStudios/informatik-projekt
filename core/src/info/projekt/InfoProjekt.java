package info.projekt;

import com.badlogic.gdx.Game;
import com.google.common.base.Stopwatch;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.OverlayGui;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.RenderUtils;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.Registry;
import info.projekt.jonas.storage.StorageHandler;
import info.projekt.jonas.util.TextureLoader;

import java.awt.*;
import java.util.logging.Logger;

/**
 * @author Jonas
 */
public class InfoProjekt extends Game {

	private final Logger fpsLogger = Logger.getLogger("FPS Logger");
	private final Stopwatch stopwatch = Stopwatch.createUnstarted();
	private LayerSupervisor supervisor;

	private void init() {
		TextureLoader.loadTextures();
		supervisor = new LayerSupervisor();
	}

	private void register() {
		//FIXME
		//Registry.registerWeapons();
		//Registry.registerArmors();
		//Registry.registerComponents();
		//Registry.registerRecipes();
		Registry.registerRooms();
	}

	@Override
	public void create() {
		register();
		init();
		supervisor.setLayer(new OverlayGui(), LayerSupervisor.OVERLAY_LAYER);
		supervisor.setLayer(new GameScreen(), LayerSupervisor.BACKGROUND_LAYER);
	}

	@Override
	public void render() {
		stopwatch.start();
		RenderUtils.clearScreen(Color.BLACK);
		supervisor.update();
		supervisor.draw();
		stopwatch.stop();
		//fpsLogger.info("Render of frame took " + stopwatch.elapsed(TimeUnit.NANOSECONDS) + "ns, max. fps: " + 1000000 / stopwatch.elapsed(TimeUnit.MICROSECONDS));
		stopwatch.reset();
	}

	@Override
	public void dispose() {
		StorageHandler.saveGame(GameStorage.INSTANCE);
	}
}

