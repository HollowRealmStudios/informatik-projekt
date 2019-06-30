package info.projekt.christoph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.gui.GameScreen;
import info.projekt.jonas.gui.Gui;
import info.projekt.jonas.gui.RenderUtils;

import static info.projekt.InfoProjekt.multiplexer;
import static info.projekt.jonas.gui.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.WIDTH;

/**
 * @author Christoph
 */
@SuppressWarnings("SpellCheckingInspection")
public class BuildGui extends Gui {

	public final Stage stage;
	private final Table table;

	public BuildGui() {
		stage = new Stage(new ScreenViewport());
		table = new Table();

		ImageButton eroom = new ImageButton(new TextureRegionDrawable(new Texture("EngineRoom/EngineRoom_1.png")));
		ImageButton wroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
		ImageButton froom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
		ImageButton hroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
		ImageButton lroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));
		ImageButton sroom = new ImageButton(new TextureRegionDrawable(new Texture("Background.png")));

		table.background(new TextureRegionDrawable(new Texture("finalDay.PNG"))).setSize(WIDTH, HEIGHT);

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
				GameScreen.setMode(GameScreen.Mode.PLACE);
				hide();
			}
		});
		wroom.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("SewageTreatmentPlant");
				GameScreen.setMode(GameScreen.Mode.PLACE);
				hide();
			}
		});
		froom.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("Kitchen");
				GameScreen.setMode(GameScreen.Mode.PLACE);
				hide();

			}
		});
		hroom.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("MedRoom");
				GameScreen.setMode(GameScreen.Mode.PLACE);
				hide();
			}
		});
		lroom.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("Barracks");
				GameScreen.setMode(GameScreen.Mode.PLACE);
				hide();
			}
		});
		sroom.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameScreen.setSelectedRoom("Storage");
				GameScreen.setMode(GameScreen.Mode.PLACE);
				hide();
			}
		});
		hide();
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
