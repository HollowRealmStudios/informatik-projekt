package info.projekt.jonas.storage;

import info.projekt.jonas.dwellers.Dweller;
import info.projekt.jonas.rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Jonas
 */
public class GameStorage implements Serializable {

    /**
     * The main 2D-Array of rooms
     */
    private final Room[][] ROOMS = new Room[5][50];

    private final ArrayList<Dweller> DWELLERS = new ArrayList<>();

    public int currency;

    public int food;

    public int energy;

    public int water;

    public void addDweller(Dweller dweller) {
        DWELLERS.add(dweller);
    }

    public ArrayList<Dweller> getDwellers() {
        return DWELLERS;
    }

    /**
     * @return the 2D-Array of rooms
     */
    public Room[][] getRooms() {
        return ROOMS;
    }

    /**
     * Set a specific room at a specific position
     *
     * @param room the room to set
     * @param x    the x position
     * @param y    the y position
     */
    public void setRoom(Room room, int x, int y) {
        ROOMS[x][y] = room;
    }
}
