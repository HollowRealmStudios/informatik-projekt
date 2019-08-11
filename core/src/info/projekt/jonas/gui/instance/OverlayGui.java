package info.projekt.jonas.gui.instance;

import com.badlogic.gdx.Gdx;
import info.projekt.jonas.gui.toolkit.widgets.Image;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.gui.toolkit.widgets.Notification;
import info.projekt.jonas.gui.toolkit.widgets.Widget;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;
import info.projekt.jonas.util.TextureLoader;

import java.util.Arrays;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.LayerSupervisor.LAYER_QUEUE;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class OverlayGui extends Layer implements IHandlesActiveUpdates {

	private final Widget[] permanents = new Widget[]{
			new Label(Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT), String.valueOf(GameStorage.INSTANCE.currency), FONT),
			new Label((Gdx.graphics.getWidth() / 6) * 2, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT), String.valueOf(GameStorage.INSTANCE.water), FONT),
			new Label((Gdx.graphics.getWidth() / 6) * 3, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT), String.valueOf(GameStorage.INSTANCE.energy), FONT),
			new Label((Gdx.graphics.getWidth() / 6) * 4, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT), String.valueOf(GameStorage.INSTANCE.food), FONT),
			new Label((Gdx.graphics.getWidth() / 6) * 5, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT), String.valueOf(GameStorage.INSTANCE.meds), FONT),
			new Notification(),
			new Image(Gdx.graphics.getWidth() / 6 - WidgetUtil.getTextHeight(FONT), Gdx.graphics.getHeight() - 2 * WidgetUtil.getTextHeight(FONT),WidgetUtil.getTextHeight(FONT), WidgetUtil.getTextHeight(FONT), TextureLoader.getTexture("Money.png")),
			new Image(Gdx.graphics.getWidth() / 6 * 2 - WidgetUtil.getTextHeight(FONT), Gdx.graphics.getHeight() - 2 * WidgetUtil.getTextHeight(FONT),WidgetUtil.getTextHeight(FONT), WidgetUtil.getTextHeight(FONT), TextureLoader.getTexture("Water.png")),
			new Image(Gdx.graphics.getWidth() / 6 * 3 - WidgetUtil.getTextHeight(FONT), Gdx.graphics.getHeight() - 2 * WidgetUtil.getTextHeight(FONT),WidgetUtil.getTextHeight(FONT), WidgetUtil.getTextHeight(FONT), TextureLoader.getTexture("Lightning.png")),
			new Image(Gdx.graphics.getWidth() / 6 * 4 - WidgetUtil.getTextHeight(FONT), Gdx.graphics.getHeight() - 2 * WidgetUtil.getTextHeight(FONT),WidgetUtil.getTextHeight(FONT), WidgetUtil.getTextHeight(FONT), TextureLoader.getTexture("Hamburger.png")),
			new Image(Gdx.graphics.getWidth() / 6 * 5 - WidgetUtil.getTextHeight(FONT), Gdx.graphics.getHeight() - 2 * WidgetUtil.getTextHeight(FONT),WidgetUtil.getTextHeight(FONT), WidgetUtil.getTextHeight(FONT), TextureLoader.getTexture("Pill.png"))
	};
	private boolean open = false;
	private final Widget[] opened = new Widget[]{
			new ImageButton(() -> LAYER_QUEUE.add(new LayerRequest(StorageGui.class, GUI_LAYER, true)), 50, 200, 100, 100, TextureLoader.getTexture("Inventory.png")),
			new ImageButton(() -> open = false, 50, 50, 100, 100, TextureLoader.getTexture("Down.png")),
			new ImageButton(() -> LAYER_QUEUE.add(new LayerRequest(DwellerListGui.class, GUI_LAYER, true)), 50, 350, 100, 100, TextureLoader.getTexture("Dwellers.png")),
			new ImageButton(() -> LAYER_QUEUE.add(new LayerRequest(BuildGui.class, GUI_LAYER, true)), 50, 500, 100, 100, TextureLoader.getTexture("Build.png")),
			new ImageButton(() -> Gdx.app.exit(), 50, 650, 100, 100, TextureLoader.getTexture("Close.png")),
	};

	private final Widget[] closed = new Widget[]{
			new ImageButton(() -> open = true, 50, 50, 100, 100, TextureLoader.getTexture("Up.png"))
	};

	public OverlayGui() {
		addWidgets(Arrays.asList(permanents));
		addWidgets(Arrays.asList(closed));
	}

	@Override
	public void onActiveUpdate() {
		removeWidgets(Arrays.asList(opened));
		if (open) {
			removeWidgets(Arrays.asList(closed));
			addWidgets(Arrays.asList(opened));
		} else {
			removeWidgets(Arrays.asList(closed));
			addWidgets(Arrays.asList(closed));
		}
		((Label) permanents[0]).setText(String.valueOf(GameStorage.INSTANCE.currency));
		((Label) permanents[1]).setText(String.valueOf(GameStorage.INSTANCE.water));
		((Label) permanents[2]).setText(String.valueOf(GameStorage.INSTANCE.energy));
		((Label) permanents[3]).setText(String.valueOf(GameStorage.INSTANCE.food));
		((Label) permanents[4]).setText(String.valueOf(GameStorage.INSTANCE.meds));
		((Notification) permanents[5]).update();
	}

}
