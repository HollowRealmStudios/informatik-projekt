package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.dwellers.Dweller;

import java.io.Serializable;

/*
 *The child class, every room inherits from
 * */
public class Room implements Serializable {

    public enum PRODUCT {WATER, FOOD, POWER}

    private PRODUCT product;
    private int currentLevel = 1;
    private int production;
    private Dweller[] dwellers = new Dweller[4];
    private transient Texture textureLvlOne;
    private transient Texture textureLvlTwo;
    private transient Texture textureLvlThree;
    private String name;


    public Room(String name, PRODUCT product) {
        this.name = name;
        this.product = product;
    }

    /**
     * @return return the room's texture
     */
    public Texture getTexture() {
        switch (currentLevel) {
            case 1:
                if (textureLvlOne == null) textureLvlOne = new Texture(name + "_1" + ".png");
                return textureLvlOne;
            case 2:
                if (textureLvlTwo == null) textureLvlTwo = new Texture(name + "_2" + ".png");
                return textureLvlTwo;
            case 3:
                if (textureLvlThree == null) textureLvlThree = new Texture(name + "_3" + ".png");
                return textureLvlThree;
        }
        return null;
    }

    /**
     * set the level of the room
     *
     * @param level the level to set to
     */
    public void setLevel(int level) {
        this.currentLevel = level;
    }

    @Override
    public String toString() {
        return "Level: " + currentLevel + ", Product: " + product;
    }
}
