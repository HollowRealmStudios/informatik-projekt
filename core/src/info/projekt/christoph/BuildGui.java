package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static info.projekt.jonas.gui.RenderUtils.*;
import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;

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
    private Image image;

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

        //sets the background to finalDay.png
        table.background(new TextureRegionDrawable(new Texture("finalDay.PNG"))).setSize(WIDTH, HEIGHT);

        //place the buttons with an space of WIDTH (of monitor) * 1/28
        table.add(eroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f)).padLeft((WIDTH * 1f / 28f));
        table.add(wroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f));
        table.add(froom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f));
        table.row().padTop((HEIGHT * 0.1f));
        table.add(hroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f)).padLeft((WIDTH * 1f / 28f));
        table.add(lroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f));

        //adds table as an actor of stage
        stage.addActor(table);

        eroom.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);



            }
        });
        wroom.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);


            }
        });
        froom.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);


            }
        });
        hroom.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);


            }
        });
        lroom.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.setVisible(false);


            }
        });






















    }



/*
    eroom =  (int) (WIDTH * (1f/28f)) , (int) (HEIGHT * 0.05f), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f) );
    hroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f/28f)) , (int) (HEIGHT * 0.05f) , (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    froom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (19f/28f)) , (int) (HEIGHT * 0.05f) , (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    wroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (1f/28f)) , (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
    broom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f/28f)) , (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f/7f)), (int) (HEIGHT * 0.4f));
*/
}
