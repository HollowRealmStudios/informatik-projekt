package info.projekt.jonas.storage;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import info.projekt.jonas.items.*;
import info.projekt.jonas.rooms.Room;
import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jonas
 */
public class Registry {

	private static final HashMap<String, Room> ROOMS = new HashMap<>();
	private static final HashMap<String, Item> ITEMS = new HashMap<>();
	private static final HashMap<String, CraftingComponent> COMPONENTS = new HashMap<>();
	private static final ArrayList<CraftingRecipe> RECIPES = new ArrayList<>();

	public static void registerWeapons() throws IOException {

		JsonArray in = JsonObject.readFrom(new FileReader("Weapons.json")).get("weapons").asArray();
		for (JsonValue object : in.asArray()) {
			JsonObject obj = object.asObject();
			ITEMS.put(obj.get("name").asString(), new WeaponItem(obj.get("texture").asString(), obj.get("name").asString(), obj.get("damage").asInt(), obj.get("deviation").asInt()));
		}
	}

	public static void registerArmors() throws IOException {
		JsonArray in = JsonObject.readFrom(new FileReader("Armor.json")).get("armors").asArray();
		for (JsonValue object : in.asArray()) {
			JsonObject obj = object.asObject();
			ITEMS.put(obj.get("name").asString(), new ArmorItem(obj.get("texture").asString(), obj.get("name").asString(), obj.get("protection").asInt(), obj.get("deviation").asInt()));
		}
	}

	public static void registerComponents() throws IOException {
		JsonArray in = JsonObject.readFrom(new FileReader("Components.json")).get("components").asArray();
		for (JsonValue object : in.asArray()) {
			JsonObject obj = object.asObject();
			COMPONENTS.put(obj.get("name").asString(), new CraftingComponent(obj.get("name").asString().replace(" ", "") + ".png", obj.get("name").asString(), obj.get("rarity").asInt()));
		}
	}

	public static void registerRecipes() throws IOException {
		JsonArray in = JsonObject.readFrom(new FileReader("Recipes.json")).get("recipes").asArray();
		for (JsonValue object : in.asArray()) {
			JsonObject obj = object.asObject();
			Item result = Registry.getItem(obj.get("result").asString());
			ArrayList<Tuple<CraftingComponent, Integer>> ingredients = new ArrayList<>();
			for (JsonValue value : obj.get("ingredients").asArray()) {
				ingredients.add(new Tuple<>(Registry.getComponent(value.asArray().get(0).asString()), value.asArray().get(1).asInt()));
			}
			RECIPES.add(new CraftingRecipe(result, ingredients, obj.get("time").asInt()));
		}
	}

	public static HashMap<String, Item> getItems() {
		return ITEMS;
	}

	public static void registerRooms() {
		new Reflections("info.projekt.jonas.rooms").getSubTypesOf(Room.class).forEach((Class c) -> {
			try {
				ROOMS.put(c.getSimpleName(), (Room) c.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Return a room by its name
	 *
	 * @param name the name of the room
	 * @return the room with the name specified
	 * @see Room
	 */
	@Nullable
	public static Room getRoom(String name) {
		try {
			return ROOMS.get(name).getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HashMap<String, Room> getRooms() {
		return ROOMS;
	}

	/**
	 * Return an Item by its name
	 *
	 * @param name the name of the Item
	 * @return the Item with the name specified
	 * @see Item
	 */
	public static Item getItem(String name) {

		if (!ITEMS.containsKey(name)) throw new IllegalArgumentException(name + " doesn't exist");
		return ITEMS.get(name);
	}

	public static HashMap<String, CraftingComponent> getComponents() {
		return COMPONENTS;
	}

	public static CraftingComponent getComponent(@NotNull String s) {
		return COMPONENTS.get(s);
	}

	public static void debug() {
		StringBuilder b = new StringBuilder();
		b.append("------------------------------Items------------------------------\n");
		ITEMS.forEach((v, k) -> {
			b.append(k.toString());
			b.append("\n");
		});
		b.append("------------------------------Rooms------------------------------\n");
		ROOMS.forEach((v, k) -> {
			b.append(k.toString());
			b.append("\n");
		});
		b.append("----------------------------Components----------------------------\n");
		COMPONENTS.forEach((v, k) -> {
			b.append(k.toString());
			b.append("\n");
		});
		b.append("-----------------------------Recipes-------------------------------\n");
		RECIPES.forEach(k -> {
			b.append(k.toString());
			b.append("\n");
		});
		System.out.println(b.toString());
	}

	public static ArrayList<CraftingRecipe> getRecipes() {
		return RECIPES;
	}
}
