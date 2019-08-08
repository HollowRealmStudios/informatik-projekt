package info.projekt.jonas.rooms;

public class MineshaftRoom extends Room {

	private int pass = 0;

	public MineshaftRoom() {
		super("Mineshaft", "Mineshaft/Mineshaft_1.png");
	}

	@Override
	public void produce() {
		/*getDwellers().forEach(d -> pass++);
		if (pass > 10) {
			if (InfoCenter.isItemSpace()) {
				GAME_STORAGE.COMPONENTS.add((CraftingComponent) Registry.getComponents().values().toArray()[ThreadLocalRandom.current().nextInt(0, Registry.getComponents().size() - 1)]);
				notification.show("New item acquired", 2);
			} else notification.show("Not enough storage space", 4);
			if (pass > 10) {
				GAME_STORAGE.ITEMS.add((Item) Registry.getItems().values().toArray()[ThreadLocalRandom.current().nextInt(0, Registry.getItems().values().toArray().length - 1)]);
				notification.show("New component acquired", 2);
				pass = 0;
			} else notification.show("Not enough storage space", 4);
		}*/
	}

	@Override
	public void consume() {
		/*getDwellers().forEach(d -> {
			GAME_STORAGE.food.subtract(4);
			GAME_STORAGE.water.subtract(4);
			GAME_STORAGE.energy.subtract(4);
		});*/
	}

	@Override
	public boolean enoughResources() {
		//return GAME_STORAGE.food.get() >= 4 && GAME_STORAGE.food.get() >= 4 && GAME_STORAGE.energy.get() >= 4;
		return true;
	}
}
