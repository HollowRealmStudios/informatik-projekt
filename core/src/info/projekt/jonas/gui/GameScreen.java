package info.projekt.jonas.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.christoph.BuildGui;
import info.projekt.christoph.DwellerList;
import info.projekt.jonas.Registry;
import info.projekt.jonas.rooms.Room;

import java.awt.*;

import static info.projekt.InfoProjekt.*;
import static info.projekt.jonas.gui.RenderUtils.*;

public class GameScreen extends InputAdapter implements Screen {

    public enum Mode {SELECT, UPGRADE, PLACE}

    private static Mode mode = Mode.SELECT;
    private Label currency;
    private static final Logger LOGGER = new Logger("Game Screen");
    private static String selectedRoom = "Kitchen";
    public static InputMultiplexer multiplexer;
    private Stage stage;
    private BuildGui buildGui;
    private DwellerList dwellerList;
    public static Table buttonTable;

    @Override
    public void show() {
        buildGui = new BuildGui();
        dwellerList = new DwellerList();
        dwellerList.table.setVisible(false);
        buildGui.table.setVisible(false);
        stage = new Stage(new ScreenViewport());
        buttonTable = new Table();
        buttonTable.setOrigin(HALF_WIDTH,HALF_HEIGHT);
        currency = new Label(Integer.toString(GAME_STORAGE.currency), new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
        ImageButton dwellerListButton = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        ImageButton buildMenuButton = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        currency.setPosition(50, HEIGHT - 50);
        currency.setFontScale(3);
        buttonTable.add(dwellerListButton).height(HEIGHT * 1f / 14f).width(WIDTH * 1f / 14f).padLeft(WIDTH * 1f / 14f);
        buttonTable.add(buildMenuButton).height(HEIGHT * 1f / 14f).width(WIDTH * 1f / 14f).padLeft(WIDTH * 1f / 14f);
        stage.addActor(currency);
        buildMenuButton.addListener(new ClickListener() {
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
        stage.addActor(buttonTable);
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
        Gdx.gl.glLineWidth(10);
        RenderUtils.clearScreen(new Color(64, 29, 14));
        batch.begin();
        for (int x = 0; x < GAME_STORAGE.getRooms().length; x++) {
            for (int y = 0; y < GAME_STORAGE.getRooms()[0].length; y++) {
                batch.draw(GAME_STORAGE.getRooms()[x][y].getTexture(), x * CELL_WIDTH, y * CELL_HEIGHT);
            }
        }
        batch.end();
        drawOutline();
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
        handleGuiKeys();
        handleMiscKeys();
        handleMouseKeys();
        handleMoveKeys();
        handleOutlineKeys();
    }

    private void handleOutlineKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.F1)) setMode(Mode.SELECT);
        if (Gdx.input.isKeyPressed(Input.Keys.F2)) setMode(Mode.PLACE);
        if (Gdx.input.isKeyPressed(Input.Keys.F3)) setMode(Mode.UPGRADE);

    }

    private void handleMouseKeys() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mode == Mode.PLACE) try {
            if (selectedRoom != null) setRoom(Registry.getRoom(selectedRoom));
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Not in a valid location");
        } finally {
            setMode(Mode.SELECT);
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && mode == Mode.UPGRADE) try {
            if (getSelectedRoom().upgradable() && GAME_STORAGE.currency >= getCost(getSelectedRoom())) {
                getSelectedRoom().upgrade();
                GAME_STORAGE.currency -= getCost(getSelectedRoom());
                updateCurrency();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Not in a valid location");
        } finally {
            setMode(Mode.SELECT);
        }
    }

    private void handleMoveKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10));
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            manager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10));
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10, 0));
        else if (Gdx.input.isKeyPressed(Input.Keys.D))
            manager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10, 0));

    }

    private void handleMiscKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            GAME_STORAGE.currency += 200;
            updateCurrency();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.C)) System.exit(0);
    }

    private void handleGuiKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            if (buildGui.table.isVisible()) buildGui.table.setVisible(false);
            if (buildGui.table.isVisible()) dwellerList.table.setVisible(false);
        }
    }

    private Room getSelectedRoom() {
        Vector3 pos = manager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return GAME_STORAGE.getRooms()[(int) Math.floor(pos.x / CELL_WIDTH)][(int) Math.floor(pos.y / CELL_HEIGHT)];
    }

    private void setRoom(Room room) {
        Vector3 pos = manager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        GAME_STORAGE.setRoom(room, (int) Math.floor(pos.x / CELL_WIDTH), (int) Math.floor(pos.y / CELL_HEIGHT));
    }

    public static void setSelectedRoom(String room) {
        selectedRoom = room;
    }

    public static void setMode(Mode mode) {
        switch (mode) {
            case PLACE:
                renderer.setColor(com.badlogic.gdx.graphics.Color.GREEN);
                GameScreen.mode = Mode.PLACE;
                break;
            case SELECT:
                renderer.setColor(com.badlogic.gdx.graphics.Color.WHITE);
                GameScreen.mode = Mode.SELECT;
                break;
            case UPGRADE:
                renderer.setColor(com.badlogic.gdx.graphics.Color.BLUE);
                GameScreen.mode = Mode.UPGRADE;
                break;
        }
    }

    private void drawOutline() {
        Vector3 pos = manager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect((int) Math.floor(pos.x / CELL_WIDTH) * CELL_WIDTH, (int) Math.floor(pos.y / CELL_HEIGHT) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
        renderer.end();
    }

    private int getCost(Room room) {
        return room.getLevel() * 200;
    }

    private void updateCurrency() {
        currency.setText(Integer.toString(GAME_STORAGE.currency));
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
