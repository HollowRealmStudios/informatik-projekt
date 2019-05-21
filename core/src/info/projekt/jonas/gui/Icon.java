package info.projekt.jonas.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Icon extends GuiComponent {

    private Texture icon;

    public Icon(Texture icon, int x, int y) {
        this.icon = icon;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Object o) {
        SpriteBatch batch = (SpriteBatch) o;
        batch.begin();
        batch.draw(icon, x, y);
        batch.end();
    }
}
