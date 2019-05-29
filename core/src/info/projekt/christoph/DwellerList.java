package info.projekt.christoph;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * @author Christoph
 * */
public class DwellerList {

    public Stage stage;
    public Table table;

    public DwellerList() {

        stage = new Stage(new ScreenViewport());
        table = new Table();

    }
}
