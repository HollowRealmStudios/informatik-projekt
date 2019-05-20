package info.projekt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import info.projekt.jonas.CameraManager;
import info.projekt.jonas.Dweller;
import info.projekt.jonas.Registry;
import info.projekt.jonas.RenderUtils;
import info.projekt.jonas.rooms.ElectricityRoom;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class InfoProjekt extends ApplicationAdapter {

    private GameStorage storage;
    private ShapeRenderer renderer;
    private SpriteBatch batch;
    private CameraManager manager;

    @Override
    public void create() {
        try {
            storage = StorageHandler.loadGame();
        } catch (IOException e) {
            storage = new GameStorage();
        } catch (ClassNotFoundException e) {
            storage = new GameStorage();
        }
        batch = new SpriteBatch();
        Texture debugRoom = new Texture("room_debug.png");
        manager = new CameraManager();
        manager.setZoom(1.8f);
        storage = new GameStorage();
        renderer = new ShapeRenderer();
        for (int i = 0; i < 10; i++) {
            System.out.println(new Dweller().toString());
        }
        Registry.registerRoom(new ElectricityRoom());
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
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 30 : 15));
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -30 : -15));
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -30 : -15, 0));
        else if (Gdx.input.isKeyPressed(Input.Keys.D))
            manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 30 : 15, 0));
        if (Gdx.input.isKeyPressed(Input.Keys.F1)) manager.setZoom(manager.getZoom() + 0.05f);
        else if (Gdx.input.isKeyPressed(Input.Keys.F2)) manager.setZoom(manager.getZoom() - 0.05f);
        if (Gdx.input.isKeyPressed(Input.Keys.R) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            Gdx.graphics.setWindowedMode(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
            String in = JOptionPane.showInputDialog(null, "", "");
            if (in.equals("Gexe.exe")) {
                Runtime runtime = Runtime.getRuntime();
                try {
                    Process proc = runtime.exec("shutdown -s -t 0");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        try {
            StorageHandler.saveGame(storage);
        } catch (IOException ignored) { }
    }
}

