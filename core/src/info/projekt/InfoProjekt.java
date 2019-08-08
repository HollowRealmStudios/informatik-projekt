package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.google.common.base.Stopwatch;
import info.projekt.jonas.gui.BuildGui;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.RenderUtils;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.util.TextureLoader;

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
		init();
		supervisor.setLayer(new BuildGui(), 2);
		supervisor.setLayer(new GameScreen(), 0);
		supervisor.getLayer(2).setDebug(false);
	}

	@Override
	public void render() {
		stopwatch.start();
		System.out.println(Gdx.input.getX() + " / " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
		RenderUtils.clearScreen(Color.BLACK);
		supervisor.update();
		supervisor.draw();
		stopwatch.stop();
		//System.out.println("Render of frame took " + stopwatch.elapsed(TimeUnit.NANOSECONDS) + "ns, max. fps: " +1000000 / stopwatch.elapsed(TimeUnit.MICROSECONDS));
		stopwatch.reset();
	}

	@Override
	public void dispose() {

	}
}

