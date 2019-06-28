package info.projekt.christoph;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.gui.Gui;

import static info.projekt.jonas.gui.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.WIDTH;


public class Settings extends Gui {

    public Stage stage;
    private ImageButton close;
    private Slider volumeSlider;
    private float[] values;

    public Settings() {
        values = new float[]{0,10,20,30,40,50,60,70,80,90,100};
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


	@Override
	public boolean isVisible() {
		return volumeSlider.isVisible();
	}
}
