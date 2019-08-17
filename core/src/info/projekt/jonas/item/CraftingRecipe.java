package info.projekt.jonas.item;

import info.projekt.jonas.util.NotNullList;
import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class CraftingRecipe {

	private final NotNullList<ItemStack> ingredients = new NotNullList<>();
	private final Item result;
	private final int time;

	public CraftingRecipe(@NotNull ItemStack stack, @NotNull Item result, int time) {
		ingredients.add(stack);
		this.result = result;
		this.time = time;
	}

	public NotNullList<ItemStack> getIngredients() {
		return ingredients;
	}

	public boolean enoughIngredients(@NotNull NotNullList<ItemStack> ingredients) {
		return this.ingredients.stream().allMatch(o -> ingredients.contains(o));
	}
}
