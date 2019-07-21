package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import info.projekt.InfoProjekt;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.util.LimitedInt;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static info.projekt.jonas.gui.RenderUtils.BACKGROUND;
import static info.projekt.jonas.gui.RenderUtils.STYLE;

public class ItemListGui extends Gui {

	private Table table;

	@Override
	public void dispose() {
		stage.dispose();
	}

	public ItemListGui() {
		table = new Table();
		table.setFillParent(true);
		table.background(BACKGROUND);
		table.setVisible(false);
	}

	@Override
	public void show(@NotNull Object... o) {
		table.reset();
		ArrayList<Item> items = (ArrayList<Item>) o[0];
		AtomicInteger i = new AtomicInteger(1);
		items.forEach(item -> {
			table.add(new Image(item.getTexture())).padRight(10);
			Label l = new Label(item.prettyPrint(), STYLE);
			l.setFontScale(0.3f);
			table.add(l).padRight(50);
			if(i.get() % 3 == 0) table.row();
			i.incrementAndGet();
		});
		table.setVisible(true);
		InfoProjekt.multiplexer.addProcessor(stage);
		stage.addActor(table);
		GuiProvider.requestGui(GameScreenGui.class).hide();
		RenderUtils.guiOpen = true;
	}

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	@Override
	public void hide() {
		InfoProjekt.multiplexer.removeProcessor(stage);
		table.setVisible(false);
		GuiProvider.requestGui(GameScreenGui.class).show();
		RenderUtils.guiOpen = false;
	}
}
