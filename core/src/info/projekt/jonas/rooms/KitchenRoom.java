package info.projekt.jonas.rooms;

/**
 * @author Jonas
 */
@Buildable
public class KitchenRoom extends Room {

	public KitchenRoom() {
		super("Kitchen", "Kitchen/Kitchen_1.png", "Kitchen/Kitchen_2.png", "Kitchen/Kitchen_3.png");
		setCost(400);
	}

	@Override
	public void produce() {
		//dwellers.forEach(d -> GAME_STORAGE.food.add(3 * d.getCreativity()));
	}

	@Override
	public void consume() {

	}

	@Override
	public boolean enoughResources() {
		return true;
	}
}
