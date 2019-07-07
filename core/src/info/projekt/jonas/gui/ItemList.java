package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import info.projekt.InfoProjekt;
import info.projekt.jonas.items.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static info.projekt.jonas.gui.RenderUtils.*;

public class ItemList extends Gui {

	private final Table table;

	public ItemList() {
		table = new Table();
		stage.addActor(table);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(@NotNull Object... o) {
		ArrayList<Item> items = (ArrayList<Item>) o[0];
		table.reset();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		items.forEach(item -> {

			table.addActor(new Image(item.getTexture()));
			table.addActor(new Label(item.toString(), STYLE));
			table.row();
		});
		table.setVisible(true);
		RenderUtils.guiOpen = true;
		InfoProjekt.multiplexer.addProcessor(stage);
	}

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	@Override
	public void hide() {
		table.setVisible(false);
		RenderUtils.guiOpen = false;
		InfoProjekt.multiplexer.removeProcessor(stage);
	}
}
