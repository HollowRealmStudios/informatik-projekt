package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.gui.*;
import info.projekt.jonas.rooms.Kitchen;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;

import java.io.IOException;
import java.util.Objects;

import static info.projekt.InfoProjekt.GAME_STORAGE;
import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Christoph
 * @author Jonas
 */
public class TitleScreen extends Gui {

	private final Table table;

	public TitleScreen() {
		table = new Table();
	}

	private void loadGame() {
		try {
			InfoProjekt.GAME_STORAGE = StorageHandler.loadGame();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void newGame() {

		InfoProjekt.GAME_STORAGE = new GameStorage();
	}


	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(Object... o) {
		InfoProjekt.multiplexer.addProcessor(stage);
		TextButton newGame = new TextButton("New Game", SKIN);
		TextButton loadGame = new TextButton("Load Game", SKIN);
		newGame.getLabel().setFontScale(2, 2);
		loadGame.getLabel().setFontScale(2, 2);
		table.background(new TextureRegionDrawable(new Texture("TitleScreenBackground.png"))).setSize(WIDTH, HEIGHT);
		table.add(newGame).width(HALF_WIDTH).height(HEIGHT / 10);
		table.row().padTop(50);
		table.add(loadGame).width(HALF_WIDTH).height(HEIGHT / 10);
		stage.addActor(table);
		newGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				newGame();
				GAME_STORAGE.getRooms()[0][0] = new Kitchen();
				register();
			}
		});
		loadGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				loadGame();
				register();
			}
		});
		table.setVisible(true);
	}

	private void register() {
		GuiProvider.registerGui(DwellerGui.class);
		GuiProvider.registerGui(DwellerList.class);
		GuiProvider.registerGui(ItemSelector.class);
		GuiProvider.registerGui(RoomGui.class);
		GuiProvider.registerGui(BuildGui.class);
		GuiProvider.registerGui(SettingsGui.class);
		GuiProvider.registerGui(GameScreen.class);
		GuiProvider.registerGui(ExplorationGui.class);
		hide();
		Objects.requireNonNull(GuiProvider.requestGui(GameScreen.class)).show();
	}

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	@Override
	public void hide() {
		InfoProjekt.multiplexer.removeProcessor(stage);
		table.setVisible(false);
	}
}





