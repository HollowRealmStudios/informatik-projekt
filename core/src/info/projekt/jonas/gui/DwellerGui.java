package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.dwellers.Dweller;

public class DwellerGui {

	public Stage stage;
	private Table table;

	public DwellerGui() {
		stage = new Stage(new ScreenViewport());
		table = new Table();
	}

	public void show(Dweller dweller) {
		table.reset();
		dweller.prettyPrint().forEach(s -> table.add(new Label(s, new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")))).padTop(20));
		table.setVisible(true);
	}

	public void hide() {
		table.setVisible(false);
	}

}
