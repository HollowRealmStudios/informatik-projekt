package info.projekt.christoph;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import info.projekt.InfoProjekt;
import info.projekt.jonas.gui.Button;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.OverlayGui;
import info.projekt.jonas.gui.RenderUtils;

import javax.sound.sampled.Line;
import javax.tools.Tool;

import java.awt.*;

import static info.projekt.InfoProjekt.*;
import static info.projekt.jonas.gui.RenderUtils.*;

public class TitleScreen implements com.badlogic.gdx.Screen, InputProcessor {

    /**
     * @author Christoph
     */


	@Override
	public void show() {

	    manager.translateAbsolute(new Vector2(HALF_WIDTH,HALF_HEIGHT));

	    Button newgamebutton = new Button(new Texture("badlogic.jpg"), (int) (WIDTH*0.2552f), (int) (HEIGHT*0.4027f), (int) (WIDTH*0.4895f), (int) (HEIGHT*0.1666f));
        Button loadgamebutton = new Button(new Texture("badlogic.jpg"),0,0,400,500);
        OverlayGui overlayGui = new OverlayGui(this) {
            @Override
            public void buttonPressed(Button button) {

                if(button.equals("newgamebutton")) {
                    ((InfoProjekt)source).changeScreen(gameScreen);

                }
                if(button.equals("loadgamebutton")){


                }

            }
        };

	}

	@Override
	public void render(float delta) {
	    RenderUtils.drawBackground(batch, new Texture("Background.png"));

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

    @Override
    public boolean keyDown(int keycode) {
            if(keycode == Input.Keys.ESCAPE) Gdx.app.exit();



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





