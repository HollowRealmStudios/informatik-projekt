package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.WidgetUtil;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.gui.toolkit.widgets.Widget;
import info.projekt.jonas.gui.toolkit.widgets.button.ImageButton;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.TextureLoader;

import java.util.Arrays;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.OVERLAY_LAYER;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.FONT;

public class OverlayGuiOpened extends Layer implements IHandlesActiveUpdates {

	private final Widget[] widgets = new Widget[] {
			new Label(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.currency)), String.valueOf(GameStorage.INSTANCE.currency), FONT),
			new Label((Gdx.graphics.getWidth() / 5) * 2, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.water)), String.valueOf(GameStorage.INSTANCE.water), FONT),
			new Label((Gdx.graphics.getWidth() / 5) * 3, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.energy)), String.valueOf(GameStorage.INSTANCE.energy), FONT),
			new Label((Gdx.graphics.getWidth() / 5) * 4, Gdx.graphics.getHeight() - WidgetUtil.getTextHeight(FONT, String.valueOf(GameStorage.INSTANCE.food)), String.valueOf(GameStorage.INSTANCE.food), FONT),
			new ImageButton(() -> LayerSupervisor.LAYER_STACK.push(new LayerRequest(OverlayGuiClosed.class, OVERLAY_LAYER, true)), 50, 200, 100, 100, TextureLoader.getTexture("Dwellers.png")),
			new ImageButton(() -> LayerSupervisor.LAYER_STACK.push(new LayerRequest(OverlayGuiClosed.class, OVERLAY_LAYER, true)), 50, 50, 100, 100, TextureLoader.getTexture("Down.png")),
			new ImageButton(() -> LayerSupervisor.LAYER_STACK.push(new LayerRequest(OverlayGuiClosed.class, OVERLAY_LAYER, true)), 50, 350, 100, 100, TextureLoader.getTexture("Save.png")),
			new ImageButton(() -> LayerSupervisor.LAYER_STACK.push(new LayerRequest(OverlayGuiClosed.class, OVERLAY_LAYER, true)), 50, 500, 100, 100, TextureLoader.getTexture("Build.png")),
			new ImageButton(() -> LayerSupervisor.LAYER_STACK.push(new LayerRequest(OverlayGuiClosed.class, OVERLAY_LAYER, true)), 50, 650, 100, 100, TextureLoader.getTexture("Close.png")),
			new ImageButton(() -> LayerSupervisor.LAYER_STACK.push(new LayerRequest(OverlayGuiClosed.class, OVERLAY_LAYER, true)), 50, 800, 100, 100, TextureLoader.getTexture("Build.png"))
	};

	public OverlayGuiOpened() {
		addWidgets(Arrays.asList(widgets));
	}

	@Override
	public void onUpdate() {
		GameStorage.INSTANCE.currency++;
		((Label) widgets[0]).setText(Gdx.input.getX() + " / " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
		//((Label)widgets[0]).setText(String.valueOf(GameStorage.INSTANCE.currency));
		((Label)widgets[1]).setText(String.valueOf(GameStorage.INSTANCE.water));
		((Label)widgets[2]).setText(String.valueOf(GameStorage.INSTANCE.energy));
		((Label)widgets[3]).setText(String.valueOf(GameStorage.INSTANCE.food));
	}
}
