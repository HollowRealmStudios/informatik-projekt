package info.projekt.jonas.rooms;

import static info.projekt.InfoProjekt.GAME_STORAGE;

/**
 * @author Jonas
 */
public class KitchenRoom extends Room {

	public KitchenRoom() {
		super("Kitchen", "Kitchen/Kitchen_1.png", "Kitchen/Kitchen_2.png", "Kitchen/Kitchen_3.png");
		setCost(400);
	}

	@SuppressWarnings("unused")
	@Override
	public void produce() {
		dwellers.forEach(d -> GAME_STORAGE.food.add(3 * d.getCreativity()));
	}

	@SuppressWarnings("unused")
	@Override
	public void consume() {

	}

	@Override
	public boolean enoughResources() {
		return true;
	}
}
