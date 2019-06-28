package info.projekt.christoph;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static info.projekt.jonas.gui.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.WIDTH;


public class Settings {

    public Stage stage;
    private ImageButton close;
    private Slider volumeSlider;
    private float[] values;

    public Settings() {
        values = new float[11];
        values[0] = 0;
        values[1] = 10;
        values[2] = 20;
        values[3] = 30;
        values[4] = 40;
        values[5] = 50;
        values[6] = 60;
        values[7] = 70;
        values[8] = 80;
        values[9] = 90;
        values[10] = 100;
        stage = new Stage(new ScreenViewport());
        close = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        close.setPosition(20f / 28f * WIDTH, 20f / 28f * HEIGHT);
        close.setSize(1f / 14f * WIDTH, 1f / 14f * HEIGHT);
        close.setVisible(false);
        volumeSlider = new Slider(0f,100f,1f,false,new Slider.SliderStyle(new TextureRegionDrawable(new Texture("Slider.png")),new TextureRegionDrawable(new Texture("Arrow.png"))));
        volumeSlider.setPosition(7f / 28f * WIDTH, 14f / 28f * HEIGHT);
        volumeSlider.setSize(7f / 14f * WIDTH, 1f / 28f * HEIGHT);
        volumeSlider.setSnapToValues(values, 2.5f);
        volumeSlider.setVisible(false);
        stage.addActor(close);
        stage.addActor(volumeSlider);

    }


    public void show(){
        close.setVisible(true);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });
        volumeSlider.setVisible(true);

    }

    public void hide(){
        close.setVisible(false);
        volumeSlider.setVisible(false);
    }


}
