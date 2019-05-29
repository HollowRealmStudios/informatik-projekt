package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static info.projekt.jonas.gui.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.WIDTH;

/**
 * @author Christoph
 * */
public class DwellerList {

    public Stage stage;
    public Table table;


    public DwellerList() {

        stage = new Stage(new ScreenViewport());
        table = new Table();

        table.background(new TextureRegionDrawable(new Texture("finalNight.png"))).setSize(WIDTH, HEIGHT);


        stage.addActor(table);
    }
}
