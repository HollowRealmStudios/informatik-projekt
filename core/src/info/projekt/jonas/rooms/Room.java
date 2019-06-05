package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.dwellers.Dweller;

import java.io.Serializable;

/**
 * @author Jonas
 * The child class, every room inherits from
 */
public class Room implements Serializable {

    /**
     * Base products that can be generated by rooms
     */
    public enum PRODUCT {WATER, FOOD, POWER, NONE}

    /**
     * The product the room generates
     */
    private PRODUCT product;
    /**
     * The room's level
     */
    private int currentLevel = 1;
    /**
     * The amount of resources produced per second
     */
    private int production;
    /**
     * The dwellers currently residing in the room
     */
    private Dweller[] dwellers = new Dweller[4];
    /**
     * The textures of the room at different levels. These cant be serialized
     */
    private transient Texture textureLvlOne;
    private transient Texture textureLvlTwo;
    private transient Texture textureLvlThree;
    /**
     * The name of the room
     */
    public final String name;

    /**
     * The default constructor
     *
     * @param name    the name of the Room
     * @param product the product the room produces
     */
    public Room(String name, String product) {
        this.name = name;
        switch (product.toUpperCase()) {
            case "WATER":
                this.product = PRODUCT.WATER;
                break;
            case "FOOD":
                this.product = PRODUCT.FOOD;
                break;
            case "POWER":
                this.product = PRODUCT.POWER;
                break;
            case "NONE":
                this.product = PRODUCT.NONE;
        }
        textureLvlOne = new Texture(name + "/" + name + "_1" + ".png");
        textureLvlTwo = new Texture(name + "/" + name + "_2" + ".png");
        textureLvlThree = new Texture(name + "/" + name + "_3" + ".png");

    }

    /**
     * @return return the room's texture
     */
    public Texture getTexture() {
        switch (currentLevel) {
            case 1:
                return textureLvlOne;
            case 2:
                return textureLvlTwo;
            case 3:
                return textureLvlThree;
            default:
                return textureLvlOne;
        }
    }

    public int getLevel() { return currentLevel; }

    public boolean upgradable() {
        return currentLevel < 3;
    }

    public void upgrade() {
        if (!upgradable()) throw new IllegalArgumentException("A level 3 room can't be upgraded any further");
        currentLevel++;
    }

    @Override
    public String toString() {
        return "Level: " + currentLevel + ", Product: " + product;
    }
}
