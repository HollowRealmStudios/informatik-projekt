package info.projekt.jonas;

import com.badlogic.gdx.graphics.Texture;

/**
 * Every animated texture must implement this interface. The drawing is handled automated
 * */
public interface Animated {

	public Texture getNextFrame();
	public void resetAnimation();
	public int getFrameCount();

}
