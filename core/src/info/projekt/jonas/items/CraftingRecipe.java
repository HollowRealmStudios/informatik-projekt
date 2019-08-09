package info.projekt.jonas.items;

import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class CraftingRecipe {

	private final HashMap<CraftingComponent, Integer> ingredients = new HashMap<>();
	private final Item result;
	private final int time;

	public CraftingRecipe(Item result, @NotNull ArrayList<Tuple<CraftingComponent, Integer>> tuples, int time) {
		tuples.forEach(t -> ingredients.put(t.getOne(), t.getTwo()));
		this.result = result;
		this.time = time;
	}

	public HashMap<CraftingComponent, Integer> getIngredients() {
		return ingredients;
	}

	public boolean enoughIngredients(@NotNull ArrayList<CraftingComponent> ingredients) {
		ArrayList<CraftingComponent> components = new ArrayList<>();
		this.ingredients.forEach((v, k) -> {
			for (int i = 0; i < k; i++) components.add(v);
		});
		return ingredients.containsAll(components);
	}

	public Item craft(@NotNull ArrayList<CraftingComponent> ingredients) {
		ArrayList<CraftingComponent> components = new ArrayList<>();
		this.ingredients.forEach((v, k) -> {
			for (int i = 0; i < k; i++) components.add(v);
		});
		if (ingredients.containsAll(components)) return result;
		return null;
	}

	public int getTime() {
		return time;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		ingredients.forEach((v, k) -> b.append(v.name).append(" * ").append(k));
		b.append(" time: ").append(time);
		b.append(" => ").append(result.name);
		return b.toString();
	}

	public String prettyPrint() {
		StringBuilder b = new StringBuilder();
		ingredients.forEach((v, k) -> b.append(v.name).append(" * ").append(k));
		b.append(" time: ").append(time);
		b.append(" => ").append(result.name);
		return b.toString();
	}
}
