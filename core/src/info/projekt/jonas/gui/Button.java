package info.projekt.jonas.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import info.projekt.InfoProjekt;

import static info.projekt.InfoProjekt.manager;
import static info.projekt.jonas.gui.RenderUtils.*;

public class Button extends GuiComponent {

	private Texture texture;
	private Rectangle box;

	public Button(Texture texture, int x, int y) {
		this.texture = texture;
		box = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
	}

	public Button(Texture texture, int x, int y, int width, int height) {
		this.texture = texture;
		box = new Rectangle(x, y, width, height);
	}

	public boolean clicked(Vector2 pos) {
		return box.contains(pos);
	}

	@Override
	public void paint(Object o) {
		SpriteBatch batch = (SpriteBatch) o;
		batch.begin();
		batch.draw(texture, box.x, box.y);
		batch.end();
	}

	@Override
	public String toString() {
		return "Button at " + box.toString() + " with texture " + texture.toString() + " fired.";
	}
}
