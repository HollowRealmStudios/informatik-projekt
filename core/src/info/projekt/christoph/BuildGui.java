package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    public BuildGui() {

        //new Stage and Table are created
        stage = new Stage(new ScreenViewport());
        table = new Table();


        //giving the ImageButtons their textures
        //electricity
        ImageButton eroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        //water
        ImageButton wroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        //food
        ImageButton froom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        //health
        ImageButton hroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
        //living
        ImageButton lroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));

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
}
