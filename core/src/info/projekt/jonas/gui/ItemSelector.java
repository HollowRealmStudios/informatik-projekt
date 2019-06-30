package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.items.ArmorItem;
import info.projekt.jonas.items.Item;
import info.projekt.jonas.items.WeaponItem;
import info.projekt.jonas.util.LimitedInt;

import java.lang.reflect.Parameter;
import java.util.HashMap;

import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

public class ItemSelector extends Gui {

    public final Stage stage;
    private final Table table;

    public ItemSelector() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setPosition(HALF_WIDTH, HALF_HEIGHT);
        stage.addActor(table);
    }

	@Override
	public void show(Object... o) {
    	@SuppressWarnings("unchecked")
    	HashMap<String, Item> map = (HashMap<String, Item>) o[0];
		Dweller dweller = (Dweller) o[1];
    	GameScreen.hideGuis();
		table.reset();
		LimitedInt i = new LimitedInt(0, Math.toIntExact(Math.round(Math.sqrt(map.size()))), true);
		map.forEach((v, k) -> {
			ImageButton b = new ImageButton(new TextureRegionDrawable(k.getTexture()));
			b.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					System.out.println(k.toString());
					if(k instanceof  ArmorItem) dweller.setArmor((ArmorItem) k);
					else dweller.setWeapon((WeaponItem) k);
					hide();
					GuiProvider.requestGui(DwellerGui.class).show(dweller);
				}
			});
			table.add(b);
			i.add(1);
			if (i.get() == 0) table.row();
		});
		GameScreen.multiplexer.addProcessor(stage);
		table.setVisible(true);
		GameScreen.guiOpen = true;
	}

	@Override
    public void hide() {
        GameScreen.multiplexer.removeProcessor(stage);
        table.setVisible(false);
	    GameScreen.guiOpen = false;
    }
}
