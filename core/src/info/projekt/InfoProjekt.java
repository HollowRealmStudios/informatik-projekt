package info.projekt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import info.projekt.jonas.CameraManager;
import info.projekt.jonas.RenderUtils;
import info.projekt.jonas.rooms.DebugRoom;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.storage.GameStorage;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class InfoProjekt extends ApplicationAdapter {

	private ShapeRenderer renderer;
	private GameStorage storage;
	private SpriteBatch batch;
	private CameraManager manager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		Texture debugRoom = new Texture("room_debug.png");
		manager = new CameraManager();
		manager.setZoom(1.8f);
		storage = new GameStorage();
		storage.debug();
		renderer = new ShapeRenderer();
	}

	@Override
	public void render() {
		processInput();
		RenderUtils.clearScreen(new Color(43, 18, 11));
		batch.setProjectionMatrix(manager.getMatrix());
		RenderUtils.drawRooms(storage.getRooms(), batch);
		Vector3 mouse = manager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		renderer.setProjectionMatrix(manager.getMatrix());
		Gdx.gl.glLineWidth(10);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(com.badlogic.gdx.graphics.Color.RED);
		renderer.rect((float) (Math.floor(mouse.x / Room.WIDTH) * Room.WIDTH), (float) (Math.floor(mouse.y / Room.HEIGHT) * Room.HEIGHT), Room.WIDTH, Room.HEIGHT);
		renderer.end();
	}

	private void processInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 30 : 15));
		else if (Gdx.input.isKeyPressed(Input.Keys.S)) manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -30 : -15));
		if (Gdx.input.isKeyPressed(Input.Keys.A)) manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -30 : -15, 0));
		else if (Gdx.input.isKeyPressed(Input.Keys.D)) manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 30 : 15, 0));
		if (Gdx.input.isKeyPressed(Input.Keys.F1)) manager.setZoom(manager.getZoom() + 0.05f);
		else if (Gdx.input.isKeyPressed(Input.Keys.F2)) manager.setZoom(manager.getZoom() - 0.05f);
		if (Gdx.input.isKeyPressed(Input.Keys.R) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			Gdx.graphics.setWindowedMode(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
			String in = JOptionPane.showInputDialog(null, "", "");
			if (Pattern.matches("Room:\\w+ \\d \\d \\d \\d", in)) {
				//FIXME
			}
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}

