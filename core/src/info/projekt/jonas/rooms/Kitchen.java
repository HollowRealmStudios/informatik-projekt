package info.projekt.jonas.rooms;

public class Kitchen extends Room {

	public Kitchen() {
		super("Kitchen", "Kitchen/Kitchen_1.png", "Kitchen/Kitchen_2.png", "Kitchen/Kitchen_3.png");
		setCost(400);
	}

	@Override
	public void produce() {

	}

	@Override
	public void consume() {

	}
}
