package info.projekt.christoph;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import info.projekt.jonas.Registry;
import info.projekt.jonas.gui.GameScreenGui;
import info.projekt.jonas.gui.Gui;
import info.projekt.jonas.gui.RenderUtils;
import info.projekt.jonas.rooms.Buildable;

import static info.projekt.InfoProjekt.multiplexer;
import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

/**
 * @author Christoph
 * @author Jonas
 */
public class BuildGui extends Gui {

	private final Table table;

	public BuildGui() {

		table = new Table();

		table.setPosition(HALF_WIDTH, HALF_HEIGHT);

		Registry.getRooms().forEach((k, v) -> {
			if (v.getClass().isAnnotationPresent(Buildable.class)) {
				ImageButton button = new ImageButton(new TextureRegionDrawable(v.getTexture()));
				table.add(button).width(200).height(100).padBottom(10);
				button.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						GameScreenGui.setSelectedRoom(k);
						hide();
					}
				});
				table.row();
			}
		});

		stage.addActor(table);

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
