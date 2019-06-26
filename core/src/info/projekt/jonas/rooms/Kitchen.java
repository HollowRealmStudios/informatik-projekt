package info.projekt.jonas.rooms;

//FIXME
public class Kitchen extends Room {

    public Kitchen() {
        super("Kitchen", "Kitchen/Kitchen_1.png", "Kitchen/Kitchen_2.png", "Kitchen/Kitchen_3.png");
        setCost(400);
    }

    @SuppressWarnings("unused")
    @Override
    public void produce() {

    }

    @SuppressWarnings("unused")
    @Override
    public void consume() {

    }
}
