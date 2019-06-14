package info.projekt.jonas.items;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Jonas
 * */
public abstract class Item {

    /**The name of the item*/
    public final String name;

    /**The texture of the item*/
    protected Texture texture;

    /**The default constructor
     * @param texture the item's texture
     * @param name the item's name
     * */
    protected Item(Texture texture, String name) {
        this.texture = texture;
        this.name = name;
    }

    public abstract String toString();
}
