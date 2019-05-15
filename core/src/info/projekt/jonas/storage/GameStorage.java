package info.projekt.jonas.storage;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.rooms.DebugRoom;
import info.projekt.jonas.rooms.Room;

import java.io.Serializable;
import java.util.Arrays;

public class GameStorage implements Serializable {

    private final Room[][] ROOMS = new Room[5][50];

    public Room[][] getRooms() {
        return ROOMS;
    }

    public void debug() {
        for (Room[] rooms : ROOMS) {
            Arrays.fill(rooms, new DebugRoom(0, 0, new Texture("room_debug.png")));
        }
        for (Room[] rooms : ROOMS) {
            for (Room room : rooms) {
                System.out.println(room.toString());
            }
        }
    }
}
