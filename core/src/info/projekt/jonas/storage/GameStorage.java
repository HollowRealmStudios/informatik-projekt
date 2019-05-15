package info.projekt.jonas.storage;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.rooms.DebugRoom;
import info.projekt.jonas.rooms.Room;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class GameStorage implements Serializable {

    private final Room[][] ROOMS = new Room[5][50];

    public Room[][] getRooms() {
        return ROOMS;
    }

    public void debug() {
        for (int x = 0; x < ROOMS.length; x++) {
            for (int y = 0; y < ROOMS[0].length; y++) {
                if(new Random().nextBoolean()) ROOMS[x][y] = new DebugRoom(0, 0, new Texture("baracke____mega fake___ pls change.png"));
                else ROOMS[x][y] = new DebugRoom(0, 0, new Texture("küche___mega fake___ pls change.png"));            }
        }
    }
}
