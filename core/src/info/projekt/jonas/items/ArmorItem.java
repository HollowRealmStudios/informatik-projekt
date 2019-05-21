package info.projekt.jonas.items;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class ArmorItem extends Item {

    private int protection;

    public ArmorItem(Texture texture, int protection, int deviation) {
        super(texture);
        this.protection = ThreadLocalRandom.current().nextBoolean() ? protection - ThreadLocalRandom.current().nextInt(deviation) : protection + ThreadLocalRandom.current().nextInt(deviation);
    }
}
