package info.projekt.jonas.rooms;

@Buildable
public class WorkshopRoom extends Room {

    public WorkshopRoom() {
        super("Workshop", "Workshop/Workshop_1.png", "Workshop/Workshop_2.png", "Workshop/Workshop_3.png");
    }

	@Override
    public void produce() {

    }

    @Override
    public void consume() {

    }

    @Override
    public boolean enoughResources() {
        return false;
    }
}
