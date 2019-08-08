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
public class SewageTreatmentPlant extends Room {

	public SewageTreatmentPlant() {
		super("SewageTreatmentPlant", "SewageTreatmentPlant/SewageTreatmentPlant_1.png", "SewageTreatmentPlant/SewageTreatmentPlant_2.png", "SewageTreatmentPlant/SewageTreatmentPlant_3.png");
		setCost(400);
	}

	@Override
	public void produce() {
<<<<<<< HEAD
		dwellers.forEach(d -> GAME_STORAGE.water.add(3 * d.getIntelligence()));
=======
		//dwellers.forEach(d -> GAME_STORAGE.water.add(d.getIntelligence()));
>>>>>>> Began entire rewrite of codebase
	}

	@Override
	public void consume() {
<<<<<<< HEAD

	}

	@Override
	public boolean enoughResources() {
		return true;
=======
		//dwellers.forEach(d -> GAME_STORAGE.energy.subtract(4));
		//dwellers.forEach(d -> GAME_STORAGE.food.subtract(4));
>>>>>>> Began entire rewrite of codebase
	}
}
