package info.projekt.jonas.items;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class WeaponItem extends Item {

    private int damage;

    public WeaponItem(Texture texture, String name, int damage, int deviation) {
        super(texture, name);
        this.damage = ThreadLocalRandom.current().nextBoolean() ? damage - ThreadLocalRandom.current().nextInt(deviation) : damage + ThreadLocalRandom.current().nextInt(deviation);
    }
}
