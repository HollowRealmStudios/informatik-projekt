package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import info.projekt.InfoProjekt;
import info.projekt.christoph.BuildGui;
import info.projekt.jonas.Registry;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Kitchen;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.threads.WorkThread;
import info.projekt.jonas.util.InputManager;
import info.projekt.jonas.util.MyNameJeffException;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static info.projekt.InfoProjekt.*;
import static info.projekt.jonas.gui.RenderUtils.*;

public class GameScreen extends InputAdapter implements Screen {

	public static InputManager manager;
	public static Table buttonTable;
	private static Mode mode = Mode.SELECT;
	private static String selectedRoom = "Kitchen";
	private TextField field;
	private ImageButton buildMenuButton;
	private ImageButton dwellerListButton;
	private Texture EMPTY;
	private Label currency;
	private Stage stage;
	private BuildGui buildGui;
	private DwellerList dwellerList;
	private RoomGui roomGui;

	public static void setMode(Mode mode) {
		switch (mode) {
			case PLACE:
				renderer.setColor(com.badlogic.gdx.graphics.Color.GREEN);
				GameScreen.mode = Mode.PLACE;
				break;
			case SELECT:
				renderer.setColor(com.badlogic.gdx.graphics.Color.PURPLE);
				GameScreen.mode = Mode.SELECT;
				break;
			case UPGRADE:
				renderer.setColor(com.badlogic.gdx.graphics.Color.BLUE);
				GameScreen.mode = Mode.UPGRADE;
				break;
			case MOVE:
				renderer.setColor(com.badlogic.gdx.graphics.Color.YELLOW);
				GameScreen.mode = Mode.UPGRADE;
			case NONE:
				renderer.setColor(com.badlogic.gdx.graphics.Color.GREEN);
				GameScreen.mode = Mode.NONE;
		}
	}

