package info.projekt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import info.projekt.jonas.CameraManager;
import info.projekt.jonas.RenderUtils;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.storage.GameStorage;

public class InfoProjekt extends ApplicationAdapter {

    private GameStorage storage;
    private SpriteBatch batch;
    private Texture debugRoom;
    private CameraManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        debugRoom = new Texture("room_debug.png");
        manager = new CameraManager();
        manager.setZoom(1.8f);
        storage = new GameStorage();
        storage.debug();
    }

    @Override
    public void render() {
        processInput();
        RenderUtils.clearScreen();
        batch.setProjectionMatrix(manager.getMatrix());
        batch.begin();
        for(int x = 0; x < storage.getRooms().length; x++) {
            for(int y = 0; y < storage.getRooms()[0].length; y++) {
                batch.draw(storage.getRooms()[x][y].getTexture(), x * Room.WIDTH, y * Room.HEIGHT);
            }
        }
        batch.end();
    }

    private void processInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) manager.translateRelative(new Vector2(0, 15));
        else if(Gdx.input.isKeyPressed(Input.Keys.S)) manager.translateRelative(new Vector2(0, -15));
        if(Gdx.input.isKeyPressed(Input.Keys.A)) manager.translateRelative(new Vector2(-15, 0));
        else if(Gdx.input.isKeyPressed(Input.Keys.D)) manager.translateRelative(new Vector2(15, 0));
        if(Gdx.input.isKeyPressed(Input.Keys.F1)) manager.setZoom(manager.getZoom() + 0.1f);
        else if(Gdx.input.isKeyPressed(Input.Keys.F2)) manager.setZoom(manager.getZoom() - 0.1f);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

