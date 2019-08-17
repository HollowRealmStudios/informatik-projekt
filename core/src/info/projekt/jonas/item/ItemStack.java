package info.projekt.jonas.item;

import info.projekt.jonas.util.Tuple;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ItemStack {

	private final Tuple<Item, Integer> tuple;

	@Contract(pure = true)
	public ItemStack(@NotNull Item item, int amount) {
		tuple = new Tuple<>(item, amount);
	}

	@Contract(pure = true)
	public ItemStack(@NotNull Item item) {
		tuple = new Tuple<>(item, 1);
	}

	public Item getItem() {
		return  tuple.getOne();
	}

	public int getAmount() {
		return tuple.getTwo();
	}

	public void setItem(Item item) {
		tuple.setOne(item);
	}

	public void setAmount(int amount) {
		tuple.setTwo(amount);
	}

}