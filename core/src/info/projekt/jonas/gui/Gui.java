package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.util.InputManager;

public abstract class Gui extends InputManager {

    protected Stage stage = new Stage(new ScreenViewport());

    public abstract void dispose();

    public abstract void show(Object... o);

    public abstract void act(float f);

    public abstract void hide();
}
