package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.rooms.Room;

public class RoomGui {

    private Label level;
    private Label name;
    private Stage stage;
    private Table table;


    public RoomGui() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        level = new Label("", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
        name = new Label("", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
        level.setFontScale(3);
        name.setFontScale(3);
        table.add(name);
        table.row().height(30);
        table.add(level);
    }

    public void show(Room room) {
        level.setText(room.getLevel());
        name.setText(room.getName());
        table.setVisible(true);
    }

    public void hide() {
        table.setVisible(false);
    }

}
