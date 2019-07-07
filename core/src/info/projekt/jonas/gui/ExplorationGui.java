package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import info.projekt.InfoProjekt;

import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Jonas
 */
public class ExplorationGui extends Gui {

	public enum EVENT {ATTACK, DIE, FIND, LOOT, START}

	private Table table;

	public ExplorationGui() {
		table = new Table();
		table.setPosition(HALF_WIDTH, HALF_HEIGHT);
		TextField field = new TextField("", SKIN);
		field.setDisabled(true);
		stage.addActor(table);
		table.add(field).size(500, 500);
		hide();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(Object... o) {
		InfoProjekt.multiplexer.addProcessor(stage);
		table.setVisible(true);
		guiOpen = true;
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
		guiOpen = false;
	}
}
