package info.projekt.jonas.rooms;

public class MedRoom extends Room {

        public MedRoom() {
            super("MedRoom", "MedRoom/MedRoom_1.png", "MedRoom/MedRoom_2.png", "MedRoom/MedRoom_3.png");
            setCost(1000);
            setProduct(PRODUCT.OTHER);
        }
}
