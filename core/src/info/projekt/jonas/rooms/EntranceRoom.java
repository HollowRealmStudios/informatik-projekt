package info.projekt.jonas.rooms;

/**
 * @author Jonas
 */
public class EntranceRoom extends Room {


	public EntranceRoom() {
		super("EntranceRoom", "EntranceRoom/EntranceRoom_1.png");
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
