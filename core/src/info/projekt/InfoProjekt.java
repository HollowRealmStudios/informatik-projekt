package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.BuildGui;
import info.projekt.christoph.SettingsGui;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.Registry;
import info.projekt.jonas.gui.*;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;
import info.projekt.jonas.threads.WorkThread;
import info.projekt.jonas.util.NameList;

import java.io.IOException;

/**
 * @author Jonas
 * @author Christoph
 */
public class InfoProjekt extends Game {

    public static final WorkThread WORK_THREAD = new WorkThread(1000);
    public static GameStorage GAME_STORAGE;
    public static SpriteBatch batch;
    public static ShapeRenderer renderer;
    public static CameraManager cameraManager;
    public static InputMultiplexer multiplexer;

    @Override
    public void create() {
		multiplexer = new InputMultiplexer();
        GuiProvider.registerGui(DwellerGui.class);
        GuiProvider.registerGui(DwellerList.class);
        GuiProvider.registerGui(ItemSelector.class);
		GuiProvider.registerGui(RoomGui.class);
	    GuiProvider.registerGui(BuildGui.class);
	    GuiProvider.registerGui(SettingsGui.class);
	    GuiProvider.registerGui(TitleScreen.class);

	    batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        TitleScreen titleScreen = new TitleScreen(this);
        cameraManager = new CameraManager();
        Gdx.input.setInputProcessor(titleScreen.stage);
        try {
            Registry.registerArmors();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Registry.registerWeapons();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Registry.registerRooms();
        System.out.println(Registry.allToString());
    }

    @Override
    public void render() {
        renderer.setProjectionMatrix(cameraManager.getMatrix());
        batch.setProjectionMatrix(cameraManager.getMatrix());
        GuiProvider.getGuis().forEach(g -> g.act(Gdx.graphics.getDeltaTime()));
    }

    @Override
    public void dispose() {
        WORK_THREAD.stop();
    	try {
            StorageHandler.saveGame(GAME_STORAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

