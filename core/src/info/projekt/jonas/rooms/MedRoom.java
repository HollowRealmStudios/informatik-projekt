package info.projekt.jonas.rooms;

/**
 * @author Jonas
 */
@Buildable
public class MedRoom extends Room {

	public MedRoom() {
		super("MedRoom", "MedRoom/MedRoom_1.png", "MedRoom/MedRoom_2.png", "MedRoom/MedRoom_3.png");
		setCost(1000);
	}

	@Override
	public void produce() {

	}

	@Override
	public void consume() {
		//GAME_STORAGE.food.subtract(getDwellers().size() * 2);
		//GAME_STORAGE.water.subtract(getDwellers().size() * 2);
		//GAME_STORAGE.energy.subtract(getDwellers().size() * 2);
	}

	@Override
	public boolean enoughResources() {
		//return GAME_STORAGE.food.get() >= getDwellers().size() * 2 && GAME_STORAGE.water.get() >= getDwellers().size() * 2 && GAME_STORAGE.energy.get() >= getDwellers().size() * 2;
		return true;
	}

}
