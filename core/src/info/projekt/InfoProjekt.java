package info.projekt;

import com.badlogic.gdx.Game;
import com.google.common.base.Stopwatch;
import info.projekt.jonas.gui.instance.GameScreen;
import info.projekt.jonas.gui.instance.OverlayGui;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.util.RenderUtils;
import info.projekt.jonas.spawner.*;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.Registry;
import info.projekt.jonas.storage.StorageHandler;
import info.projekt.jonas.util.Configuration;
import info.projekt.jonas.util.TextureLoader;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.BACKGROUND_LAYER;

/**
 * @author Jonas
 */
public class InfoProjekt extends Game {

	private final Logger logger = Logger.getLogger("FPS Logger");
	private final Stopwatch stopwatch = Stopwatch.createUnstarted();
	private LayerSupervisor supervisor;

	private void init() {
		TextureLoader.loadTextures();
		Registry.registerRooms();
		try {
			Registry.registerArmors();
			Registry.registerWeapons();
			Registry.registerComponents();
			Registry.registerRecipes();
			if (Configuration.Debug.REGISTRY_DEBUG) Registry.debug();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GameStorage.INSTANCE = StorageHandler.loadGame();
		supervisor = new LayerSupervisor();
		new DwellerSpawner();
		new ResourceSpawner();
		new ChildSpawner();
		new MoneySpawner();
		new DeathSpawner();
	}

	@Override
	public void create() {
		init();
		supervisor.setLayer(new OverlayGui(), LayerSupervisor.OVERLAY_LAYER);
		supervisor.setLayer(new GameScreen(), BACKGROUND_LAYER);
	}

	@Override
	public void render() {
		stopwatch.start();
		RenderUtils.clearScreen(Color.BLACK);
		supervisor.update();
		supervisor.draw();
		stopwatch.stop();
		if (Configuration.Debug.FPS_COUNTER)
			logger.info("Render of frame took " + stopwatch.elapsed(TimeUnit.NANOSECONDS) + "ns, max. fps: " + 1000000 / stopwatch.elapsed(TimeUnit.MICROSECONDS));
		stopwatch.reset();
	}

	@Override
	public void dispose() {
		StorageHandler.saveGame(GameStorage.INSTANCE);
	}
}

