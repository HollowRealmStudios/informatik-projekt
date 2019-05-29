package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static info.projekt.jonas.gui.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.WIDTH;

/**
 * @author Christoph
 */
public class BuildGui {

    public Stage stage;
    public Table table;
    private ImageButton eroom;//electricity
    private ImageButton wroom;//water
    private ImageButton froom;//food
    private ImageButton hroom;//health
    private ImageButton lroom;//living

    public BuildGui() {


        //new Stage and Table are created
        stage = new Stage(new ScreenViewport());
        table = new Table();

        //giving the ImageButtons their textures
        eroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        wroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        froom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        hroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        lroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));

        //set the start postion of the table to WIDTH (of monitor) * 1/28 and HEIGHT (of monitor) * 0.05
        table.setPosition((WIDTH * 1f / 28f), (HEIGHT * 0.05f));

        //sets the background to finalDay.png
        table.setBackground(new TextureRegionDrawable(new Texture("finalDay.png")));

        //place the buttons with an space of WIDTH (of monitor) * 1/28
        table.add(eroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f));
        table.padRight((WIDTH * 1f / 28f));
        table.add(wroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f));
        table.padRight((WIDTH * 1f / 28f));
        table.add(froom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f));
        table.padRight((WIDTH * 1f / 28f));
        table.row().padTop((HEIGHT * 0.1f));
        table.add(hroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f));
        table.padRight((WIDTH * 1f / 28f));
        table.add(lroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f));
        table.padRight((WIDTH * 1f / 28f));

        //adds table as an actor of stage
        stage.addActor(table);


    }


/*
    eroom =  (int) (WIDTH * (1f/28f)) , (int) (HEIGHT * 0.05f), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f) );
    hroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f/28f)) , (int) (HEIGHT * 0.05f) , (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    froom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (19f/28f)) , (int) (HEIGHT * 0.05f) , (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    wroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (1f/28f)) , (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    broom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f/28f)) , (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
*/
}
