package info.projekt.jonas.rooms;


/**
 * @author Jonas
 */
public class BarrackRoom extends Room {

	public BarrackRoom() {
		super("Barracks", "Barracks/Barracks_1.png", "Barracks/Barracks_2.png", "Barracks/Barracks_3.png");
		setCost(800);
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
