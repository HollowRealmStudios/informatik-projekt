package info.projekt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import info.projekt.jonas.CameraManager;
import info.projekt.jonas.RenderUtils;

public class InfoProjekt extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture debugRoom;
    private CameraManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        debugRoom = new Texture("room_debug.png");
        manager = new CameraManager();
    }

    @Override
    public void render() {
        RenderUtils.clearScreen();
        batch.setProjectionMatrix(manager.getMatrix());
        batch.begin();
        batch.draw(debugRoom, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}