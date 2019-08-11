package info.projekt.jonas.gui.instance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.widgets.Image;
import info.projekt.jonas.gui.toolkit.widgets.Label;

import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class LoadingGui extends Layer implements IHandlesActiveUpdates {

	public static int progress;

	private final Image image = new Image(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new Texture("textures/TitleScreen.png"));
	private final Label label = new Label(0, 0, String.valueOf(progress), FONT).centerX().centerY();

	@Override
	public void onActiveUpdate() {
		label.setText(String.valueOf(progress));
	}
}
