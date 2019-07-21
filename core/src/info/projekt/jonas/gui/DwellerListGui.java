package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import info.projekt.InfoProjekt;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.util.LimitedArrayList;
import info.projekt.jonas.util.Tuple;

import java.util.ArrayList;

import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Jonas
 */
public class DwellerListGui extends Gui {

	private final Table table;

	public DwellerListGui() {
		table = new Table();
		table.setFillParent(true);
		table.background(BACKGROUND);
		stage.addActor(table);
		table.setVisible(false);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(Object... o) {
		InfoProjekt.multiplexer.addProcessor(stage);
		table.reset();
		table.add(new Label("Dwellers: ", STYLE)).padTop(30f);
		table.row();
		getDwellers().forEach(tuple -> {
			table.add(tuple.getOne()).padTop(20f);
			tuple.getOne().addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					GuiProvider.requestGui(DwellerGui.class).show(tuple.getTwo());
					hide();
				}
			});
			table.row();
		});

		table.setVisible(true);
		GuiProvider.requestGui(GameScreenGui.class).hide();
		RenderUtils.guiOpen = true;
	}

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	private ArrayList<Tuple<TextButton, Dweller>> getDwellers() {
		ArrayList<Tuple<TextButton, Dweller>> buttons = new ArrayList<>();
		InfoProjekt.GAME_STORAGE.getDwellers().forEach(dweller -> buttons.add(new Tuple<>(new TextButton(dweller.toString(), SKIN), dweller)));
		return buttons;
	}

	@Override
	public void hide() {
		table.setVisible(false);
		GuiProvider.requestGui(GameScreenGui.class).show();
		InfoProjekt.multiplexer.removeProcessor(stage);
		RenderUtils.guiOpen = false;
	}
}
