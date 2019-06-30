package info.projekt.christoph;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.Gui;
import info.projekt.jonas.gui.RenderUtils;


import java.awt.image.renderable.RenderableImage;

import static info.projekt.jonas.gui.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.WIDTH;


public class SettingsGui extends Gui {

    public Stage stage;
    private ImageButton close;
    private Slider volumeSlider;
    private float volume;
    private Music music;


    public SettingsGui() {
        //Creating a new stage
        stage = new Stage(new ScreenViewport());
        InfoProjekt.multiplexer.addProcessor(stage);

        //define the volume of the music, start the music and loop it, define the snapvalues of the volumeslider, define the song
        volume = 100f;
        music = Gdx.audio.newMusic(Gdx.files.internal("Nein jetzt hältst du die Schnauze! (Remix).mp3"));
        music.play();
        music.setLooping(true);

        //creating a close button, set its position and size, set it invisible
        close = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        close.setPosition(20f / 28f * WIDTH, 20f / 28f * HEIGHT);
        close.setSize(1f / 14f * WIDTH, 1f / 14f * HEIGHT);
        close.setVisible(false);

        //creating a volumeslider ( define its minimum and maximum, the step size and 1: slider; 2: knob pictures ),set its position, set its size, set its snapvalues, set it invisible, set its value to the current volume level
        volumeSlider = new Slider(0f, 100f, 1f, false, new Slider.SliderStyle(new TextureRegionDrawable(new Texture("Slider.png")), new TextureRegionDrawable(new Texture("Arrow.png"))));
        volumeSlider.setPosition(7f / 28f * WIDTH, 14f / 28f * HEIGHT);
        volumeSlider.setSize(7f / 14f * WIDTH, 1f / 28f * HEIGHT);
        volumeSlider.setSnapToValues(new float[]{0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100}, 2.5f);
        volumeSlider.setVisible(false);
        volumeSlider.setValue(volume);


        //add all actors to the stage
        stage.addActor(close);
        stage.addActor(volumeSlider);

    }

	@Override
    public void show(Object... o) {

        //setting all actors visible and add their listener
        close.setVisible(true);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });


        volumeSlider.setVisible(true);
        close.setVisible(true);
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                volume = volumeSlider.getValue();
                music.setVolume(volume / 100);

            }
        });
        RenderUtils.guiOpen = true;
    }

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	@Override
    public void hide() {
        //set all actors invisible
        close.setVisible(false);
        volumeSlider.setVisible(false);
	    RenderUtils.guiOpen = false;
    }
}
