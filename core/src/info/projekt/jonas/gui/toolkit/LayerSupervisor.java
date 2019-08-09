package info.projekt.jonas.gui.toolkit;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesPassiveUpdates;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.util.StreamArray;
import info.projekt.jonas.util.TextureLoader;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Stack;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.WIDTH;

public class LayerSupervisor {

	public static final int BACKGROUND_LAYER = 0;
	public static final int OVERLAY_LAYER = 1;
	public static final int GUI_LAYER = 2;
	public static final Stack<LayerRequest> LAYER_STACK = new Stack<>();
	public static final Stack<NotificationRequest> NOTIFICATION_STACK = new Stack<>();
	private final HashMap<Class<? extends Layer>, Layer> LAYERS = new HashMap<>();
	private final StreamArray<Layer> layers = new StreamArray<>(new Layer[3]);
	private final KeyManager manager = new KeyManager();
	private final SpriteBatch batch;
	private final ShapeRenderer renderer;

	public LayerSupervisor() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		new Reflections("info.projekt").getSubTypesOf(Layer.class).forEach(c -> {
			try {
				LAYERS.put(c, c.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	public void setLayer(Layer layer, int i) {
		layers.t[i] = layer;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Layer getLayer(int i) {
		return layers.t[i];
	}

	public void draw() {
		for (int i = 0; i <= 2; i++) {
			if(i == 2 && layers.t[i] != null) {
				batch.begin();
				batch.draw(TextureLoader.getTexture("Back.png"), 0, 0, WIDTH, HEIGHT);
				batch.end();
			}
			if (layers.t[i] != null) layers.t[i].draw(batch, renderer);
		}

	}

	private void passThrough() {
		if (manager.isMouseClicked()) passThroughMouse();
		passThroughKeyboard();
	}

	private void passThroughKeyboard() {
		if (layers.t[GUI_LAYER] != null) layers.t[GUI_LAYER].handleKeyboard(manager);
		else layers.t[BACKGROUND_LAYER].handleKeyboard(manager);
	}

	private void passThroughMouse() {
		if (layers.t[GUI_LAYER] != null) layers.t[GUI_LAYER].handleMouse();
		else if (layers.t[OVERLAY_LAYER] != null) layers.t[OVERLAY_LAYER].handleMouse();
		else if (layers.t[BACKGROUND_LAYER] != null) layers.t[BACKGROUND_LAYER].handleMouse();
	}

	private void checkForQuit() {
		if (manager.getKeys().get(Input.Keys.ESCAPE)) layers.t[GUI_LAYER] = null;
	}

	public void update() {
		manager.update();
		passThrough();
		checkForQuit();
		LAYERS.values().forEach(layer -> {
			if (layer instanceof IHandlesPassiveUpdates) ((IHandlesPassiveUpdates) layer).onPassiveUpdate();
		});
		layers.stream().forEach(layer -> {
			if (layer != null) layer.update();
		});
		if (!LAYER_STACK.isEmpty()) {
			LayerRequest request = LAYER_STACK.pop();
			if (layers.t[request.layerNumber] == null || request.force)
				layers.t[request.layerNumber] = LAYERS.get(request.layer);
		}
	}
}
