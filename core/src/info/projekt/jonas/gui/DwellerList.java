package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.util.LimitedArrayList;
import info.projekt.jonas.util.Tuple;

import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

public class DwellerList {

	public Stage stage;
	private Table table;
	public DwellerGui dwellerGui;

	public DwellerList() {
		stage = new Stage(new ScreenViewport());
		table = new Table();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		stage.addActor(table);
		dwellerGui = new DwellerGui();
	}

	public void show() {
		table.reset();
		Label label = new Label("Dwellers: ", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
		label.setFontScale(2.5f);
		table.add(label).padTop(30f);
		table.row();
		GameScreen.manager.addProcessor(stage);
		getDwellers().forEach(tuple -> {
			table.add(tuple.getOne()).padTop(20f);
			tuple.getOne().addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					dwellerGui.show(tuple.getTwo());
				}
			});
			table.row();
		});

		table.setVisible(true);
	}

	private LimitedArrayList<Tuple<Label, Dweller>> getDwellers() {
		LimitedArrayList<Tuple<Label, Dweller>> buttons = new LimitedArrayList<>(4);
		InfoProjekt.GAME_STORAGE.getDwellers().forEach(dweller -> buttons.add(new Tuple<>(new Label(dweller.toString(), new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json"))), dweller)));
		return buttons;
	}

	public void hide() {
		table.setVisible(false);
		GameScreen.manager.removeProcessor(stage);
	}

}
