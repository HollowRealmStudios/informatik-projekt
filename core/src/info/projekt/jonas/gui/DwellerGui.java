package info.projekt.jonas.gui;

import info.projekt.jonas.dweller.Dweller;
import info.projekt.jonas.gui.toolkit.Layer;
import info.projekt.jonas.gui.toolkit.capabilities.IHandlesOnOpen;
import info.projekt.jonas.gui.toolkit.widgets.ImageButton;
import info.projekt.jonas.gui.toolkit.widgets.Label;
import info.projekt.jonas.util.TextureLoader;

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
		System.out.println(dweller == null);
		info = new Label(0, HEIGHT - 300, dweller.toString(), FONT).centerX();
		//FIXME
		heal = new ImageButton(() -> dweller.heal(), HALF_WIDTH - 203, HEIGHT - 400, 64, 64, TextureLoader.getTexture("Heal.png"));
		armor = new ImageButton(() -> {}, HALF_WIDTH + 25, HEIGHT - 400, 64, 64, dweller.getArmor().getTexture());
		weapon = new ImageButton(() -> {}, HALF_WIDTH + 25, HEIGHT - 514, 64, 64, dweller.getWeapon().getTexture());
		addWidget(info);
		addWidget(heal);
		addWidget(armor);
		addWidget(weapon);
	}
}
