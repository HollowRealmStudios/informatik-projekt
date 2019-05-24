package info.projekt.christoph;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.projekt.InfoProjekt;
import info.projekt.jonas.gui.Button;
import info.projekt.jonas.gui.Label;
import info.projekt.jonas.gui.*;

import java.awt.*;

public class Screen extends Game implements InputProcessor {

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private ProgressBar bar;
    private Button newgamebutton;
    private Button loadgamebutton;
    private Icon icon;
    private Label label;
    private FullscreenGui gui;
    private OverlayGui overlayGui;

    public void create() {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        overlayGui = new OverlayGui(this) {
            @Override
            public void buttonPressed(Button button) {
                if (button.equals(newgamebutton)) {
                    new InfoProjekt();

                }
            }
        };
        gui = new FullscreenGui(new Texture("finalNight.PNG"), this) {

            @Override
            public void keyPressed(int key) {
                System.out.println(key);
                if (key == Input.Keys.F1) this.hide();
                if(key == Input.Keys.ESCAPE) Gdx.app.exit();
            }

            @Override
            public void buttonPressed(Button button) {
                if (button.equals(newgamebutton)) {
                    new InfoProjekt();

                }
            }
        };
        // bar = new ProgressBar(new Color(7, 14, 255), 200, 200, 200, 20);
        newgamebutton = new Button(new Texture("badlogic.jpg"), 400, 500, 400, 500);
        loadgamebutton = new Button(0, 500, 400, 500);

        //icon = new Icon(new Texture("room_debug.png"), 900, 900);
        // label = new Label("Hello, world!", new Font(Font.MONOSPACED, Font.BOLD, 40), 30, 30);
        // gui.addComponent(bar);
        overlayGui.addComponent(newgamebutton);
        gui.addComponent(loadgamebutton);
        // gui.addComponent(icon);
        // gui.addComponent(label);
        // gui.registerKey(Input.Keys.F1);
        gui.registerKey(Input.Keys.ESCAPE);
    }

    @Override
    public void render() {
        RenderUtils.clearScreen(new java.awt.Color(43, 18, 11));
        RenderUtils.drawBackground(batch, new Texture("Background.png"));
        //bar.update(1);
        gui.paint(batch, renderer);
        overlayGui.paint(batch, renderer);
        overlayGui.show();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.F2) {
            gui.show();
        }
        if (keycode == Input.Keys.ESCAPE) Gdx.app.exit();
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}





