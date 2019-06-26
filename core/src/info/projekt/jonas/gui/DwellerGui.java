package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.Registry;
import info.projekt.jonas.dwellers.Dweller;

import static info.projekt.jonas.gui.RenderUtils.HALF_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.HALF_WIDTH;

public class DwellerGui {

    public Stage stage;
    private Table table;
    public ItemSelector selector;


    public DwellerGui() {
        stage = new Stage(new ScreenViewport());
        table = new Table();
        selector = new ItemSelector();
        table.setPosition(HALF_WIDTH, HALF_HEIGHT);
        stage.addActor(table);
    }

    public void show(Dweller dweller) {
        table.reset();
        GameScreen.manager.addProcessor(stage);
        dweller.prettyPrint().forEach(s -> table.add(new Label(s, new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")))));
        table.row();
        ImageButton v = new ImageButton(new TextureRegionDrawable(dweller.getArmor().getTexture()));
        v.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selector.show(Registry.getITEMS(), dweller);
            }
        });
        table.add(v).size(200, 200);
        ImageButton k = new ImageButton(new TextureRegionDrawable(dweller.getWeapon().getTexture()));
        k.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selector.show(Registry.getITEMS(), dweller);
            }
        });
        table.add(k).size(200, 200);
        table.setVisible(true);
    }

    public void hide() {
        GameScreen.manager.removeProcessor(stage);
        table.setVisible(false);
    }
}
