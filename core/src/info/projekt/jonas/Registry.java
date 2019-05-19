package info.projekt.jonas;

import info.projekt.jonas.rooms.Room;

import java.util.ArrayList;

public class Registry {

	private static final ArrayList<Class<? extends Room>> ROOMS = new ArrayList<Class<? extends Room>>();
	private static final ArrayList<Class<? extends Dweller>> DWELLERS = new ArrayList<Class<? extends Dweller>>();

	public static void registerRoom(Room room) {
		if(ROOMS.contains(room.getClass())) throw new IllegalArgumentException("Room " + room.getClass().getName() + " already registered!");
		ROOMS.add(room.getClass());
	}

	public static void registerDweller(Dweller dweller) {
		if(DWELLERS.contains(dweller.getClass())) throw new IllegalArgumentException("Dweller " + dweller.getClass().getName() + " already registered!");
		DWELLERS.add(dweller.getClass());
	}

}
