package info.projekt.jonas.gui.instance;

import com.badlogic.gdx.Gdx;
import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesActiveUpdates;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesOnOpen;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.util.NotificationRequest;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.TextButton;
import info.projekt.jonas.storage.GameStorage;
import info.projekt.jonas.util.TextureLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.*;

/**
 * @author Jonas
 */
public class DwellerListGui extends Layer implements IHandlesActiveUpdates {

	private int page = 0;
	private int pages;

	@NotNull
	@Contract(pure = true)
	private ArrayList<List<Dweller>> split() {
		ArrayList<List<Dweller>> dwellers = new ArrayList<>();
		int perPage = (HEIGHT - 400) / 100;
		int pages = (int) Math.ceil((float) GameStorage.INSTANCE.getDwellers().size() / (float) perPage);
		this.pages = pages;
		if (pages > 1) {
			for (int page = 0; page < pages; page++) {
				if (page + 1 == pages)
					dwellers.add(GameStorage.INSTANCE.getDwellers().subList(page * perPage, GameStorage.INSTANCE.getDwellers().size()));
				else
					dwellers.add(GameStorage.INSTANCE.getDwellers().subList(page * perPage, (page + 1) * perPage));
			}
		} else dwellers.add(GameStorage.INSTANCE.getDwellers());
		return dwellers;
	}

	@Override
	public void onActiveUpdate() {
		if (GameStorage.INSTANCE.getDwellers().isEmpty()) {
			LayerSupervisor.NOTIFICATION_QUEUE.add(new NotificationRequest("No Dwellers available", 2));
			LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(null, GUI_LAYER, true));
			return;
		}
		boolean overflow = GameStorage.INSTANCE.getDwellers().size() * 100 > Gdx.graphics.getHeight() - 400;
		removeAll();
		AtomicInteger i = new AtomicInteger(HEIGHT - 200);
		split().get(page).forEach(dweller -> {
			addWidget(new TextButton(() -> {
				DwellerGui.dweller = dweller;
				LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(DwellerGui.class, GUI_LAYER, true));
			}, 0, i.get(), dweller.toString(), FONT).centerX());
			i.addAndGet(-100);
		});
		if (overflow) {
			addWidget(new ImageButton(() -> {
				if (page != pages - 1) page++;
			}, HALF_WIDTH + 25, 200, 64, 64, TextureLoader.getTexture("Right.png")));
			addWidget(new ImageButton(() -> {
				if (page != 0) page--;
			}, HALF_WIDTH - 89, 200, 64, 64, TextureLoader.getTexture("Left.png")));
		}
	}
}
