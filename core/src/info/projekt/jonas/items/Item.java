package info.projekt.jonas.items;

import com.badlogic.gdx.graphics.Texture;

public class Item {

    private String name;
    private Texture texture;

    protected Item(Texture texture, String name) {
        this.texture = texture;
        this.name = name;
    }
}
