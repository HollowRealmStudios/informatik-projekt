package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.gui.toolkit.widgets.Notification;
import info.projekt.jonas.gui.toolkit.widgets.Widget;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.storage.StorageHandler;
import info.projekt.jonas.util.TextureLoader;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.*;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class OverlayGui extends Layer implements IHandlesActiveUpdates {

	private final Widget[] permanents = new Widget[]{
			new Label(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.currency)), String.valueOf(GameStorage.INSTANCE.currency), FONT),
			new Label((Gdx.graphics.getWidth() / 5) * 2, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.water)), String.valueOf(GameStorage.INSTANCE.water), FONT),
			new Label((Gdx.graphics.getWidth() / 5) * 3, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.energy)), String.valueOf(GameStorage.INSTANCE.energy), FONT),
			new Label((Gdx.graphics.getWidth() / 5) * 4, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.food)), String.valueOf(GameStorage.INSTANCE.food), FONT),
			new Notification()
	};
	private boolean open = false;
	private final Widget[] opened = new Widget[]{
			new ImageButton(() -> NOTIFICATION_QUEUE.add(new NotificationRequest(RandomStringUtils.randomAlphabetic(ThreadLocalRandom.current().nextInt(5, 50)), 2)), 50, 200, 100, 100, TextureLoader.getTexture("Dwellers.png")),
			new ImageButton(() -> open = false, 50, 50, 100, 100, TextureLoader.getTexture("Down.png")),
			new ImageButton(() -> StorageHandler.saveGame(GameStorage.INSTANCE), 50, 350, 100, 100, TextureLoader.getTexture("Save.png")),
			new ImageButton(() -> LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(BuildGui.class, GUI_LAYER, true)), 50, 500, 100, 100, TextureLoader.getTexture("Build.png")),
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
		if (open) {
			removeWidgets(Arrays.asList(opened));
			removeWidgets(Arrays.asList(closed));
			addWidgets(Arrays.asList(opened));
		} else {
			removeWidgets(Arrays.asList(opened));
			removeWidgets(Arrays.asList(closed));
			addWidgets(Arrays.asList(closed));
		}
		GameStorage.INSTANCE.currency++;
		((Label) permanents[0]).setText(String.valueOf(GameStorage.INSTANCE.currency));
		((Label) permanents[1]).setText(String.valueOf(GameStorage.INSTANCE.water));
		((Label) permanents[2]).setText(String.valueOf(GameStorage.INSTANCE.energy));
		((Label) permanents[3]).setText(String.valueOf(GameStorage.INSTANCE.food));
		((Notification) permanents[4]).update();
	}

}
