package info.projekt.jonas.rooms;

public class EngineRoom extends Room {

	public EngineRoom() {
		super("EngineRoom", "EngineRoom/EngineRoom_1.png", "EngineRoom/EngineRoom_2.png", "EngineRoom/EngineRoom_3.png");
		setCost(600);
		setProduct(PRODUCT.ENERGY);
	}
}
