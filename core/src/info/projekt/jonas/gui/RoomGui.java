package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.rooms.Room;

import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

//FIXME
public class RoomGui {

	private Label level;
	private Label name;
	private Stage stage;
	private Table table;


	public RoomGui() {
		stage = new Stage(new ScreenViewport());
		table = new Table();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		level = new Label("", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
		name = new Label("", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
		level.setFontScale(3);
		name.setFontScale(3);
		table.add(name);
		table.row().height(30);
		table.add(level);
		stage.addActor(table);
	}

	public void show(Room room) {
		stage.act();
		if (room != null) {
			level.setText(room.getLevel());
			name.setText(room.getName());
		} else {
			level.setText("---------");
			name.setText("---------");
		}
		table.setVisible(true);
	}

	public void hide() {
		table.setVisible(false);
	}

}
