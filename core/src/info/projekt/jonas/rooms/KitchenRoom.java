package info.projekt.jonas.rooms;

<<<<<<< HEAD:core/src/info/projekt/jonas/rooms/KitchenRoom.java
import static info.projekt.InfoProjekt.GAME_STORAGE;

/**
 * @author Jonas
 */
@Buildable
public class KitchenRoom extends Room {
=======
public class Kitchen extends Room {
>>>>>>> Began entire rewrite of codebase:core/src/info/projekt/jonas/rooms/Kitchen.java

	public KitchenRoom() {
		super("Kitchen", "Kitchen/Kitchen_1.png", "Kitchen/Kitchen_2.png", "Kitchen/Kitchen_3.png");
		setCost(400);
	}

	@Override
	public void produce() {
<<<<<<< HEAD:core/src/info/projekt/jonas/rooms/KitchenRoom.java
		dwellers.forEach(d -> GAME_STORAGE.food.add(3 * d.getCreativity()));
=======
		//dwellers.forEach(d -> GAME_STORAGE.food.add(d.getCreativity()));
>>>>>>> Began entire rewrite of codebase:core/src/info/projekt/jonas/rooms/Kitchen.java
	}

	@Override
	public void consume() {
<<<<<<< HEAD:core/src/info/projekt/jonas/rooms/KitchenRoom.java

	}

	@Override
	public boolean enoughResources() {
		return true;
=======
		//dwellers.forEach(d -> GAME_STORAGE.energy.subtract(4));
>>>>>>> Began entire rewrite of codebase:core/src/info/projekt/jonas/rooms/Kitchen.java
	}
}
