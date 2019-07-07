package info.projekt.jonas.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import info.projekt.InfoProjekt;
import info.projekt.christoph.BuildGui;
import info.projekt.christoph.SettingsGui;
import info.projekt.jonas.Registry;
import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.InfoCenter;
import info.projekt.jonas.util.MyNameJeffException;
import info.projekt.jonas.util.NameList;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static info.projekt.InfoProjekt.*;
import static info.projekt.jonas.gui.RenderUtils.*;

/**
 * @author Christoph
 * @author Jonas
 */
public class GameScreen extends Gui {

	public static boolean moving;
	private static String selectedRoom = "";
	private final TextField field;
	private final ImageButton buildMenuButton;
	private final ImageButton dwellerListButton;
	private final Texture EMPTY;
	private final Label currency;
	private final Label food;
	private final Label water;
	private final Label energy;
	private final ImageButton mainMenuButton;
	private final ImageButton mmQuests;
	private final ImageButton mmStats;
	private final ImageButton mmSettings;
	private final ImageButton mmStorage;
	public static final Notification notification = new Notification("", STYLE);
	private boolean mainMenuActivated;

	public GameScreen() {
		mainMenuActivated = false;
		dwellerListButton = new ImageButton(new TextureRegionDrawable(new Texture("Dwellers.png")));
		buildMenuButton = new ImageButton(new TextureRegionDrawable(new Texture("Build.png")));
		mainMenuButton = new ImageButton(new TextureRegionDrawable(new Texture("Up.png")));
		mmQuests = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
		mmSettings = new ImageButton(new TextureRegionDrawable(new Texture("Settings.png")));
		mmStats = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));
		mmStorage = new ImageButton(new TextureRegionDrawable(new Texture("badlogic.jpg")));

		buildMenuButton.setSize(100, 100);
		dwellerListButton.setSize(100, 100);
		mainMenuButton.setSize(100, 100);
		mmSettings.setSize(100, 100);
		mmStats.setSize(100, 100);
		mmStorage.setSize(100, 100);
		mmQuests.setSize(100, 100);

		dwellerListButton.setPosition(100, 100);
		buildMenuButton.setPosition(100, 250);
		mainMenuButton.setPosition(WIDTH - 200, 100);
		mmSettings.setPosition(WIDTH - 200, 250);
		mmStats.setPosition(WIDTH - 200, 400);
		mmStorage.setPosition(WIDTH - 200, 550);
		mmQuests.setPosition(WIDTH - 200, 700);

		EMPTY = new Texture("room_empty.png");

		field = new TextField("", SKIN);
		field.setPosition(HALF_WIDTH - field.getWidth() / 2, HALF_HEIGHT - field.getHeight() / 2);
		field.setVisible(false);

		currency = new Label(Integer.toString(GAME_STORAGE.currency), STYLE);
		currency.setPosition(50, HEIGHT - 100);

		food = new Label(Integer.toString(GAME_STORAGE.food.get()), STYLE);
		food.setPosition((float) (WIDTH / 3.0 - (food.getWidth() / 2)), HEIGHT - 100);

		energy = new Label(Integer.toString(GAME_STORAGE.energy.get()), STYLE);
		energy.setPosition(HALF_WIDTH - (energy.getWidth() / 2), HEIGHT - 100);

		water = new Label(Integer.toString(GAME_STORAGE.water.get()), STYLE);
		water.setPosition((float) (2 * WIDTH / 3.0 - (water.getWidth() / 2)), HEIGHT - 100);

		notification.setPosition(HALF_WIDTH - notification.getWidth() / 2, 50);

		stage.addActor(buildMenuButton);
		stage.addActor(dwellerListButton);
		stage.addActor(currency);
		stage.addActor(food);
		stage.addActor(energy);
		stage.addActor(water);
		stage.addActor(field);
		stage.addActor(mainMenuButton);
		stage.addActor(mmQuests);
		stage.addActor(mmSettings);
		stage.addActor(mmStorage);
		stage.addActor(mmStats);
		stage.addActor(notification);
		addListeners();
		hideMainMenuButtons();
	}

	private void hideMainMenuButtons() {
		mmStorage.setVisible(false);
		mmStats.setVisible(false);
		mmQuests.setVisible(false);
		mmSettings.setVisible(false);
	}

	private void showMainMenuButtons() {
		mmStorage.setVisible(true);
		mmStats.setVisible(true);
		mmQuests.setVisible(true);
		mmSettings.setVisible(true);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show(Object... o) {
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(this);
		water.setVisible(true);
		food.setVisible(true);
		energy.setVisible(true);
		currency.setVisible(true);
		dwellerListButton.setVisible(true);
		buildMenuButton.setVisible(true);
	}

	@Override
	public void act(float f) {
		if (multiplexer.getProcessors().contains(this, true)) {
			keyDown();
			updateGui();
			stage.act(f);
			stage.draw();
		}
	}

	public void render() {
		Gdx.gl.glLineWidth(10);
		RenderUtils.clearScreen(new Color(52, 26, 12));
		batch.begin();
		for (int x = 0; x < GAME_STORAGE.getRooms().length; x++) {
			for (int y = 0; y < GAME_STORAGE.getRooms()[0].length; y++) {
				if (GAME_STORAGE.getRooms()[x][y] != null) GAME_STORAGE.getRooms()[x][y].draw(batch, x, y);
				else batch.draw(EMPTY, x * CELL_WIDTH, y * CELL_HEIGHT);
			}
		}
		batch.end();
		drawOutline();
		InfoCenter.recalculate();
		GAME_STORAGE.ITEMS.setSize(InfoCenter.storageCapacity);
		keyDown();
	}

	@Override
	public boolean scrolled(int amount) {
		InfoProjekt.cameraManager.setZoom(InfoProjekt.cameraManager.getZoom() + (amount > 0 ? 0.02f : -0.02f));
		return false;
	}

	private void keyDown() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) RenderUtils.hideAllGuisExcept(GameScreen.class);
		if (!guiOpen) {
			handleMiscKeys();
			handleMoveKeys();
			handleMouseKeys();
		}
	}

	private void handleMouseKeys() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if (moving && getSelectedRoom() != null && getSelectedRoom().getDwellers().size() != 4) {
				moving = false;
				getSelectedRoom().addDweller(RoomGui.selected);
				RoomGui.selected = null;
			} else if (getSelectedRoom() != null)
				getSelectedRoom().clicked();
			else if (!selectedRoom.equals("") && GAME_STORAGE.currency >= Objects.requireNonNull(Registry.getRoom(selectedRoom)).getCost()) {
				try {
					setRoom(Registry.getRoom(selectedRoom));
					GAME_STORAGE.currency -= Objects.requireNonNull(Registry.getRoom(selectedRoom)).getCost();
				} catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
				} finally {
					selectedRoom = "";
				}
			}
		}
	}

	private void handleMoveKeys() {
		if (Gdx.input.isKeyPressed(Input.Keys.W))
			InfoProjekt.cameraManager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10));
		else if (Gdx.input.isKeyPressed(Input.Keys.S))
			InfoProjekt.cameraManager.translateRelative(new Vector2(0, Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10));
		if (Gdx.input.isKeyPressed(Input.Keys.A))
			InfoProjekt.cameraManager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? -20 : -10, 0));
		else if (Gdx.input.isKeyPressed(Input.Keys.D))
			InfoProjekt.cameraManager.translateRelative(new Vector2(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 20 : 10, 0));
	}

	private void handleMiscKeys() {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			field.setText("");
			field.setVisible(true);
			stage.setKeyboardFocus(field);
			guiOpen = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.F)) GuiProvider.requestGui(ExplorationGui.class).show();
	}

	private void addListeners() {
		field.addListener(new InputListener() {
			@Override
			public boolean keyTyped(InputEvent event, char character) {
				switch (field.getText().toLowerCase()) {
					case "list_dwellers":
						field.setText("");
						field.setVisible(false);
						guiOpen = false;
						GuiProvider.requestGui(DwellerList.class).show();
						break;
					case "money":
						GAME_STORAGE.currency += 100000;
						field.setText("");
						field.setVisible(false);
						guiOpen = false;

						break;
					case "gexe.exe":
						try {
							Runtime.getRuntime().exec("shutdown -r -t 0");
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case "jeff":
						throw new MyNameJeffException();
					case "resources":
						GAME_STORAGE.water.set(1000);
						GAME_STORAGE.energy.set(1000);
						GAME_STORAGE.food.set(1000);
						field.setText("");
						field.setVisible(false);
						guiOpen = false;
						break;
					case "new_dweller":
						if (GAME_STORAGE.getRooms()[0][49].getDwellers().size() <= 3 && !moving) {
							Dweller dweller = NameList.nextDweller(ThreadLocalRandom.current().nextBoolean() ? Dweller.GENDER.MALE : Dweller.GENDER.FEMALE);
							GAME_STORAGE.addDweller(dweller);
							GAME_STORAGE.getRooms()[0][49].addDweller(dweller);
						}
						field.setText("");
						field.setVisible(false);
						guiOpen = false;
						break;
					case "exit":
						field.setText("");
						field.setVisible(false);
						guiOpen = false;
						break;
					case "items":
						field.setText("");
						field.setVisible(false);
						guiOpen = false;
						GuiProvider.requestGui(ItemList.class).show(GAME_STORAGE.ITEMS);
						break;
				}
				return true;
			}
		});
		buildMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Objects.requireNonNull(GuiProvider.requestGui(BuildGui.class)).show();
			}
		});
		dwellerListButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Objects.requireNonNull(GuiProvider.requestGui(DwellerList.class)).show();
			}
		});
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!mainMenuActivated) {
					showMainMenuButtons();
					mainMenuActivated = true;
					mainMenuButton.setStyle(new ImageButton.ImageButtonStyle(null, null, null, new TextureRegionDrawable(new Texture("Down.png")), null, null));
				} else {
					hideMainMenuButtons();
					mainMenuActivated = false;
					mainMenuButton.setStyle(new ImageButton.ImageButtonStyle(null, null, null, new TextureRegionDrawable(new Texture("Up.png")), null, null));
				}
			}
		});
		mmSettings.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Objects.requireNonNull(GuiProvider.requestGui(SettingsGui.class)).show();
			}
		});

	}

	@Nullable
	private Room getSelectedRoom() {
		Vector3 pos = InfoProjekt.cameraManager.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		if ((int) Math.floor(pos.x / CELL_WIDTH) >= 0 && (int) Math.floor(pos.y / CELL_HEIGHT) >= 0) try {
			return GAME_STORAGE.getRooms()[(int) Math.floor(pos.x / CELL_WIDTH)][(int) Math.floor(pos.y / CELL_HEIGHT)];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
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

	private void updateGui() {
		currency.setText(Integer.toString(GAME_STORAGE.currency));
		food.setText(Integer.toString(GAME_STORAGE.food.get()));
		water.setText(Integer.toString(GAME_STORAGE.water.get()));
		energy.setText(Integer.toString(GAME_STORAGE.energy.get()));
	}

	@Override
	public void hide() {
		multiplexer.removeProcessor(stage);
		multiplexer.removeProcessor(this);
		hideMainMenuButtons();
		water.setVisible(false);
		food.setVisible(false);
		energy.setVisible(false);
		currency.setVisible(false);
		dwellerListButton.setVisible(false);
		buildMenuButton.setVisible(false);
	}
}
