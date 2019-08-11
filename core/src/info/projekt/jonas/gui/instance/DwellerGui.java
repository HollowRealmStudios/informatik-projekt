package info.projekt.jonas.gui.instance;

import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.LayerSupervisor;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesOnOpen;
import info.projekt.jonas.gui.toolkit.util.LayerRequest;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.util.TextureLoader;

import static info.projekt.jonas.gui.toolkit.LayerSupervisor.GUI_LAYER;
import static info.projekt.jonas.gui.toolkit.util.RenderUtils.*;

public class DwellerGui extends Layer implements IHandlesOnOpen {

	public static Dweller dweller;

	private Label info;
	private ImageButton heal;
	private ImageButton armor;
	private ImageButton weapon;

	@Override
	public void onOpen() {
		removeAll();
		info = new Label(0, HEIGHT - 300, dweller.toString(), FONT).centerX();
		heal = new ImageButton(() -> dweller.heal(), HALF_WIDTH - 89, HEIGHT - 400, 64, 64, TextureLoader.getTexture("Heal.png"));
		armor = new ImageButton(() -> {
			ItemSelectorGui.dweller = dweller;
			ItemSelectorGui.type = ArmorItem.class;
			LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(ItemSelectorGui.class, GUI_LAYER, true));
		}, HALF_WIDTH + 25, HEIGHT - 400, 64, 64, dweller.getArmor().getTexture());
		weapon = new ImageButton(() -> {
			ItemSelectorGui.dweller = dweller;
			ItemSelectorGui.type = WeaponItem.class;
			LayerSupervisor.LAYER_QUEUE.add(new LayerRequest(ItemSelectorGui.class, GUI_LAYER, true));
		}, HALF_WIDTH + 25, HEIGHT - 514, 64, 64, dweller.getWeapon().getTexture());
		addWidget(info);
		addWidget(heal);
		addWidget(armor);
		addWidget(weapon);
	}
}
