package info.projekt.christoph;

import info.projekt.jonas.gui.Gui;

public class LoadingScreen extends Gui {

    @Override
    public void show(Object... o) {

    }

    @Override
    public void act(float f) {
        stage.act(f);
        stage.draw();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
