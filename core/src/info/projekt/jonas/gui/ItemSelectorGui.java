package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import info.projekt.InfoProjekt;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.util.LimitedInt;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Jonas
 */
public class ItemSelectorGui extends Gui {

	private final Table table;

	public ItemSelectorGui() {
		table = new Table();
		table.setFillParent(true);
		table.background(BACKGROUND);
		stage.addActor(table);
		table.setVisible(false);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(@NotNull Object... o) {
		@SuppressWarnings("unchecked")
		HashMap<String, Item> map = (HashMap<String, Item>) o[0];
		Dweller dweller = (Dweller) o[1];
		RenderUtils.hideAllGuis();
		table.reset();
		LimitedInt i = new LimitedInt(0, Math.toIntExact(Math.round(Math.sqrt(map.size()))), true);
		map.forEach((v, k) -> {
			ImageButton b = new ImageButton(new TextureRegionDrawable(k.getTexture()));
			b.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (k instanceof ArmorItem) dweller.setArmor((ArmorItem) k);
					else dweller.setWeapon((WeaponItem) k);
					hide();
					GuiProvider.requestGui(DwellerGui.class).show(dweller);
				}
			});
			table.add(b);
			i.add(1);
			if (i.get() == 0) table.row();
		});
		InfoProjekt.multiplexer.addProcessor(stage);
		table.setVisible(true);
		GuiProvider.requestGui(GameScreenGui.class).hide();
		RenderUtils.guiOpen = true;
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
		GuiProvider.requestGui(GameScreenGui.class).show();
		RenderUtils.guiOpen = false;
	}
}
