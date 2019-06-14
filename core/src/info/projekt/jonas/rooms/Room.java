package info.projekt.jonas.rooms;

import com.badlogic.gdx.graphics.Texture;
import info.projekt.jonas.dwellers.Dweller;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    public enum PRODUCT {WATER, FOOD, ENERGY, OTHER, NONE}

    private int cost = 0;
    private Dweller[] dwellers = new Dweller[4];
    private PRODUCT product = PRODUCT.NONE;
    private final String name;
    private int level;
    private transient ArrayList<Texture> textures = new ArrayList<>();

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setProduct(PRODUCT product) {
        this.product = product;
    }

    public PRODUCT getProduct() {
        return product;
    }

    public int getCost() {
        return cost;
    }

    public void produce() {}

    public void onTick() {
        System.out.println("tick");
    }

    public void onPlace() {
        System.out.println("place");
    }

    public void onUpgrade() {
        System.out.println("upgrade");
    }

    public void onNewDweller() {
        System.out.println("new dweller");
    }

    protected Room(@NotNull String name, @NotNull String texture, @NotNull String... textures) {
        this.name = name;
        this.textures.add(new Texture(texture));
        for (String s : textures) this.textures.add(new Texture(s));
    }

    public Dweller[] getDwellers() {
        return dwellers;
    }

    public Texture getTexture() {
        return textures.get(level);
    }

    public boolean upgradable() {
        return level < textures.size();
    }

    public void upgrade() {
        if (level == textures.size())
            throw new IllegalArgumentException("A level " + textures.size() + " room can't be upgraded any further");
        level++;
    }
}