package info.projekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.christoph.TitleScreen;
import info.projekt.jonas.Registry;
import info.projekt.jonas.gui.CameraManager;
import info.projekt.jonas.gui.GameScreen;
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
    public static NameList list;
    public static GameStorage GAME_STORAGE;
    public static SpriteBatch batch;
    public static ShapeRenderer renderer;
    public static CameraManager cameraManager;

	public static void loadGame() {
        try {
            GAME_STORAGE = StorageHandler.loadGame();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void newGame() {
        GAME_STORAGE = new GameStorage();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        TitleScreen titleScreen = new TitleScreen(this);
        new GameScreen();
        cameraManager = new CameraManager();
        Gdx.input.setInputProcessor(titleScreen);
        setScreen(titleScreen);
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
        list = new NameList();
    }

    @Override
    public void render() {
        renderer.setProjectionMatrix(cameraManager.getMatrix());
        batch.setProjectionMatrix(cameraManager.getMatrix());
        getScreen().render(0);
    }

    @Override
    public void dispose() {
        try {
            StorageHandler.saveGame(GAME_STORAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

