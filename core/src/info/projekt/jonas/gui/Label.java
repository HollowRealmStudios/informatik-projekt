package info.projekt.jonas.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Label extends GuiComponent {

    private BitmapFont font;
    public String text;

    public Label(String text, Font font, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new BitmapFont();
    }

    public void paint(SpriteBatch batch) {
    }

    @Override
    public void paint(Object o) {
        SpriteBatch batch = (SpriteBatch) o;
        batch.begin();
        font.draw(batch, text, x, y);
        batch.end();
    }
}
