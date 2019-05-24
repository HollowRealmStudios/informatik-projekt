package info.projekt.jonas.items;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class ArmorItem extends Item {

	private int protection;

	public ArmorItem(Texture texture, String name, int protection, int deviation) {
		super(texture, name);
		this.protection = ThreadLocalRandom.current().nextBoolean() ? protection - ThreadLocalRandom.current().nextInt(deviation) : protection + ThreadLocalRandom.current().nextInt(deviation);
	}
}
