package info.projekt.jonas.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import info.projekt.InfoProjekt;
import info.projekt.jonas.storage.GameStorage;

import java.awt.*;

import static info.projekt.InfoProjekt.batch;
import static info.projekt.InfoProjekt.manager;
import static info.projekt.InfoProjekt.renderer;


public class GameScreen extends InputAdapter implements Screen {

	public OverlayGui overlayGui;
	public FullscreenGui fullscreenGui;
	public Button buildMenuButton;
	private static InfoProjekt source;

	public GameScreen(InfoProjekt o) {
		source = o;
	}


	@Override
	public void show() {

		buildMenuButton = new Button(new Texture("badlogic.jpg"),1,1,50,50);

		overlayGui = new OverlayGui(source) {
			@Override
			public void buttonPressed(Button button) {

				if(button.equals(buildMenuButton)){
					System.out.println("ALLAH");
					fullscreenGui.show();
				}

			}
		};

		fullscreenGui = new FullscreenGui(new Texture("finalDay.PNG"), source) {
			@Override
			public void keyPressed(int key) {

			}

			@Override
			public void buttonPressed(Button button) {

			}
		};
		overlayGui.show();
		overlayGui.addComponent(buildMenuButton);
		Gdx.input.setInputProcessor(overlayGui);
	}

	@Override
	public void render(float delta) {
		keyDown();
		RenderUtils.clearScreen(new Color(49, 144, 175));
		RenderUtils.drawRooms(InfoProjekt.GAME_STORAGE.getRooms(), batch);
		overlayGui.paint(batch,renderer);
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

	}
}
