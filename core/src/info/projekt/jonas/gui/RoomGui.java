package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;
import org.jetbrains.annotations.NotNull;

import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

//FIXME
public class RoomGui {

    private Dweller dweller;
    private Label info;
    public Stage stage;
    public Table table;


    public RoomGui() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setPosition(HALF_WIDTH, HALF_HEIGHT);
        info = new Label("", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
        info.setFontScale(2.5f);
        table.add(info);
        table.row().padTop(30f);
        stage.addActor(table);
        table.setVisible(false);
    }

    public void show(Room room) {
        table.reset();
        GameScreen.manager.addProcessor(stage);
        info.setText(room.getName() + ", " + room.getLevel());
        table.add(info);
        table.row().padTop(20f);
        getDwellers(room).forEach(label -> {
            table.add(label.getOne()).padTop(20f);
            label.getOne().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                	System.out.println("Click");
                    dweller = label.getTwo();
                    hide();
                    room.removeDweller(dweller);
                    room.getDwellers().forEach(d -> System.out.println(d.toString()));
                    GameScreen.setMode(GameScreen.Mode.MOVE);
                }
            });
        });

        table.setVisible(true);
    }

    private LimitedArrayList<Tuple<TextButton, Dweller>> getDwellers(Room room) {
        LimitedArrayList<Tuple<TextButton, Dweller>> buttons = new LimitedArrayList<>(4);
        room.getDwellers().forEach(dweller -> buttons.add(new Tuple<>(new TextButton(dweller.toString(), new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json"))), dweller)));
        return buttons;
    }

    public void hide() {
        table.setVisible(false);
        GameScreen.manager.removeProcessor(stage);
    }

    public void click(@NotNull Room room) {
        room.addDweller(dweller);
    }

}
