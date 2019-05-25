package info.projekt.jonas.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;

import java.awt.*;
import java.util.Objects;

import static info.projekt.InfoProjekt.batch;
import static info.projekt.InfoProjekt.manager;


public class GameScreen extends InputAdapter implements Screen {

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
		//button.setBounds(0, 0, 256, 256);
		button.setPosition(200, 200);
		stage.addActor(button);
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
	}


	@Override
	public void render(float delta) {
		keyDown();
		RenderUtils.clearScreen(new Color(49, 144, 175));
		RenderUtils.drawRooms(InfoProjekt.GAME_STORAGE.getRooms(), batch);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public boolean scrolled(int amount) {
		manager.setZoom(manager.getZoom() + (amount > 0 ? 0.2f : -0.2f));
		return false;
	}

	private void keyDown() {
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

	@Override
	public void dispose() {
		stage.dispose();
	}
}
