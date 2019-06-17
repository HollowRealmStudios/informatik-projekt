package info.projekt.christoph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.RenderUtils;

import static info.projekt.InfoProjekt.batch;
import static info.projekt.InfoProjekt.manager;
import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Christoph
 * @author Jonas
 */
public class TitleScreen implements com.badlogic.gdx.Screen, InputProcessor {

	private GameScreen gameScreen;
	private InfoProjekt source;
	private TextButton newGame;
	private TextButton loadGame;
	private Table table;
	private Stage stage;

	public TitleScreen(InfoProjekt source) {
		this.source = source;
	}


	@Override
	public void show() {
		manager.translateAbsolute(new Vector2(HALF_WIDTH, HALF_HEIGHT));
		stage = new Stage(new ScreenViewport());
		table = new Table();
		newGame = new TextButton("New Game", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
		loadGame = new TextButton("Load Game", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
		newGame.getLabel().setFontScale(2, 2);
		loadGame.getLabel().setFontScale(2, 2);
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		table.add(newGame).width(HALF_WIDTH).height(HEIGHT / 10);
		table.row().padTop(50);
		table.add(loadGame).width(HALF_WIDTH).height(HEIGHT / 10);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
		newGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				InfoProjekt.newGame();
				source.setScreen(new GameScreen());
				Gdx.input.setInputProcessor(GameScreen.multiplexer);
			}
		});
		loadGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				InfoProjekt.loadGame();
				source.setScreen(new GameScreen());
				Gdx.input.setInputProcessor(GameScreen.multiplexer);
			}
		});
	}

	@Override
	public void render(float delta) {
		RenderUtils.drawBackground(batch, new Texture("TitleScreenBackground.png"));
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
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
		stage.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE) System.exit(0);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}





