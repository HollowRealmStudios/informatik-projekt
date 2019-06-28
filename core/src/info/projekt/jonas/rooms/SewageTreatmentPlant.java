package info.projekt.jonas.rooms;

import static info.projekt.InfoProjekt.GAME_STORAGE;

@SuppressWarnings("unused")
public class SewageTreatmentPlant extends Room {

    public SewageTreatmentPlant() {
        super("SewageTreatmentPlant", "SewageTreatmentPlant/SewageTreatmentPlant_1.png", "SewageTreatmentPlant/SewageTreatmentPlant_2.png", "SewageTreatmentPlant/SewageTreatmentPlant_3.png");
        setCost(400);
    }

    @Override
    public void produce() {
	    dwellers.forEach(d -> GAME_STORAGE.water.add(d.getIntelligence()));
    }

    @Override
    public void consume() {
	    dwellers.forEach(d -> GAME_STORAGE.energy.subtract(4));
	    dwellers.forEach(d -> GAME_STORAGE.food.subtract(4));
    }
}
