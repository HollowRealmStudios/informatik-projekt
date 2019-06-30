package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.util.LimitedArrayList;
import info.projekt.jonas.util.Tuple;

import static info.projekt.jonas.gui.RenderUtils.*;

public class DwellerList extends Gui {

    public final Stage stage;
    public final DwellerGui dwellerGui;
    private final Table table;

    public DwellerList() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setPosition(HALF_WIDTH, HALF_HEIGHT);
        stage.addActor(table);
        dwellerGui = new DwellerGui();
    }

    @Override
    public void show(Object... o) {
        table.reset();
        Label label = new Label("Dwellers: ", SKIN);
        label.setFontScale(2.5f);
        table.add(label).padTop(30f);
        table.row();
        GameScreen.multiplexer.addProcessor(stage);

        getDwellers().forEach(tuple -> {
            table.add(tuple.getOne()).padTop(20f);
            tuple.getOne().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    dwellerGui.show(tuple.getTwo());
                    hide();
                }
            });
            table.row();
        });

        table.setVisible(true);
	    GameScreen.guiOpen = true;
    }

    private LimitedArrayList<Tuple<TextButton, Dweller>> getDwellers() {
        LimitedArrayList<Tuple<TextButton, Dweller>> buttons = new LimitedArrayList<>(4);
        InfoProjekt.GAME_STORAGE.getDwellers().forEach(dweller -> buttons.add(new Tuple<>(new TextButton(dweller.toString(), SKIN), dweller)));
        return buttons;
    }

    @Override
    public void hide() {
        table.setVisible(false);
        GameScreen.multiplexer.removeProcessor(stage);
	    GameScreen.guiOpen = false;
    }
}
