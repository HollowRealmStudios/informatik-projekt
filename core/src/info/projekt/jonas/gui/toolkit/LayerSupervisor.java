package info.projekt.jonas.gui.toolkit;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.util.StreamArray;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Stack;

public class LayerSupervisor {

	enum HANDLING {PASS_THROUGH, IGNORE, CLOSE_ON_SUCCESSFUL_PASS_THROUGH}

	public static final Stack<Class<? extends Layer>> LAYER_STACK = new Stack<>();
	private final HashMap<Class<? extends Layer>, Layer> LAYERS = new HashMap<>();
	private final HANDLING handling = HANDLING.PASS_THROUGH;
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
		int i = 2;
		for (Layer layer : layers.t) {
			if (layer != null) {
				if (layer.handleKeyboard(manager)) break;
				i--;
			}
		}
		//System.out.println("Keyboard handled by layer " + i);
		i = 2;
		for (Layer layer : layers.t) {
			if (layer != null) {
				if (layer.handleMouse(manager)) break;
				i--;
			}
		}
		//System.out.println("Mouse handled by layer " + i);
	}

	private void checkForQuit() {
		if (manager.getKeys().get(Input.Keys.ESCAPE)) layers.t[2] = null;
	}

	public void update() {
		layers.stream().forEach(layer -> {
			if (layer != null) layer.update();
		});
		passThrough();
		checkForQuit();
		if (!LAYER_STACK.isEmpty()) {
			layers.t[2] = null;
			layers.t[2] = LAYERS.get(LAYER_STACK.pop());
		}
	}
}
