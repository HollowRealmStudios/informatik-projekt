package info.projekt.jonas.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import info.projekt.InfoProjekt;
import info.projekt.jonas.Registry;
import info.projekt.jonas.items.CraftingRecipe;
import info.projekt.jonas.util.Tuple;

import java.util.ArrayList;

import static info.projekt.InfoProjekt.GAME_STORAGE;
import static info.projekt.jonas.gui.RenderUtils.BACKGROUND;
import static info.projekt.jonas.gui.RenderUtils.SKIN;

public class WorkshopGui extends Gui {

	private final Table table;

	public WorkshopGui() {
		table = new Table();
		table.setFillParent(true);
		table.background(BACKGROUND);
		table.setVisible(false);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(Object... o) {
		table.reset();
		getRecipes().forEach(t -> {
				t.getOne().addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						System.out.println("Craft");
						t.getTwo().craft(GAME_STORAGE.COMPONENTS);
					}
				});
				table.add(t.getOne());
				table.row().padTop(10);
		});
		stage.addActor(table);
		InfoProjekt.multiplexer.addProcessor(stage);
		RenderUtils.guiOpen = true;
		table.setVisible(true);
		GuiProvider.requestGui(GameScreenGui.class).hide();
	}

	@Override
	public void act(float f) {
		stage.act(f);
		stage.draw();
	}

	@Override
	public void hide() {
		InfoProjekt.multiplexer.removeProcessor(stage);
		RenderUtils.guiOpen = false;
		GuiProvider.requestGui(GameScreenGui.class).show();
		table.setVisible(false);
	}

	private ArrayList<Tuple<TextButton, CraftingRecipe>> getRecipes() {
		ArrayList<Tuple<TextButton, CraftingRecipe>> recipes = new ArrayList<>();
		Registry.getRecipes().forEach(r -> recipes.add(new Tuple<TextButton, CraftingRecipe>(new TextButton(r.prettyPrint(), SKIN), r)));
		return recipes;
	}
}
