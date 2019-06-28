package info.projekt.jonas.rooms;

import static info.projekt.InfoProjekt.GAME_STORAGE;

public class Kitchen extends Room {

    public Kitchen() {
        super("Kitchen", "Kitchen/Kitchen_1.png", "Kitchen/Kitchen_2.png", "Kitchen/Kitchen_3.png");
        setCost(400);
    }

    @SuppressWarnings("unused")
    @Override
    public void produce() {
	    dwellers.forEach(d -> GAME_STORAGE.food.add(d.getCreativity()));
    }

    @SuppressWarnings("unused")
    @Override
    public void consume() {
	    dwellers.forEach(d -> GAME_STORAGE.energy.subtract(4));
    }
}
