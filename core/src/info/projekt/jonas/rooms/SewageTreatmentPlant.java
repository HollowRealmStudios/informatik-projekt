package info.projekt.jonas.rooms;

import static info.projekt.InfoProjekt.GAME_STORAGE;

/**
 * @author Jonas
 */
@Buildable
public class SewageTreatmentPlant extends Room {

	public SewageTreatmentPlant() {
		super("SewageTreatmentPlant", "SewageTreatmentPlant/SewageTreatmentPlant_1.png", "SewageTreatmentPlant/SewageTreatmentPlant_2.png", "SewageTreatmentPlant/SewageTreatmentPlant_3.png");
		setCost(400);
	}

	@Override
	public void produce() {
		dwellers.forEach(d -> GAME_STORAGE.water.add(3 * d.getIntelligence()));
	}

	@Override
	public void consume() {

	}

	@Override
	public boolean enoughResources() {
		return true;
	}
}
