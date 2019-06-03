package info.projekt.jonas.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.christoph.BuildGui;
import info.projekt.christoph.DwellerList;
import info.projekt.jonas.Registry;

import java.awt.*;

import static info.projekt.InfoProjekt.*;
import static info.projekt.jonas.gui.RenderUtils.CELL_HEIGHT;
import static info.projekt.jonas.gui.RenderUtils.CELL_WIDTH;


public class GameScreen extends InputAdapter implements Screen {

    private static final Logger LOGGER = new Logger("Game Screen");
    public static String selectedRoom;
    public static com.badlogic.gdx.graphics.Color cursorColor;
    private Vector2 cellPosition = new Vector2();
    public static InputMultiplexer multiplexer;
    private ImageButton buildMenu;
    private ImageButton dwellerListButton;
    private Stage stage;
    private BuildGui buildGui;
    private DwellerList dwellerList;

    @Override
    public void show() {
        cursorColor = new com.badlogic.gdx.graphics.Color(1f, 1f, 1f, 1f);
        buildGui = new BuildGui();
        dwellerList = new DwellerList();
        dwellerList.table.setVisible(false);
        stage = new Stage(new ScreenViewport());
        buildGui.table.setVisible(false);
        //Images du noch richtig setzen musst
        dwellerListButton = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        buildMenu = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        //Size proportional noch machen du musst
        buildMenu.setSize(100f, 100f);
        dwellerListButton.setSize(100f, 100f);
        //Position du noch proportinonal machen musst
        buildMenu.setPosition(200, 200);
        dwellerListButton.setPosition(200, 700);
        stage.addActor(buildMenu);
        stage.addActor(dwellerListButton);
        buildMenu.addListener(new ClickListener() {


            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(event.toString());

                buildGui.table.setVisible(true);

            }
        });
        dwellerListButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dwellerList.table.setVisible(true);
            }
        });

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(buildGui.stage);
        multiplexer.addProcessor(dwellerList.stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        keyDown();
        Vector3 pos = manager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        cellPosition.set((float) (Math.floor(pos.x / CELL_WIDTH) * CELL_WIDTH), (float) (Math.floor(pos.y / CELL_HEIGHT) * CELL_HEIGHT));
        Gdx.gl.glLineWidth(10);
        RenderUtils.clearScreen(new Color(49, 144, 175));
        batch.begin();
        for (int x = 0; x < GAME_STORAGE.getRooms().length; x++) {
            for (int y = 0; y < GAME_STORAGE.getRooms()[0].length; y++) {
                batch.draw(GAME_STORAGE.getRooms()[x][y].getTexture(), x * CELL_WIDTH, y * CELL_HEIGHT);
            }
        }
        batch.end();
        renderer.setColor(cursorColor);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(cellPosition.x, cellPosition.y, CELL_WIDTH, CELL_HEIGHT);
        renderer.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        buildGui.stage.act(Gdx.graphics.getDeltaTime());
        buildGui.stage.draw();
        dwellerList.stage.act(Gdx.graphics.getDeltaTime());
        dwellerList.stage.draw();

    }

    @Override
    public boolean scrolled(int amount) {
        manager.setZoom(manager.getZoom() + (amount > 0 ? 0.2f : -0.2f));
        return false;
    }

    private void keyDown() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) try {
            GAME_STORAGE.setRoom(Registry.getRoom(selectedRoom), (int) cellPosition.x / CELL_WIDTH, (int) cellPosition.y / CELL_HEIGHT);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
            LOGGER.error("No room selected to place");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10));
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10, 0));
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10, 0));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            System.exit(0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            if (buildGui.table.isVisible()) {
                buildGui.table.setVisible(false);
            } else if (dwellerList.table.isVisible()) {
                dwellerList.table.setVisible(false);
            }


        }

    }


    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
