package info.projekt.jonas.gui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class ProgressBar extends GuiComponent {

    private int amount;
    private Rectangle outline;
    private Color color;

    public ProgressBar(Color c, int x, int y, int width, int height) {
        color = c;
        outline = new Rectangle(x, y, width, height);
    }

    public void update(int amount) {
        this.amount = MathUtils.clamp(this.amount += amount, 0, 100);
    }

    @Override
    public void paint(Object o) {
        ShapeRenderer renderer = (ShapeRenderer) o;
        renderer.setColor(new com.badlogic.gdx.graphics.Color(color.getRed() / 255, color.getGreen() / 255, color.getBlue() / 255, 1f));
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(outline.x, outline.y, outline.width, outline.height);
        renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(outline.x, outline.y, outline.width / 100 * amount, outline.height);
        renderer.end();
    }
}
