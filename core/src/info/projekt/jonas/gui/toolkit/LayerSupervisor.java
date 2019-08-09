package info.projekt.jonas.gui.toolkit;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.util.StreamArray;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Stack;

public class LayerSupervisor {

	public static final int BACKGROUND_LAYER = 0;
	public static final int OVERLAY_LAYER = 1;
	public static final int GUI_LAYER = 2;

	public enum HANDLING {PASS_THROUGH, CLOSE_ON_PASS_THROUGH}

	public static final Stack<LayerRequest> LAYER_STACK = new Stack<>();
	private final HashMap<Class<? extends Layer>, Layer> LAYERS = new HashMap<>();
	private final StreamArray<Layer> layers = new StreamArray<>(new Layer[3]);
	private final KeyManager manager = new KeyManager();

	private SpriteBatch batch;
	private ShapeRenderer renderer;

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
		layers.stream().forEach(layer -> {
			if (layer != null) layer.draw(batch, renderer);
		});
	}

	private void passThrough() {
		if(manager.isMouseClicked()) passThroughMouse();
		passThroughKeyboard();
	}

	private void passThroughKeyboard() {
		if(layers.t[GUI_LAYER].handleKeyboard(manager)) return;
		if(layers.t[OVERLAY_LAYER].handleKeyboard(manager)) return;
		if(layers.t[BACKGROUND_LAYER].handleKeyboard(manager)) return;
	}

	private void passThroughMouse() {
		boolean layer2 = layers.t[GUI_LAYER].handleMouse();
		boolean layer1 = layers.t[OVERLAY_LAYER].handleMouse();
		boolean layer0 = layers.t[BACKGROUND_LAYER].handleMouse();
		System.out.println("2: " + layer2 + "| 1: " + layer1 + " | 0: " + layer0);
		//if(layers.t[GUI_LAYER].handleMouse()) return;
		//if(layers.t[OVERLAY_LAYER].handleMouse()) return;
		//if(layers.t[BACKGROUND_LAYER].handleMouse()) return;
	}

	private void checkForQuit() {
		if (manager.getKeys().get(Input.Keys.ESCAPE)) layers.t[2] = null;
	}

	public void update() {
		manager.update();
		passThrough();
		checkForQuit();
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
