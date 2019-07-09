package info.projekt.jonas.rooms;

import static info.projekt.InfoProjekt.GAME_STORAGE;

/**
 * @author Jonas
 */
@Buildable
public class EngineRoom extends Room {

	public EngineRoom() {
		super("EngineRoom", "EngineRoom/EngineRoom_1.png", "EngineRoom/EngineRoom_2.png", "EngineRoom/EngineRoom_3.png");
		setCost(600);
	}

	@Override
	public void produce() {
		dwellers.forEach(d -> GAME_STORAGE.energy.add(3 * d.getStrength()));
	}

	@Override
	public void consume() {dwellers.forEach(d -> GAME_STORAGE.food.subtract(4));
	}

	@Override
	public boolean enoughResources() {
		return true;
	}
}
