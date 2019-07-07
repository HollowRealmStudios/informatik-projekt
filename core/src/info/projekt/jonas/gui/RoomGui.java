package info.projekt.jonas.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import info.projekt.InfoProjekt;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.threads.WorkThread;
import info.projekt.jonas.util.LimitedArrayList;
import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.NotNull;

import static info.projekt.InfoProjekt.GAME_STORAGE;
import static info.projekt.InfoProjekt.WORK_THREAD;
import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Jonas
 */
public class RoomGui extends Gui {

	static Dweller selected;
	private final Table table;
	private final Label info;

	public RoomGui() {
		table = new Table();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		info = new Label("", STYLE);
		table.add(info);
		table.row().padTop(30f);
		stage.addActor(table);
		table.setVisible(false);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(@NotNull Object... o) {
		Room room = (Room) o[0];
		table.reset();
		InfoProjekt.multiplexer.addProcessor(stage);
		info.setText(room.getName() + ", " + room.getLevel());
		table.add(info);
		table.row();
		getDwellers(room).forEach(label -> {
			table.add(label.getOne()).padTop(20f);
			label.getOne().addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (selected == null) {
						selected = label.getTwo();
						room.removeDweller(selected);
						GameScreen.moving = true;
					}
					hide();
				}
			});
			table.row();
		});
		if (room.upgradable()) {
			ImageButton button = new ImageButton(new TextureRegionDrawable(new Texture("Arrow.png")));
			button.setScale(5, 5);
			if (room.upgradable()) table.add(button);
			button.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (GAME_STORAGE.currency >= room.getCost() + (room.getLevel() * 200)) {
						GAME_STORAGE.currency -= room.getCost() + (room.getLevel() * 200);
						WORK_THREAD.notify(WorkThread.NOTIFICATION.UPGRADED);
						room.upgrade();
						info.setText(room.getName() + ", " + room.getLevel());
						if (!room.upgradable()) table.removeActor(button);
					}
				}
			});
		}
		table.setVisible(true);
		RenderUtils.guiOpen = true;
	}

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	@NotNull
	private LimitedArrayList<Tuple<TextButton, Dweller>> getDwellers(@NotNull Room room) {
		LimitedArrayList<Tuple<TextButton, Dweller>> buttons = new LimitedArrayList<>(4);
		room.getDwellers().forEach(dweller -> buttons.add(new Tuple<>(new TextButton(dweller.toString(), SKIN), dweller)));
		return buttons;
	}

	@Override
	public void hide() {
		table.setVisible(false);
		InfoProjekt.multiplexer.removeProcessor(stage);
		RenderUtils.guiOpen = false;
	}
}
