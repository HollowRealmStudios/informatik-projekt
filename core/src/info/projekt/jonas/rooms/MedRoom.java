package info.projekt.jonas.rooms;

/**
 * @author Jonas
 */
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

	}

	@Override
	public boolean enoughResources() {
		return true;
	}
}
