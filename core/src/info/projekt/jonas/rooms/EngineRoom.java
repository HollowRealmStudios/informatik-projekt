package info.projekt.jonas.rooms;

import static info.projekt.InfoProjekt.GAME_STORAGE;

@SuppressWarnings("unused")
public class EngineRoom extends Room {

	public EngineRoom() {
		super("EngineRoom", "EngineRoom/EngineRoom_1.png", "EngineRoom/EngineRoom_2.png", "EngineRoom/EngineRoom_3.png");
		setCost(600);
	}

	@Override
	public void produce() {
		dwellers.forEach(d -> GAME_STORAGE.energy.add(d.getStrength()));
	}

	@Override
	public void consume() {
		dwellers.forEach(d -> GAME_STORAGE.food.subtract(4));
	}
}
