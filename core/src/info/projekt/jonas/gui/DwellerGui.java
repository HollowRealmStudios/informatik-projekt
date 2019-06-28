package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.Registry;
import info.projekt.jonas.dwellers.Dweller;

import static info.projekt.jonas.gui.RenderUtils.*;

class DwellerGui extends Gui {

	final Stage stage;
	private final Table table;
	final ItemSelector selector;


	DwellerGui() {
		stage = new Stage(new ScreenViewport());
		table = new Table();
		selector = new ItemSelector();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		stage.addActor(table);
	}

	void show(Dweller dweller) {
		table.reset();
		GameScreen.manager.addProcessor(stage);
		dweller.prettyPrint().forEach(s -> table.add(new Label(s, SKIN)));
		table.row();
		ImageButton v = new ImageButton(new TextureRegionDrawable(dweller.getArmor().getTexture()));
		v.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				selector.show(Registry.getITEMS(), dweller);
			}
		});
		table.add(v).size(200, 200);
		ImageButton k = new ImageButton(new TextureRegionDrawable(dweller.getWeapon().getTexture()));
		k.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				selector.show(Registry.getITEMS(), dweller);
			}
		});
		table.add(k).size(200, 200);
		table.setVisible(true);
	}

	void hide() {
		GameScreen.manager.removeProcessor(stage);
		table.setVisible(false);
	}

	@Override
	public boolean isVisible() {
		return table.isVisible();
	}
}
