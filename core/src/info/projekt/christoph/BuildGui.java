package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * @author Christoph
 * */
public class BuildGui {

    private Stage stage;
    private Table table;
    private ImageButton eroom;//electricity
    private ImageButton wroom;//water
    private ImageButton froom;//food
    private ImageButton hroom;//health
    private ImageButton lroom;//living

    public BuildGui() {

        stage = new Stage(new ScreenViewport());
        table = new Table();
        eroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.jpg")));
        wroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.jpg")));
        froom = new ImageButton(new TextureRegionDrawable(new Texture("Background.jpg")));
        hroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.jpg")));
        lroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.jpg")));






    }


/*
    eroom =  (int) (WIDTH * (1f/28f)) , (int) (HEIGHT * 0.05f), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f) );
    hroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f/28f)) , (int) (HEIGHT * 0.05f) , (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    froom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (19f/28f)) , (int) (HEIGHT * 0.05f) , (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    wroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (1f/28f)) , (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    broom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f/28f)) , (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
*/
}
