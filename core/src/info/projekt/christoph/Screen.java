package info.projekt.christoph;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.jonas.RenderUtils;

import java.awt.*;

public class Screen extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private Texture TitleScreenPicture;
    public void create() {
        batch = new SpriteBatch();
        TitleScreenPicture = new Texture("maxresdefault.jpg");
        renderer = new ShapeRenderer();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        processInput();
        RenderUtils.clearScreen(new Color(43, 18, 11));
        batch.begin();
        batch.draw(TitleScreenPicture,0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        batch.end();
        renderer.end();
    }


    private void processInput(){




    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}





