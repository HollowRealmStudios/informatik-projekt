package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;

public class Room {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;

    private int level;
    private int capacity;
    private Texture texture;

    Room(int level, int capacity, Texture texture) {
        this.level = level;
        this.capacity = capacity;
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public String toString() {
        return "Level: " + level + ", Capacity: " + capacity + ", Texture: " + texture.toString();
    }
}
