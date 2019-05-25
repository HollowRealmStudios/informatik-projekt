package info.projekt.jonas.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;

import java.awt.*;

import static info.projekt.InfoProjekt.*;
import static info.projekt.jonas.gui.RenderUtils.CELL_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.CELL_WIDTH;


public class GameScreen extends InputAdapter implements Screen {

	private Vector2 cellPosition = new Vector2();
	public static InputMultiplexer multiplexer;
	private ImageButton button;
	private Stage stage;
	private static InfoProjekt source;

	public GameScreen(InfoProjekt o) {
		source = o;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport());
		button = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
		button.setPosition(200, 200);
		stage.addActor(button);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(event.toString());
			}
		});
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render(float delta) {
		keyDown();
		Vector3 pos = manager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		cellPosition.set((float) (Math.floor(pos.x / CELL_WIDTH) * CELL_WIDTH), (float) (Math.floor(pos.y / CELL_HEIGHT) * CELL_HEIGHT));
		Gdx.gl.glLineWidth(10);
		RenderUtils.clearScreen(new Color(49, 144, 175));
		batch.begin();
		for (int x = 0; x < GAME_STORAGE.getRooms().length; x++) {
			for (int y = 0; y < GAME_STORAGE.getRooms()[0].length; y++) {
				batch.draw(GAME_STORAGE.getRooms()[x][y].getTexture(), x * CELL_WIDTH, y * CELL_HEIGHT);
			}
		}
		batch.end();
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.rect(cellPosition.x, cellPosition.y, CELL_WIDTH, CELL_HEIGHT);
		renderer.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public boolean scrolled(int amount) {
		manager.setZoom(manager.getZoom() + (amount > 0 ? 0.2f : -0.2f));
		return false;
	}

	private void keyDown() {
		//if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) GAME_STORAGE.setRoom(new DebugRoom(), (int)cellPosition.x / CELL_WIDTH, (int)cellPosition.y / CELL_HEIGHT);
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10));
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10, 0));
		} else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10, 0));
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}
}
