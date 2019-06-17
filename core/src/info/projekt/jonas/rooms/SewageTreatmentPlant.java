package info.projekt.jonas.rooms;

public class SewageTreatmentPlant extends Room {

	public SewageTreatmentPlant() {
		super("SewageTreatmentPlant", "SewageTreatmentPlant/SewageTreatmentPlant_1.png", "SewageTreatmentPlant/SewageTreatmentPlant_2.png", "SewageTreatmentPlant/SewageTreatmentPlant_3.png");
		setCost(400);
		setProduct(PRODUCT.WATER);
	}

}
