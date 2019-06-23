package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.dwellers.Dweller;

import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

public class DwellerGui {

	public Stage stage;
	private Table table;

	public DwellerGui() {
		stage = new Stage(new ScreenViewport());
		table = new Table();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		stage.addActor(table);
	}

	public void show(Dweller dweller) {
		table.reset();
		dweller.prettyPrint().forEach(s -> table.add(new Label(s, new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")))));
		table.row();
		table.add(new ImageButton(new TextureRegionDrawable(dweller.getArmor().getTexture()))).size(200, 200);
		table.add(new ImageButton(new TextureRegionDrawable(dweller.getWeapon().getTexture()))).size(200, 200);
		table.setVisible(true);
	}

	public void hide() {
		table.setVisible(false);
	}

}
