package info.projekt.jonas.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.LimitedArrayList;
import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.NotNull;

import static info.projekt.InfoProjekt.GAME_STORAGE;
import static info.projekt.jonas.gui.RenderUtils.*;

//FIXME
@SuppressWarnings("WeakerAccess")
public class RoomGui {

	public final Stage stage;
	public final Table table;
	private Dweller dweller;
	private final Label info;


	public RoomGui() {
		stage = new Stage(new ScreenViewport());
		table = new Table();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		info = new Label("", SKIN);
		info.setFontScale(2.5f);
		table.add(info);
		table.row().padTop(30f);
		stage.addActor(table);
		table.setVisible(false);
	}

	public void show(@NotNull Room room) {
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
					dweller = label.getTwo();
					room.removeDweller(dweller);
					GameScreen.setMode(GameScreen.Mode.MOVE);
					hide();
				}
			});
		});
		table.row().padTop(20f);
		if (room.upgradable()) {
			ImageButton button = new ImageButton(new TextureRegionDrawable(new Texture("Arrow.png")));
			button.setScale(5, 5);
			button.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (room.upgradable() && GAME_STORAGE.currency >= room.getCost() + (room.getLevel() * 200)) {
						GAME_STORAGE.currency -= room.getCost() + (room.getLevel() * 200);
						room.upgrade();
						info.setText(room.getName() + ", " + room.getLevel());

					}
				}
			});
			table.add(button);
		}
		table.setVisible(true);
	}

	@NotNull
	private LimitedArrayList<Tuple<TextButton, Dweller>> getDwellers(@NotNull Room room) {
		LimitedArrayList<Tuple<TextButton, Dweller>> buttons = new LimitedArrayList<>(4);
		room.getDwellers().forEach(dweller -> buttons.add(new Tuple<>(new TextButton(dweller.toString(), SKIN), dweller)));
		return buttons;
	}

	@SuppressWarnings("WeakerAccess")
	public void hide() {
		table.setVisible(false);
		GameScreen.manager.removeProcessor(stage);
	}

	public void click(@NotNull Room room) {
		room.addDweller(dweller);
	}

}
