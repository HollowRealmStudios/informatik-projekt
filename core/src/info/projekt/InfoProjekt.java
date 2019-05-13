package info.projekt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import info.projekt.jonas.CameraManager;
import info.projekt.jonas.InputHandler;
import info.projekt.jonas.InputReciever;
import info.projekt.jonas.RenderUtils;

public class InfoProjekt extends ApplicationAdapter implements InputReciever {

    private SpriteBatch batch;
    private Texture debugRoom;
    private CameraManager manager;
    private InputHandler handler = new InputHandler();

    @Override
    public void create() {
        batch = new SpriteBatch();
        debugRoom = new Texture("room_debug.png");
        manager = new CameraManager();
        handler.addReciever(this);

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

    @Override
    public void movePressed(int key) {
        System.out.println(key);
    }
}

