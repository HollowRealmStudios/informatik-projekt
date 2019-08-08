package info.projekt.jonas.rooms;

<<<<<<< HEAD
import static info.projekt.InfoProjekt.GAME_STORAGE;

/**
 * @author Jonas
 */
@Buildable
=======
@SuppressWarnings("unused")
>>>>>>> Began entire rewrite of codebase
public class EngineRoom extends Room {

	public EngineRoom() {
		super("EngineRoom", "EngineRoom/EngineRoom_1.png", "EngineRoom/EngineRoom_2.png", "EngineRoom/EngineRoom_3.png");
		setCost(600);
	}

	@Override
	public void produce() {
<<<<<<< HEAD
		dwellers.forEach(d -> GAME_STORAGE.energy.add(3 * d.getStrength()));
	}

	@Override
	public void consume() {dwellers.forEach(d -> GAME_STORAGE.food.subtract(4));
	}

	@Override
	public boolean enoughResources() {
		return true;
=======
		//dwellers.forEach(d -> GAME_STORAGE.energy.add(d.getStrength()));
	}

	@Override
	public void consume() {
		//dwellers.forEach(d -> GAME_STORAGE.food.subtract(4));
>>>>>>> Began entire rewrite of codebase
	}
}