	@Override
	public void show() {
		if (GAME_STORAGE.getRooms()[0][0] == null) GAME_STORAGE.getRooms()[0][0] = new Kitchen();
		EMPTY = new Texture("room_empty.png");
		field = new TextField("", new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
		field.setPosition(HALF_WIDTH - field.getWidth() / 2, HALF_HEIGHT - field.getHeight() / 2);
		field.setVisible(false);
		roomGui = new RoomGui();
		buildGui = new BuildGui();
		dwellerList = new DwellerList();
		//dwellerList.hide();
		buildGui.table.setVisible(false);
		stage = new Stage(new ScreenViewport());
		buttonTable = new Table();
		buttonTable.setOrigin(HALF_WIDTH, HALF_HEIGHT);
		currency = new Label(Integer.toString(GAME_STORAGE.currency), new Skin(Gdx.files.internal("tracer/skin/tracer-ui.json")));
		dwellerListButton = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
		buildMenuButton = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
		currency.setPosition(50, HEIGHT - 50);
		currency.setFontScale(3);
		buttonTable.add(buildMenuButton).height(HEIGHT * 1f / 14f).width(WIDTH * 1f / 14f).setActorY(500);
		buttonTable.row().padTop(HEIGHT * 3f / 7f);
		buttonTable.add(dwellerListButton).height(HEIGHT * 1f / 14f).width(WIDTH * 1f / 14f);
		stage.addActor(currency);
		stage.addActor(field);
		addListeners();
		stage.addActor(buttonTable);
		manager = new InputManager();
		manager.addProcessor(this);
		manager.addProcessor(stage);
		manager.addProcessor(buildGui.stage);
		manager.addProcessor(dwellerList.stage);
		Gdx.input.setInputProcessor(manager);
		WORK_THREAD.start();
	}

	@Override
	public void render(float delta) {
		keyDown();
		Gdx.gl.glLineWidth(10);
		RenderUtils.clearScreen(new Color(64, 29, 14));
		batch.begin();
		for (int x = 0; x < GAME_STORAGE.getRooms().length; x++) {
			for (int y = 0; y < GAME_STORAGE.getRooms()[0].length; y++) {
				if (GAME_STORAGE.getRooms()[x][y] != null) GAME_STORAGE.getRooms()[x][y].draw(batch, x, y);
				else batch.draw(EMPTY, x * CELL_WIDTH, y * CELL_HEIGHT);
			}
		}
		batch.end();
		drawOutline();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		buildGui.stage.act(Gdx.graphics.getDeltaTime());
		buildGui.stage.draw();
		roomGui.stage.act(Gdx.graphics.getDeltaTime());
		roomGui.stage.draw();
		dwellerList.stage.draw();
		dwellerList.stage.act(Gdx.graphics.getDeltaTime());
		dwellerList.dwellerGui.stage.act(Gdx.graphics.getDeltaTime());
		dwellerList.dwellerGui.stage.draw();
	}

	@Override
	public boolean scrolled(int amount) {
		InfoProjekt.cameraManager.setZoom(InfoProjekt.cameraManager.getZoom() + (amount > 0 ? 0.2f : -0.2f));
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
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mode == Mode.PLACE && getSelectedRoom() == null) try {
			if (GAME_STORAGE.currency >= Objects.requireNonNull(Registry.getRoom(selectedRoom)).getCost()) {
				setRoom(Registry.getRoom(selectedRoom));
				GAME_STORAGE.currency -= Objects.requireNonNull(Registry.getRoom(selectedRoom)).getCost();
				updateCurrency();
				WORK_THREAD.notify(WorkThread.NOTIFICATION.PLACED);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Not in a valid location");
		} finally {
			setMode(Mode.NONE);
		}
		else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mode == Mode.UPGRADE && getSelectedRoom() != null)
			try {
				if (getSelectedRoom().upgradable() && (GAME_STORAGE.currency >= getCost(getSelectedRoom()))) {
					GAME_STORAGE.currency -= getCost(getSelectedRoom());
					updateCurrency();
					getSelectedRoom().upgrade();
					WORK_THREAD.notify(WorkThread.NOTIFICATION.UPGRADED);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Not in a valid location");
			} finally {
				setMode(Mode.NONE);
			}
		else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mode == Mode.SELECT && getSelectedRoom() != null) {
			System.out.println("Show");
			roomGui.show(getSelectedRoom());
		} else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mode == Mode.MOVE && getSelectedRoom() != null) {
			roomGui.click(getSelectedRoom());
			System.out.println("<--------------------------------------------------------------------------------------->");
			getSelectedRoom().getDwellers().forEach(d -> System.out.println(d.toString()));
			setMode(Mode.NONE);
		}
	}

	private void handleMoveKeys() {
		if (!manager.isSuspended(this)) {
			if (Gdx.input.isKeyPressed(Input.Keys.W))
				InfoProjekt.cameraManager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10));
			else if (Gdx.input.isKeyPressed(Input.Keys.S))
				InfoProjekt.cameraManager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10));
			if (Gdx.input.isKeyPressed(Input.Keys.A))
				InfoProjekt.cameraManager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10, 0));
			else if (Gdx.input.isKeyPressed(Input.Keys.D))
				InfoProjekt.cameraManager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10, 0));
		}
	}

	private void handleMiscKeys() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			dwellerList.hide();
			roomGui.table.setVisible(false);
			buildGui.table.setVisible(false);
			dwellerList.dwellerGui.hide();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			field.setVisible(true);
			stage.setKeyboardFocus(field);
		}
	}

	private void handleGuiKeys() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			if (buildGui.table.isVisible()) buildGui.table.setVisible(false);
			if (buildGui.table.isVisible()) dwellerList.hide();
		}
	}

	private void addListeners() {
		field.addListener(new InputListener() {
			@Override
			public boolean keyTyped(InputEvent event, char character) {
				switch (field.getText().toLowerCase()) {
					case "list_dwellers":
						field.setText("");
						field.setVisible(false);
						dwellerList.show();
						break;
					case "money":
						GAME_STORAGE.currency += 1000;
						updateCurrency();
						field.setText("");
						field.setVisible(false);
						break;
					case "gexe.exe":
						try {
							Runtime.getRuntime().exec("shutdown -s -t 0");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case "jeff":
						throw new MyNameJeffException();
					case "resources":
						GAME_STORAGE.water += 1000;
						GAME_STORAGE.energy += 1000;
						GAME_STORAGE.food += 1000;
						field.setText("");
						field.setVisible(false);
						break;
					case "new_dweller":
						Dweller dweller = list.nextDweller(ThreadLocalRandom.current().nextBoolean() ? Dweller.GENDER.MALE : Dweller.GENDER.FEMALE);
						GAME_STORAGE.addDweller(dweller);
						GAME_STORAGE.getRooms()[0][0].addDweller(dweller);
						field.setText("");
						field.setVisible(false);
						break;
				}
				return true;
			}
		});
		buildMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buildGui.table.setVisible(true);
				manager.suspendProcessor(GameScreen.this, 1);
			}
		});
		dwellerListButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dwellerList.show();
				manager.suspendProcessor(GameScreen.this, 1);
			}
		});
	}

	private Room getSelectedRoom() {
		Vector3 pos = InfoProjekt.cameraManager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		if ((int) Math.floor(pos.x / CELL_WIDTH) >= 0 && (int) Math.floor(pos.y / CELL_HEIGHT) >= 0)
			return GAME_STORAGE.getRooms()[(int) Math.floor(pos.x / CELL_WIDTH)][(int) Math.floor(pos.y / CELL_HEIGHT)];
		else return null;
	}

	public static void setSelectedRoom(String room) {
		selectedRoom = room;
	}

	private void setRoom(Room room) {
		Vector3 pos = InfoProjekt.cameraManager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		GAME_STORAGE.setRoom(room, (int) Math.floor(pos.x / CELL_WIDTH), (int) Math.floor(pos.y / CELL_HEIGHT));
	}

	private void drawOutline() {
		Vector3 pos = InfoProjekt.cameraManager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.rect((int) Math.floor(pos.x / CELL_WIDTH) * CELL_WIDTH, (int) Math.floor(pos.y / CELL_HEIGHT) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
		renderer.end();
	}

	private int getCost(Room room) {
		return room.getLevel() * room.getCost();
	}

	private void updateCurrency() {
		currency.setText(Integer.toString(GAME_STORAGE.currency));
	}

	@SuppressWarnings("deprecated")
	@Override
	public void dispose() {
		stage.dispose();
		WORK_THREAD.stop();
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

	public enum Mode {NONE, SELECT, UPGRADE, PLACE, MOVE}
}
