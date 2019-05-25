package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.InfoProjekt;

import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;

public abstract class FullscreenGui extends Gui {

    private ArrayList<Integer> Keys = new ArrayList<Integer>();
    private Texture background;

    public FullscreenGui(Texture background, Object source) {
        super(source);
        this.background = background;
    }

    public void registerKey(int key) { Keys.add(key); }

    public abstract void keyPressed(int key);

    @Override
    public void paint(SpriteBatch batch, ShapeRenderer renderer) {
        if (visible) {
	        InfoProjekt.manager.setZoom(1f);
            batch.begin();
            batch.draw(background, InfoProjekt.manager.getCamera().position.x - (int)(Gdx.graphics.getWidth() / 2), InfoProjekt.manager.getCamera().position.y - (int)(Gdx.graphics.getHeight() / 2), Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
            batch.end();
        }
        super.paint(batch, renderer);
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        if (Keys.contains(keycode)) {
            keyPressed(keycode);
        }
        return false;
    }
}
