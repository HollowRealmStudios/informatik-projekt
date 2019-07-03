package info.projekt.jonas.gui;

import info.projekt.jonas.util.InputManager;

public abstract class Gui extends InputManager {

    public abstract void show(Object... o);

    public abstract void act(float f);

    public abstract void hide();
}
