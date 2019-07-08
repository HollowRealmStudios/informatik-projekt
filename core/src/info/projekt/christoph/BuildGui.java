package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.Gui;
import info.projekt.jonas.gui.RenderUtils;

import static info.projekt.InfoProjekt.multiplexer;
import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Christoph
 * @author Jonas
 */
@SuppressWarnings("SpellCheckingInspection")
public class BuildGui extends Gui {

	private final Table table;

	public BuildGui() {
		table = new Table();

		ImageButton eroom = new ImageButton(new TextureRegionDrawable(new Texture("EngineSell.png")));
		ImageButton wroom = new ImageButton(new TextureRegionDrawable(new Texture("SewageTreatmentPlant/SewageTreatmentPlant_1.png")));
		ImageButton froom = new ImageButton(new TextureRegionDrawable(new Texture("Kitchen/Kitchen_1.png")));
		ImageButton hroom = new ImageButton(new TextureRegionDrawable(new Texture("MedRoom/MedRoom_1.png")));
		ImageButton lroom = new ImageButton(new TextureRegionDrawable(new Texture("Barracks/Barracks_1.png")));
		ImageButton sroom = new ImageButton(new TextureRegionDrawable(new Texture("Storage/Storage_1.png")));

		table.setPosition(HALF_WIDTH, HALF_HEIGHT);

		table.add(eroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f)).padLeft((WIDTH * 1f / 28f));
		table.add(wroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f));
		table.add(froom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f));
		table.row().padTop((HEIGHT * 0.1f));
		table.add(hroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f)).padLeft((WIDTH * 1f / 28f));
		table.add(lroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f));
		table.add(sroom).width((WIDTH * (2f / 7f))).height((HEIGHT * 0.4f)).padRight((WIDTH * 1f / 28f));

		stage.addActor(table);

		eroom.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("EngineRoom");
				hide();
			}
		});
		wroom.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("SewageTreatmentPlant");
				hide();
			}
		});
		froom.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("KitchenRoom");
				hide();

			}
		});
		hroom.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("MedRoom");
				hide();
			}
		});
		lroom.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("BarrackRoom");
				hide();
			}
		});
		sroom.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("StorageRoom");
				hide();
			}
		});
		hide();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(Object... o) {
		multiplexer.addProcessor(stage);
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
		multiplexer.removeProcessor(stage);
		table.setVisible(false);
		RenderUtils.guiOpen = false;
	}
}
