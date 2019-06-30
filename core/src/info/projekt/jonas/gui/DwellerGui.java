package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.jonas.Registry;
import info.projekt.jonas.dwellers.Dweller;

import java.util.Objects;

import static info.projekt.jonas.gui.RenderUtils.*;

public class DwellerGui extends Gui {

	private final Stage stage;
	private final Table table;

	public DwellerGui() {
		stage = new Stage(new ScreenViewport());
		table = new Table();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		stage.addActor(table);
	}

	@Override
	public void show(Object... o) {
		Dweller dweller = (Dweller) o[0];
		table.reset();
		InfoProjekt.multiplexer.addProcessor(stage);
		dweller.prettyPrint().forEach(s -> table.add(new Label(s, SKIN)));
		table.row();
		ImageButton v = new ImageButton(new TextureRegionDrawable(dweller.getArmor().getTexture()));
		v.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Objects.requireNonNull(GuiProvider.requestGui(ItemSelector.class)).show(Registry.getITEMS(), dweller);
			}
		});
		table.add(v).size(200, 200);
		ImageButton k = new ImageButton(new TextureRegionDrawable(dweller.getWeapon().getTexture()));
		k.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Objects.requireNonNull(GuiProvider.requestGui(ItemSelector.class)).show(Registry.getITEMS(), dweller);
			}
		});
		table.add(k).size(200, 200);
		table.setVisible(true);
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
		RenderUtils.guiOpen = false;
	}

}
