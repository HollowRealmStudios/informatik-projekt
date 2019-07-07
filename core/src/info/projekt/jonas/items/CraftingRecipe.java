package info.projekt.jonas.items;

import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CraftingRecipe {

	private HashMap<CraftingComponent, Integer> ingredients = new HashMap<>();
	private Item result;

	public CraftingRecipe(Item result, @NotNull ArrayList<Tuple<CraftingComponent, Integer>> tuples) {
		tuples.forEach(t -> ingredients.put(t.getOne(), t.getTwo()));
		this.result = result;
	}

	public HashMap<CraftingComponent, Integer> getIngredients() {
		return ingredients;
	}

	public boolean enoughIngredients(@NotNull HashMap<CraftingComponent, Integer> ingredients) {
		for (Map.Entry<CraftingComponent, Integer> entry : ingredients.entrySet()) {
			if (!(this.ingredients.containsKey(entry.getKey()) && entry.getValue() >= this.ingredients.get(entry.getKey())))
				return false;
		}
		return true;
	}

	public Item craft(@NotNull HashMap<CraftingComponent, Integer> ingredients) {
		if(!enoughIngredients(ingredients)) throw new IllegalArgumentException("Not enough or fitting ingredients");
		for (Map.Entry<CraftingComponent, Integer> entry : ingredients.entrySet()) {
			entry.setValue(entry.getValue() - this.ingredients.get(entry.getKey()));
			ingredients.put(entry.getKey(), entry.getValue());
		}
		return result;
	}


	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		ingredients.forEach((v, k) -> b.append(v.name).append(" * ").append(k).append(", "));
		b.append(" => ").append(result.name);
		return b.toString();
	}
}
