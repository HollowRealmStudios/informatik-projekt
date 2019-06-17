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

import static info.projekt.jonas.gui.RenderUtils.HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.WIDTH;

/**
 * @author Christoph
 */
public class DwellerList {

	public Stage stage;
	public Table table;


	public DwellerList() {

		stage = new Stage(new ScreenViewport());
		table = new Table();

		table.background(new TextureRegionDrawable(new Texture("finalNight.png"))).setSize(WIDTH, HEIGHT);
		stage.addActor(table);

		ImageButton close = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
		table.add(close).width(WIDTH / 2f).height(HEIGHT / 2f);

		close.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				table.setVisible(false);
				GameScreen.buttonTable.setVisible(true);


			}

		});

	}
}
