package info.projekt.jonas.gui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.jonas.rooms.Room;

import java.awt.*;

import static info.projekt.InfoProjekt.*;
import static info.projekt.jonas.gui.RenderUtils.*;


public class GameScreen extends InputAdapter, Screen {

    private final Object o = this;
    private Vector2 cellPosition = new Vector2();
    public static InputMultiplexer multiplexer;
    private ImageButton buildMenu;
    private ImageButton dwellerList;
    private Stage stage;
    private FullscreenGui buildMenuGui;
    private FullscreenGui dwellerListGui;
    private Button eroom;
    private Button froom;
    private Button wroom;
    private Button broom;
    private Button hroom;

    @Override
    public void show() {
        eroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (1f / 28f)), (int) (HEIGHT * 0.05f), (int) (WIDTH * (2f / 7f)), (int) (HEIGHT * 0.4f));
        hroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f / 28f)), (int) (HEIGHT * 0.05f), (int) (WIDTH * (2f / 7f)), (int) (HEIGHT * 0.4f));
        froom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (19f / 28f)), (int) (HEIGHT * 0.05f), (int) (WIDTH * (2f / 7f)), (int) (HEIGHT * 0.4f));
        wroom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (1f / 28f)), (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f / 7f)), (int) (HEIGHT * 0.4f));
        broom = new Button(new Texture("badlogic.jpg"), (int) (WIDTH * (10f / 28f)), (int) ((HEIGHT * 0.05f) + HALF_HEIGHT), (int) (WIDTH * (2f / 7f)), (int) (HEIGHT * 0.4f));
        buildMenuGui = new FullscreenGui(new Texture("finalDay.png"), this) {
            @Override
            public void keyPressed(int key) {
                if (key == Input.Keys.ESCAPE) {
                    buildMenuGui.hide();
                    Gdx.input.setInputProcessor(GameScreen.multiplexer);
                }

            }

            @Override
            public void buttonPressed(Button button) {

            }
        };
        dwellerListGui = new FullscreenGui(new Texture("finalNight.png"), this) {
            @Override
            public void keyPressed(int key) {
                if (key == Input.Keys.ESCAPE) {
                    dwellerListGui.hide();
                    Gdx.input.setInputProcessor(GameScreen.multiplexer);
                }
            }

            @Override
            public void buttonPressed(Button button) {

            }
        };
        buildMenuGui.addComponent(broom);
        buildMenuGui.addComponent(hroom);
        buildMenuGui.addComponent(eroom);
        buildMenuGui.addComponent(froom);
        buildMenuGui.addComponent(wroom);
        buildMenuGui.registerKey(Input.Keys.ESCAPE);
        dwellerListGui.registerKey(Input.Keys.ESCAPE);
        stage = new Stage(new ScreenViewport());
        //Images du noch richtig setzen musst
        dwellerList = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        buildMenu = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
        //Size proportional noch machen du musst
        buildMenu.setSize(100f, 100f);
        dwellerList.setSize(100f, 100f);
        //Position du noch proportional machen musst
        buildMenu.setPosition(200, 200);
        dwellerList.setPosition(200, 700);
        stage.addActor(buildMenu);
        stage.addActor(dwellerList);
        buildMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(event.toString());
                buildMenuGui.show();
                multiplexer.removeProcessor((InputProcessor) o);

            }
        });
        dwellerList.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dwellerListGui.show();
            }
        });

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
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
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(cellPosition.x, cellPosition.y, CELL_WIDTH, CELL_HEIGHT);
        renderer.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        buildMenuGui.paint(batch, renderer);
        dwellerListGui.paint(batch, renderer);
    }

    @Override
    public boolean scrolled(int amount) {
        manager.setZoom(manager.getZoom() + (amount > 0 ? 0.2f : -0.2f));
        return false;
    }

    private void keyDown() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) try {
            GAME_STORAGE.setRoom(new Room("LOL", "room_debug.png"), (int) cellPosition.x / CELL_WIDTH, (int) cellPosition.y / CELL_HEIGHT);
        } catch (ArrayIndexOutOfBoundsException e) {
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
