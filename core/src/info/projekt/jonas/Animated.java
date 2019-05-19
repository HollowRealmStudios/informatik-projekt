package info.projekt.jonas;

import com.badlogic.gdx.graphics.Texture;

public interface Animated {

	public Texture getNextFrame();
	public void resetAnimation();
	public int getFrameCount();

}
