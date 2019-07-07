package info.projekt.jonas.rooms;

/**
 * @author Jonas
 */
public class StorageRoom extends Room {

	public StorageRoom() {
		super("Storage", "Storage/Storage_1.png", "Storage/Storage_2.png", "Storage/Storage_3.png");
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
