package info.projekt.jonas.rooms;

public class Barracks extends Room {

    public Barracks() {
        super("Barracks", "Barracks/Barracks_1.png", "Barracks/Barracks_2.png", "Barracks/Barracks_3.png");
        setCost(800);
        setProduct(PRODUCT.NONE);
    }

}
