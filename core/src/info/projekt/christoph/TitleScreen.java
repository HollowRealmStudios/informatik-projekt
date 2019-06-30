package info.projekt.christoph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.Gui;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;

import java.io.IOException;

import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Christoph
 * @author Jonas
 */
public class TitleScreen extends Gui {

	private final InfoProjekt source;
	private Table table;
	public Stage stage;


	public TitleScreen(InfoProjekt source) {
        this.source = source;
    }

	private void loadGame() {
		try {
			InfoProjekt.GAME_STORAGE = StorageHandler.loadGame();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void newGame() {
		InfoProjekt.GAME_STORAGE = new GameStorage();
	}


	@Override
    public void show(Object... o) {
	    stage = new Stage(new ScreenViewport());
	    table = new Table();
	    TextButton newGame = new TextButton("New Game", SKIN);
	    TextButton loadGame = new TextButton("Load Game", SKIN);
        newGame.getLabel().setFontScale(2, 2);
        loadGame.getLabel().setFontScale(2, 2);
        table.background(new TextureRegionDrawable(new Texture("TitleScreenBackground.png"))).setSize(WIDTH, HEIGHT);
        table.add(newGame).width(HALF_WIDTH).height(HEIGHT / 10);
        table.row().padTop(50);
        table.add(loadGame).width(HALF_WIDTH).height(HEIGHT / 10);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                newGame();
                source.setScreen(new GameScreen());
            }
        });
        loadGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadGame();
                source.setScreen(new GameScreen());
            }
        });
    }

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	@Override
    public void hide() {
		table.setVisible(false);
    }
}





